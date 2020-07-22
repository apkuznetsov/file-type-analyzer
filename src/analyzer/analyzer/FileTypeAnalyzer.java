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

    public static void parseFileTypes(@NotNull final String patternsFileName) {

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(patternsFileName)
        )) {

            String[] fileTypesLines = new String(input.readAllBytes()).split("\n");
            Arrays.sort(fileTypesLines, Collections.reverseOrder());

        } catch (IOException exc) {
            exc.printStackTrace();
        }
    }
}
