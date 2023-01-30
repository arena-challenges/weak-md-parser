package com.github.arena.challenges.weakmdparser.line;

import lombok.Getter;
import lombok.Setter;

public abstract class Line {
    protected static final String UL_CLOSING_TAG = "</ul>";
    protected static final String UL_OPENING_TAG = "<ul>";
    @Getter
    @Setter
    private Boolean active;
    @Getter
    private final String rawLine;
    @Getter
    @Setter
    private String lineWithTags;

    protected Line(Boolean active, String rawLine) {
        this.active = active;
        this.rawLine = rawLine;
    }

    public abstract String processLine();
}
