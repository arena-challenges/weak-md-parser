package com.github.arena.challenges.weakmdparser.tagprocessor;

import com.github.arena.challenges.weakmdparser.line.AbstractLine;
import com.github.arena.challenges.weakmdparser.line.OtherLine;

public class PTagProcessor extends AbstractTagProcessor {

    public static final String OPENING_TAG = "<p>";
    public static final String CLOSING_TAG = "</p>";

    public PTagProcessor(AbstractTagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected AbstractLine processMarkdown(String markdown) {
        final String result = parseUnderscore(markdown);
        return new OtherLine(result);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return true;
    }

    @Override
    protected String openingTag(String markdown) {
        return OPENING_TAG;
    }

    @Override
    protected String closingTag(String markdown) {
        return CLOSING_TAG;
    }


}
