package com.github.arena.challenges.weakmdparser.line;

public class LiLine extends Line {

    public LiLine(Boolean active, String rawLine) {
        super(active, rawLine);
    }

    @Override
    public String processLine() {
        if (getActive()) return getLineWithTags();

        this.setActive(true);
        return UL_OPENING_TAG + getLineWithTags();
    }
}
