package openjge.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class FileUtil {
    private FileUtil() {}

    public static String readFile(String path) {
        StringBuilder text = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(FileUtil.class.getResourceAsStream(path)))) {
            String line = "";
            while ((line = br.readLine()) != null) {
                text.append(line).append("\n");
            }
        } catch(Exception e) {
            System.err.println("Error reading file '" + path + "': " + e);
        }

        return text.toString();
    }
}
