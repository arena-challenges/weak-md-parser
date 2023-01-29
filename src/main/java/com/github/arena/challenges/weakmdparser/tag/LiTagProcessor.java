package com.github.arena.challenges.weakmdparser.tag;

public class LiTagProcessor extends TagProcessor {

    public static final String OPENING_TAG = "<li>";
    public static final String CLOSING_TAG = "</li>";

    public LiTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected String processMarkdown(String markdown) {
        String skipAsterisk = markdown.substring(2);
        return parseUnderscore(skipAsterisk);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return markdown.startsWith("*");
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
