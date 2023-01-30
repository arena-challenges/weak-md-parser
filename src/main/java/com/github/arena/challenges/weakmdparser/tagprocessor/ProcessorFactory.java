package com.github.arena.challenges.weakmdparser.tagprocessor;

public final class ProcessorFactory {
    private ProcessorFactory() {
    }

    /**
     * @return default chain of tag processors
     */
    public static AbstractTagProcessor getDefaultProcessor() {
        return new PhTagProcessor(new LiTagProcessor(new PTagProcessor(null)));
    }

}
