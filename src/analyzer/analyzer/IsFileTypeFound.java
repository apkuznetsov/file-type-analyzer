package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class IsFileTypeFound {

    private final String fileName;
    private final boolean isTypeFound;

    public IsFileTypeFound(@NotNull String fileName, boolean isTypeFound) {
        this.fileName = fileName;
        this.isTypeFound = isTypeFound;
    }

    public String getFileName() {
        return fileName;
    }

    public boolean isTypeFound() {
        return isTypeFound;
    }
}
