package openjge;

import java.lang.reflect.Array;

public class ArrayUtil {
    private ArrayUtil() {}

    public static int indexOf(Object[] array, Object object) {
        for (int i = 0; i < array.length; i++)
            if (array[i] == object)
                return i;
        return -1;
    }

    public static <T> T[] remove(Class<T> tclass, T[] array, int... index) {
        T[] out = (T[]) Array.newInstance(tclass, array.length - 1);
        int skip = 0;
        for (int i = 0; i < out.length; i++) {
            if (i == index[skip]) {
                skip++;
                continue;
            }
            out[i + skip] = array[i];
        }

        return out;
    }
}
