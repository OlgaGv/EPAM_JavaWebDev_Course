package by.training.task3.parser;

import by.training.task3.entity.Sentence;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses paragraph string into sentences
 */
public final class ParagraphParser {

    private static ParagraphParser instance = null;
    private static final String DELIMITERS = "(?<=\\?)|(?<=\\!)|(?<=\\n)|(?<=(\\.\\.\\.|\\.))";

    private ParagraphParser() {
    }

    public static ParagraphParser getInstance() {
        if (instance == null) {
            instance = new ParagraphParser();
        }
        return instance;
    }

    public List<Component> parse(String str) {
        ArrayList<Component> sentenceArrayList = new ArrayList<>();
        String[] sentenceArray = str.split(DELIMITERS);
        for (String i : sentenceArray) {
            if(!i.contains("\n")) {
                i = i.trim();
                sentenceArrayList.add(new Sentence(i));
            }
        }

        return sentenceArrayList;

    }

}
