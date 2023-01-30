package com.github.arena.challenges.weakmdparser.line;

public class OtherLine extends AbstractLine {
    public OtherLine(String rawLine) {
        super(rawLine);
    }

    @Override
    public void processLine(String openingTag, String closingTag, boolean initialActive) {
        lineWithTags = openingTag + this.getRawLine() + closingTag;
        finalLine = initialActive ? UL_CLOSING_TAG + lineWithTags : lineWithTags;
    }

    @Override
    public Boolean nextActiveListValue() {
        return false;
    }
}
