package by.training.task3.parser;

import by.training.task3.entity.Lexeme;
import by.training.task3.pattern.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Parses sentence into lexemes
 */
public final class SentenceParser {

    private static SentenceParser instance = null;
    private static final String DELIMITERS = " ";

    private SentenceParser() {
    }

    public static SentenceParser getInstance() {
        if (instance == null) {
            instance = new SentenceParser();
        }
        return instance;
    }

    public List<Component> parse(String sentence) {
        String trimmedString = sentence.trim();
        ArrayList<Component> lexemeArrayList = new ArrayList<>();
        String[] lexemeArray = trimmedString.split(DELIMITERS);
        for (String i : lexemeArray) {
            if(!i.contains("\n")) {
                lexemeArrayList.add(new Lexeme(i));
            }
        }
        return lexemeArrayList;

    }


}
