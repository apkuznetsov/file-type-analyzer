package analyzer;

import analyzer.analyzer.FoundFile;

import java.util.List;

import static analyzer.analyzer.FileTypeAnalyzer.analyze;

public class Main {

    public static void main(String[] args) {
        if (args.length < 2) {
            System.exit(0);
        }

        final String patternsFileName = args[0];
        final String folderName = args[1];

        List<FoundFile> results = analyze(patternsFileName, folderName);

        for (FoundFile r : results) {
            System.out.printf("%s: %s\n",
                    r.getFileName(),
                    r.getTypeName().equals("") ? "Unknown file type" : r.getTypeName()
            );
        }
    }
}
