package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class FoundFile {

    private final String fileName;
    private final String typeName;

    public FoundFile(@NotNull String fileName, @NotNull String typeName) {
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
