package cryptocross;

import java.lang.reflect.Field;

public final class CryptoCrossDictionaryPathProbe {
    private CryptoCrossDictionaryPathProbe() {
    }

    public static void main(String[] args) throws Exception {
        Field dictionaryPathField = CryptoCross.class.getDeclaredField("str_dictionaryPath");
        dictionaryPathField.setAccessible(true);
        Object value = dictionaryPathField.get(null);
        System.out.print(value == null ? "null" : value.toString());
    }
}
