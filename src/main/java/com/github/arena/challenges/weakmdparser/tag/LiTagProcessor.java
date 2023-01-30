package com.github.arena.challenges.weakmdparser.tag;

import com.github.arena.challenges.weakmdparser.line.LiLine;
import com.github.arena.challenges.weakmdparser.line.Line;

public class LiTagProcessor extends TagProcessor {

    public static final String OPENING_TAG = "<li>";
    public static final String CLOSING_TAG = "</li>";

    public LiTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected Line processMarkdown(String markdown, Boolean activeList) {
        String skipAsterisk = markdown.substring(2);
        String result = parseUnderscore(skipAsterisk);
        return new LiLine(activeList, result);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return markdown.startsWith("*");
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
