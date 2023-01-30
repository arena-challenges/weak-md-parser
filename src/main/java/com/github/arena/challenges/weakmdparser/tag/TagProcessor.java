package com.github.arena.challenges.weakmdparser.tag;

import com.github.arena.challenges.weakmdparser.line.Line;

public abstract class TagProcessor {
    private static final String REGEX_FOR_DOUBLE_UNDERSCORE = "__(.+)__";
    private static final String REGEX_FOR_SINGLE_UNDERSCORE = "_(.+)_";
    private static final String TAG_EM = "<em>$1</em>";
    private static final String TAG_STRONG = "<strong>$1</strong>";

    protected final TagProcessor nextTag;

    protected TagProcessor(TagProcessor nextTag) {
        this.nextTag = nextTag;
    }

    public Line parseLine(String markdown, Boolean activeList) {
        if (shouldPassToNextProcessor(markdown)) return nextTag.parseLine(markdown, activeList);
        Line line = processMarkdown(markdown, activeList);
        line.setLineWithTags(openingTag(markdown) + line.getRawLine() + closingTag(markdown));
        return line;
    }

    protected String parseUnderscore(String markdown) {
        return markdown.replaceAll(REGEX_FOR_DOUBLE_UNDERSCORE, TAG_STRONG)
                .replaceAll(REGEX_FOR_SINGLE_UNDERSCORE, TAG_EM);
    }

    protected boolean shouldPassToNextProcessor(String markdown) {
        return nextTag != null && !shouldProcess(markdown);
    }

    protected abstract Line processMarkdown(String markdown, Boolean activeLine);

    protected abstract boolean shouldProcess(String markdown);

    protected abstract String openingTag(String markdown);

    protected abstract String closingTag(String markdown);
}
