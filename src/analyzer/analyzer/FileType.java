package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class FileType {

    private final int priority;
    private final String pattern;
    private final String typeName;

    public FileType(int priority, @NotNull String pattern, @NotNull String typeName) {
        this.priority = priority;
        this.pattern = pattern;
        this.typeName = typeName;
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getTypeName() {
        return typeName;
    }
}
