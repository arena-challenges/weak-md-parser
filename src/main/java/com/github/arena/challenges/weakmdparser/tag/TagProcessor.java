package com.github.arena.challenges.weakmdparser.tag;

public abstract class TagProcessor {
    private static final String REGEX_FOR_DOUBLE_UNDERSCORE = "__(.+)__";
    private static final String REGEX_FOR_SINGLE_UNDERSCORE = "_(.+)_";
    private static final String TAG_EM = "<em>$1</em>";
    private static final String TAG_STRONG = "<strong>$1</strong>";

    protected final TagProcessor nextTag;

    protected TagProcessor(TagProcessor nextTag) {
        this.nextTag = nextTag;
    }

    public String parse(String markdown) {
        if (shouldPassToNextTag(markdown)) {
            return nextTag.parse(markdown);
        }
        return openingTag() + processMarkdown(markdown) + closingTag();
    }

    protected abstract String processMarkdown(String markdown);

    protected String parseUnderscore(String markdown) {
        String workingOn = replaceRegexWithTag(markdown, REGEX_FOR_DOUBLE_UNDERSCORE, TAG_STRONG);
        return replaceRegexWithTag(workingOn, REGEX_FOR_SINGLE_UNDERSCORE, TAG_EM);
    }

    protected String replaceRegexWithTag(String markdown, String regex, String tag) {
        return markdown.replaceAll(regex, tag);
    }

    protected boolean shouldPassToNextTag(String markdown) {
        return nextTag != null && !shouldProcess(markdown);
    }

    protected abstract boolean shouldProcess(String markdown);

    protected abstract String openingTag();

    protected abstract String closingTag();
}
