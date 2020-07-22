package analyzer;

import java.util.List;

import static analyzer.FileTypeAnalyzer.isTypeFound;

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

        final String algName = "--KMP";
        final String folderName = args[0];
        final String pattern = args[1];
        final String returnType = args[2];

        List<IsFileTypeFound> results = isTypeFound(algName, folderName, pattern);

        for (IsFileTypeFound r : results) {
            System.out.printf("%s: %s",
                    r.getFileName(),
                    r.isTypeFound() ? returnType : "Unknown file type");
        }
    }
}
