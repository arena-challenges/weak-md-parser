package com.github.arena.challenges.weakmdparser.tag;

import com.github.arena.challenges.weakmdparser.line.Line;
import com.github.arena.challenges.weakmdparser.line.OtherLine;

public class PhTagProcessor extends TagProcessor {
    public static final String OPENING_TAG = "<h";
    public static final String CLOSING_TAG = "</h";
    public static final String CLOSING_SYMBOL = ">";


    public PhTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected Line processMarkdown(String markdown, Boolean activeList) {
        String result = markdown.substring(countFirstHashes(markdown) + 1);
        return new OtherLine(activeList, result);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        return countFirstHashes(markdown) != 0;
    }

    @Override
    protected String openingTag(String markdown) {
        return OPENING_TAG + countFirstHashes(markdown) + CLOSING_SYMBOL;
    }

    @Override
    protected String closingTag(String markdown) {
        return CLOSING_TAG + countFirstHashes(markdown) + CLOSING_SYMBOL;
    }

    private int countFirstHashes(String markdown) {
        int count = 0;
        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++) {
            count++;
        }
        return count;
    }

}
