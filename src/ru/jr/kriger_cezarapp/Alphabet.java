package ru.jr.kriger_cezarapp;

import java.util.ArrayList;
import java.util.List;

public class Alphabet {
    private static final char [] RU_ALPHABET= {'а', 'б', 'в', 'г', 'д', 'е', 'ж', 'з',
            'и','к', 'л', 'м', 'н', 'о', 'п', 'р', 'с', 'т', 'у', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'ъ', 'ы', 'ь', 'э', 'я', '.', ',', '«', '»', '"', '\'', ':', '!', '?', ' '};

    private Alphabet() {
    }

    public static List<Character> getAlphabet(){
        List<Character> chars = new ArrayList<>();
        for (char c : RU_ALPHABET) {
            chars.add(c);
        }
        return chars;

    }

    public static int getAlphabetCapacity(){
        return RU_ALPHABET.length;
    }
}
