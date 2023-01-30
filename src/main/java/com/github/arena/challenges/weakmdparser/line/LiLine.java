package com.github.arena.challenges.weakmdparser.line;

public class LiLine extends AbstractLine {

    public LiLine(String rawLine) {
        super(rawLine);
    }

    @Override
    public void processLine(String openingTag, String closingTag, boolean initialActive) {
        lineWithTags = openingTag + this.getRawLine() + closingTag;
        finalLine = initialActive ? lineWithTags : UL_OPENING_TAG + lineWithTags;
    }

    @Override
    public Boolean nextActiveListValue() {
        return true;
    }


}
