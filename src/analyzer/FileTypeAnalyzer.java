package analyzer;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileTypeAnalyzer {

    public static void analyze(@NotNull String algName, @NotNull String fileName,
                               @NotNull String pattern, @NotNull String returnType) {

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(fileName)
        )) {

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
