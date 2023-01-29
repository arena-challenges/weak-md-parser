package com.github.arena.challenges.weakmdparser.tag;

public class PhTagProcessor extends TagProcessor {
    public static final String OPENING_TAG = "<h";
    public static final String CLOSING_TAG = "</h";
    public static final String CLOSING_SYMBOL = ">";
    private Integer count = 0;

    public PhTagProcessor(TagProcessor nextTag) {
        super(nextTag);
    }

    @Override
    protected String processMarkdown(String markdown) {
        return markdown.substring(count + 1);
    }

    @Override
    protected boolean shouldProcess(String markdown) {
        calculateFirstHashesNumber(markdown);
        return count != 0;
    }

    @Override
    protected String openingTag() {
        return OPENING_TAG + count + CLOSING_SYMBOL;
    }

    @Override
    protected String closingTag() {
        return CLOSING_TAG + count + CLOSING_SYMBOL;
    }

    private void calculateFirstHashesNumber(String markdown) {
        for (int i = 0; i < markdown.length() && markdown.charAt(i) == '#'; i++) {
            count++;
        }
    }

}
