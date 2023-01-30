package com.github.arena.challenges.weakmdparser.tagprocessor;

import com.github.arena.challenges.weakmdparser.line.AbstractLine;

public abstract class AbstractTagProcessor {
    private static final String DOUBLE_UNDERSCORE_REGEX = "__(.+)__";
    private static final String SINGLE_UNDERSCORE_REGEX = "_(.+)_";
    private static final String TAG_EM = "<em>$1</em>";
    private static final String TAG_STRONG = "<strong>$1</strong>";
    private final AbstractTagProcessor nextTag;

    /**
     * Parse a string line based on special characters included in it.
     *
     * @param markdown raw line
     * @return Line with tagged string value and active list attribute
     */
    public AbstractLine parseLine(String markdown, boolean initialActive) {
        return shouldPassToNextProcessor(markdown) ? nextTag.parseLine(markdown, initialActive) : processLine(markdown, initialActive);
    }

    private AbstractLine processLine(String markdown, boolean initialActive) {
        final AbstractLine line = processMarkdown(markdown);
        line.processLine(openingTag(markdown), closingTag(markdown), initialActive);
        return line;
    }

    protected AbstractTagProcessor(AbstractTagProcessor nextTag) {
        this.nextTag = nextTag;
    }

    protected String parseUnderscore(String markdown) {
        return markdown.replaceAll(DOUBLE_UNDERSCORE_REGEX, TAG_STRONG)
                .replaceAll(SINGLE_UNDERSCORE_REGEX, TAG_EM);
    }

    protected boolean shouldPassToNextProcessor(String markdown) {
        return nextTag != null && !shouldProcess(markdown);
    }

    protected abstract AbstractLine processMarkdown(String markdown);

    protected abstract boolean shouldProcess(String markdown);

    protected abstract String openingTag(String markdown);

    protected abstract String closingTag(String markdown);
}
