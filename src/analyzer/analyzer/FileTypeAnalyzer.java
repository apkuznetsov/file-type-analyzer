package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import static analyzer.algs.KnuthMorrisPrattAlg.searchOccurrencesByKmpAlg;

public class FileTypeAnalyzer {

    public static List<FoundFile> analyze(@NotNull final String patternsFileName,
                                          @NotNull final String folderName) {

        final File[] files = new File(folderName).listFiles();
        final ExecutorService executor = Executors.newCachedThreadPool();
        final List<Future<FoundFile>> futures = new LinkedList<>();
        FileType[] fileTypesPrioritySorted = parseFileTypesPrioritySorted(patternsFileName);

        for (File file : files) {
            futures.add(
                    executor.submit(() -> findFileType(file, fileTypesPrioritySorted))
            );
        }

        List<FoundFile> result = new ArrayList<>(futures.size());
        for (Future<FoundFile> future : futures) {
            try {
                result.add(future.get());
            } catch (InterruptedException | ExecutionException exc) {
                exc.printStackTrace();
            }
        }

        executor.shutdown();
        return result;
    }

    private static FoundFile findFileType(@NotNull File file, @NotNull final FileType[] fileTypes) {
        FileType foundFileType = null;

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(file)
        )) {

            String fileContent = new String(input.readAllBytes());

            boolean isTypeFound;
            for (FileType type : fileTypes) {
                isTypeFound = searchOccurrencesByKmpAlg(fileContent, type.getPattern()).size() > 0;
                if (isTypeFound) {
                    foundFileType = type;
                    break;
                }
            }

        } catch (IOException exc) {
            foundFileType = null;
            exc.printStackTrace();
        }

        return new FoundFile(file.getName(), foundFileType);
    }

    private static FileType[] parseFileTypesPrioritySorted(@NotNull final String patternsFileName) {

        FileType[] fileTypes;

        try (InputStream input = new BufferedInputStream(
                new FileInputStream(patternsFileName)
        )) {

            final String[] fileTypesLines = new String(input.readAllBytes()).split("\n");
            Arrays.sort(fileTypesLines, Collections.reverseOrder());

            fileTypes = new FileType[fileTypesLines.length];
            for (int i = 0; i < fileTypes.length; i++) {
                fileTypes[i] = parseFileType(fileTypesLines[i]);
            }

        } catch (IOException exc) {
            fileTypes = null;
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
