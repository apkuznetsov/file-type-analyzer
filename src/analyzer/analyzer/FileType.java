package analyzer.analyzer;

import org.jetbrains.annotations.NotNull;

public class FileType {

    private final int priority;
    private final String pattern;
    private final String type;

    public FileType(int priority, @NotNull String pattern, @NotNull String type) {
        this.priority = priority;
        this.pattern = pattern;
        this.type = type;
    }

    public int getPriority() {
        return priority;
    }

    public String getPattern() {
        return pattern;
    }

    public String getType() {
        return type;
    }
}
