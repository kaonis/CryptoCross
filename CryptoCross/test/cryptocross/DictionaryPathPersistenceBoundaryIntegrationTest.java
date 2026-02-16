package cryptocross;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.lang.reflect.Field;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import org.junit.jupiter.api.Test;

public class DictionaryPathPersistenceBoundaryIntegrationTest {

    @Test
    public void testDictionaryPathOverrideIsSessionScopedAndNotPersistedAcrossNewJvm() throws Exception {
        Path tempDict = Files.createTempFile("cryptocross-dict-persistence-boundary", ".txt");
        Files.writeString(tempDict, "ΑΒ\n", StandardCharsets.UTF_8);

        Field dictionaryPathField = CryptoCross.class.getDeclaredField("str_dictionaryPath");
        dictionaryPathField.setAccessible(true);
        String originalPath = (String) dictionaryPathField.get(null);

        try {
            CryptoCross.validateDictionaryFileForBoardSize(tempDict.toString(), 5);

            dictionaryPathField.set(null, tempDict.toString());
            assertEquals(tempDict.toString(), dictionaryPathField.get(null),
                    "Dictionary path override should apply within the current session");

            ProbeResult probeResult = runProbeInFreshJvm();
            assertEquals(0, probeResult.exitCode,
                    "Probe process should exit cleanly. stderr: " + probeResult.stderr);
            assertEquals("el-dictionary.txt", probeResult.stdout.trim(),
                    "Fresh JVM should load default dictionary path (no persisted override)");
        } finally {
            dictionaryPathField.set(null, originalPath);
            Files.deleteIfExists(tempDict);
        }
    }

    private ProbeResult runProbeInFreshJvm() throws Exception {
        String javaHome = System.getProperty("java.home");
        String javaBinary = javaHome + "/bin/java";
        String appClassesPath = CryptoCross.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String testClassesPath = CryptoCrossDictionaryPathProbe.class.getProtectionDomain().getCodeSource().getLocation().getPath();
        String classPath = appClassesPath + File.pathSeparator + testClassesPath;

        Process process = new ProcessBuilder(
                javaBinary,
                "-cp",
                classPath,
                "cryptocross.CryptoCrossDictionaryPathProbe")
                .start();

        String stdout = new String(process.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        String stderr = new String(process.getErrorStream().readAllBytes(), StandardCharsets.UTF_8);
        int exitCode = process.waitFor();
        return new ProbeResult(stdout, stderr, exitCode);
    }

    private static final class ProbeResult {
        private final String stdout;
        private final String stderr;
        private final int exitCode;

        private ProbeResult(String stdout, String stderr, int exitCode) {
            this.stdout = stdout;
            this.stderr = stderr;
            this.exitCode = exitCode;
        }
    }
}
