package com.github.arena.challenges.weakmdparser.tagprocessor;

import com.github.arena.challenges.weakmdparser.line.LiLine;
import com.github.arena.challenges.weakmdparser.line.AbstractLine;

public class LiTagProcessor extends AbstractTagProcessor {

    private static final String OPENING_TAG = "<li>";
    private static final String CLOSING_TAG = "</li>";
    private static final String PREFIX_ASTERISK = "*";

    public LiTagProcessor(AbstractTagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected AbstractLine processMarkdown(String markdown) {
        final String skipAsterisk = markdown.substring(2);
        final String result = parseUnderscore(skipAsterisk);
        return new LiLine(result);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return markdown.startsWith(PREFIX_ASTERISK);
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
