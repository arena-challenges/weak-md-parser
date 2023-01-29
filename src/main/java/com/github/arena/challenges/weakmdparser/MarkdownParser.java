package com.github.arena.challenges.weakmdparser;

import com.github.arena.challenges.weakmdparser.tag.LiTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.PTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.PhTagProcessor;
import com.github.arena.challenges.weakmdparser.tag.TagProcessor;

public class MarkdownParser {

    public String parse(String markdown) {
        String[] lines = markdown.split("\n");
        StringBuilder result = new StringBuilder();
        boolean activeList = false;

        for (String line : lines) {
            TagProcessor tag = new PhTagProcessor(new LiTagProcessor(new PTagProcessor(null)));

            String theLine = tag.parse(line);


            if (theLine.matches("(<li>).*") && !theLine.matches("(<h).*") && !theLine.matches("(<p>).*") && !activeList) {
                activeList = true;
                result.append("<ul>");
                result.append(theLine);
            } else if (!theLine.matches("(<li>).*") && activeList) {
                activeList = false;
                result.append("</ul>");
                result.append(theLine);
            } else {
                result.append(theLine);
            }
        }

        if (activeList) {
            result.append("</ul>");
        }

        return result.toString();
    }

}
