package analyzer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        if (args.length < 3) {
            System.out.println("Please provide three arguments:\n" +
                    "the file to check (relative path),\n" +
                    "the pattern string (P),\n" +
                    "and the result string (R)");
            System.exit(0);
        }

        String fileName = args[0];
        String pattern = args[1];
        String returnType = args[2];

        try (InputStream input = new BufferedInputStream(new FileInputStream(fileName))) {
            String fileContent = new String(input.readAllBytes());

            if (fileContent.contains(pattern)) {
                System.out.println(returnType);
            } else {
                System.out.println("Unknown file type");
            }
        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
