package com.github.arena.challenges.weakmdparser.line;

public class OtherLine extends Line {
    public OtherLine(Boolean active, String rawLine) {
        super(active, rawLine);
    }

    @Override
    public String processLine() {
        if (!getActive()) return getLineWithTags();

        this.setActive(false);
        return UL_CLOSING_TAG + getLineWithTags();
    }
}
