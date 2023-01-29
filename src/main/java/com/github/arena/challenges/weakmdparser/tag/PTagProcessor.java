package com.github.arena.challenges.weakmdparser.tag;

public class PTagProcessor extends TagProcessor {

    public static final String OPENING_TAG = "<p>";
    public static final String CLOSING_TAG = "</p>";

    public PTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected String processMarkdown(String markdown) {
        return parseUnderscore(markdown);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return true;
    }

    @Override
    protected String openingTag() {
        return OPENING_TAG;
    }

    @Override
    protected String closingTag() {
        return CLOSING_TAG;
    }


}
