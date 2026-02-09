# cryptocross
A Java implementation of a word game where a user forms words from letters on a board and is scored based on the words sum of the individual letters values

## Requirements
- Java 17 or higher (OpenJDK recommended)

## Building and Running
```bash
cd CryptoCross
ant clean jar
java -jar dist/CryptoCross.jar
```

## Testing
```bash
cd CryptoCross
ant compile-test
java -jar lib/junit-platform-console-standalone-1.10.1.jar --class-path build/classes:build/test/classes --scan-class-path
```
