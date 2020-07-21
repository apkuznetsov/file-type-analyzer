package analyzer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Main {

    public static void main(String[] args) {
        if (args.length < 4) {
            System.out.println("Please provide four arguments:\n" +
                    "the algorithm,\n" +
                    "the file to check (relative path),\n" +
                    "the pattern string (P),\n" +
                    "and the result string (R)");
            System.exit(0);
        }

        String algName = args[0];
        String fileName = args[1];
        String pattern = args[2];
        String returnType = args[3];

        try (InputStream input = new BufferedInputStream(new FileInputStream(fileName))) {
            String fileContent = new String(input.readAllBytes());
            boolean isTypeFound = false;

            long startTime = System.nanoTime();
            if (algName.equals("--naive")) {
                isTypeFound = fileContent.contains(pattern);
            } else if (algName.equals("--KMP")) {
                isTypeFound = KnuthMorrisPrattAlg.searchOccurrencesByKmpAlg(fileContent, pattern).size() > 0;
            }
            long elapsedNanos = System.nanoTime() - startTime;


            if (isTypeFound) {
                System.out.println(returnType);
            } else {
                System.out.println("Unknown file type");
            }
            double elapsedTimeInSecond = (double) elapsedNanos / 1_000_000_000;
            System.out.println("It took " + elapsedTimeInSecond + " seconds");

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
