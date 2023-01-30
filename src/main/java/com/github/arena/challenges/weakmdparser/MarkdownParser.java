package com.github.arena.challenges.weakmdparser;

import com.github.arena.challenges.weakmdparser.line.AbstractLine;
import com.github.arena.challenges.weakmdparser.tagprocessor.ProcessorFactory;
import com.github.arena.challenges.weakmdparser.tagprocessor.AbstractTagProcessor;

public final class MarkdownParser {

    private final AbstractTagProcessor tagProcessor = ProcessorFactory.getDefaultProcessor();

    private static final String NEW_LINE_REGEX = "\n";

    /**
     * Parse text changing special characters to html tags.
     *
     * @param markdown initial
     * @return text in html format
     */
    public String parse(String markdown) {
        final String[] lines = markdown.split(NEW_LINE_REGEX);
        final StringBuilder result = new StringBuilder();
        boolean activeList = false;

        for (final String line : lines) {
            activeList = processLine(result, activeList, tagProcessor, line);
        }

        return activeList ? result.append(AbstractLine.UL_CLOSING_TAG).toString() : result.toString();
    }

    private boolean processLine(StringBuilder result, boolean activeList, AbstractTagProcessor tagProcessor, String line) {
        final AbstractLine resultLine = tagProcessor.parseLine(line, activeList);

        result.append(resultLine.getFinalLine());

        return resultLine.nextActiveListValue();
    }

}
