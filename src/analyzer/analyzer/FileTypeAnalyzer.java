package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static analyzer.algs.KnuthMorrisPrattAlg.searchOccurrencesByKmpAlg;

public class FileTypeAnalyzer {

    public static List<IsFileTypeFound> isTypeFound(@NotNull final String folderName,
                                                    @NotNull final String pattern) {

        final ExecutorService executor = Executors.newCachedThreadPool();
        final File[] files = new File(folderName).listFiles();
        final List<Future<IsFileTypeFound>> futures = new LinkedList<>();

        for (File file : files) {
            futures.add(
                    executor.submit(() -> {
                        boolean isTypeFound = false;

                        try (InputStream input = new BufferedInputStream(
                                new FileInputStream(file)
                        )) {

                            String fileContent = new String(input.readAllBytes());

                            isTypeFound = searchOccurrencesByKmpAlg(fileContent, pattern).size() > 0;

                        } catch (IOException exc) {
                            exc.printStackTrace();
                        }

                        return new IsFileTypeFound(file.getName(), isTypeFound);
                    })
            );
        }

        List<IsFileTypeFound> result = new ArrayList<>(futures.size());
        for (Future<IsFileTypeFound> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException exc) {
                exc.printStackTrace();
            }
        }

        executor.shutdown();
        return result;
    }

    public static FileType[] parseFileTypes(@NotNull final String patternsFileName) {

        FileType[] fileTypes = null;

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(patternsFileName)
        )) {

            final String[] fileTypesLines = new String(input.readAllBytes()).split("\n");
            Arrays.sort(fileTypesLines, Collections.reverseOrder());

            fileTypes = new FileType[fileTypesLines.length];
            for(int i = 0; i < fileTypes.length; i++) {
                fileTypes[i] = parseFileType(fileTypesLines[i]);
            }

        } catch (IOException exc) {
            exc.printStackTrace();
        }

        return fileTypes;
    }

    private static FileType parseFileType(@NotNull final String line) {
        final String[] fileTypeFields = line.split(";");
        fileTypeFields[1] = fileTypeFields[1].substring(1, fileTypeFields[1].length() - 1);
        fileTypeFields[2] = fileTypeFields[2].substring(1, fileTypeFields[2].length() - 1);

        return new FileType(Integer.parseInt(fileTypeFields[0]), fileTypeFields[1], fileTypeFields[2]);
    }
}
