package analyzer;

import org.jetbrains.annotations.NotNull;

public class FileNameAndType {

    private final String fileName;
    private final String fileType;

    public FileNameAndType(@NotNull String fileName, @NotNull String fileType) {
        this.fileName = fileName;
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getFileType() {
        return fileType;
    }
}
