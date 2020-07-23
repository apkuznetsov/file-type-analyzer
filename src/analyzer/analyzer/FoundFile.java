package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class FoundFile {

    private final String fileName;
    private final FileType fileType;

    public FoundFile(@NotNull String fileName, FileType fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getTypeName() {
        if (fileType == null) {
            return null;
        }

        return fileType.getTypeName();
    }
}
