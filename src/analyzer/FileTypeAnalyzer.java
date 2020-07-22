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

            if (algName.equals("--naive")) {
                isTypeFound = fileContent.contains(pattern);
            } else if (algName.equals("--KMP")) {
                isTypeFound = KnuthMorrisPrattAlg.searchOccurrencesByKmpAlg(fileContent, pattern).size() > 0;
            }

            if (isTypeFound) {
                System.out.println(returnType);
            } else {
                System.out.println("Unknown file type");
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
