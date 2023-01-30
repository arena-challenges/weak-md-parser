package com.github.arena.challenges.weakmdparser;

import com.github.arena.challenges.weakmdparser.line.Line;
import com.github.arena.challenges.weakmdparser.tag.LiTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.PTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.PhTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.TagProcessor;

public class MarkdownParser {

    public String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean activeList = false;
        TagProcessor tagProcessor = new PhTagProcessor(new LiTagProcessor(new PTagProcessor(null)));

        for (String line : lines) {
            Line resultLine = tagProcessor.parseLine(line, activeList);
            String processedLine = resultLine.processLine();
            activeList = resultLine.getActive();
            result.append(processedLine);
        }

        if (activeList) {
            result.append("</ul>");
        }

        return result.toString();
    }

}
