package com.github.arena.challenges.weakmdparser.line;

import lombok.Getter;

public abstract class AbstractLine {
    public static final String UL_CLOSING_TAG = "</ul>";
    protected static final String UL_OPENING_TAG = "<ul>";

    @Getter
    private final String rawLine;
    protected String lineWithTags;
    @Getter
    protected String finalLine;

    protected AbstractLine(String rawLine) {
        this.rawLine = rawLine;
    }

    /**
     * Wrap with 'ul' tags if necessary based on active value and Line type combination
     */

    public abstract void processLine(String openingTag, String closingTag, boolean initialActive);

    public abstract Boolean nextActiveListValue();

}
