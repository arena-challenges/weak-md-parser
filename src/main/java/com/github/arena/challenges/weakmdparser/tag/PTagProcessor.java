package com.github.arena.challenges.weakmdparser.tag;

import com.github.arena.challenges.weakmdparser.line.Line;
import com.github.arena.challenges.weakmdparser.line.OtherLine;

public class PTagProcessor extends TagProcessor {

    public static final String OPENING_TAG = "<p>";
    public static final String CLOSING_TAG = "</p>";

    public PTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected Line processMarkdown(String markdown, Boolean activeList) {
        String result = parseUnderscore(markdown);
        return new OtherLine(activeList, result);
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
