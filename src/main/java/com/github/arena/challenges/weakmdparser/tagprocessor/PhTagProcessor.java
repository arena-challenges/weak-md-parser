package com.github.arena.challenges.weakmdparser.tagprocessor;

import com.github.arena.challenges.weakmdparser.line.AbstractLine;
import com.github.arena.challenges.weakmdparser.line.OtherLine;

import java.util.Arrays;

public class PhTagProcessor extends AbstractTagProcessor {
    private static final String OPENING_TAG = "<h";
    private static final String CLOSING_TAG = "</h";
    private static final String CLOSING_SYMBOL = ">";
    private static final char HASH_CHAR = '#';
    public static final String WORD_REGEX = "\\w";

    public PhTagProcessor(AbstractTagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected AbstractLine processMarkdown(String markdown) {
        final String result = markdown.substring(countFirstHashes(markdown) + 1);
        return new OtherLine(result);
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
        final String firstWord = Arrays.stream(markdown.split(WORD_REGEX)).findFirst().get().trim();
        final boolean notHashes = firstWord.codePoints().anyMatch(ch -> ch != HASH_CHAR);
        return notHashes ? 0 : firstWord.length();
    }

}
