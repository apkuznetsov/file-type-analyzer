package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class IsFileTypeFound {

    private final String fileName;
    private final String typeName;

    public IsFileTypeFound(@NotNull String fileName, @NotNull String typeName) {
        this.fileName = fileName;
        this.typeName = typeName;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTypeName() {
        return typeName;
    }
}
