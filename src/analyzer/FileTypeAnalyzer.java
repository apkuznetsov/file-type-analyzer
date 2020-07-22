package analyzer;

import org.jetbrains.annotations.NotNull;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileTypeAnalyzer {

    public static boolean isTypeFound(@NotNull final String algName,
                                      @NotNull final String fileName,
                                      @NotNull final String pattern) {

        boolean isTypeFound = false;

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(fileName)
        )) {

            String fileContent = new String(input.readAllBytes());

            if (algName.equals("--naive")) {
                isTypeFound = fileContent.contains(pattern);
            } else if (algName.equals("--KMP")) {
                isTypeFound = KnuthMorrisPrattAlg.searchOccurrencesByKmpAlg(fileContent, pattern).size() > 0;
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return isTypeFound;
    }
}
