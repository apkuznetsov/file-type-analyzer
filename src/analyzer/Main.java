package analyzer;

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

        String algName = "--KMP";
        String fileName = args[0];
        String pattern = args[1];
        String returnType = args[2];

        FileTypeAnalyzer.analyze(algName, fileName, pattern, returnType);
    }
}
