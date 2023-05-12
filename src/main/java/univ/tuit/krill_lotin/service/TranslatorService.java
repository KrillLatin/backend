package univ.tuit.krill_lotin.service;

import org.springframework.stereotype.Service;
import univ.tuit.krill_lotin.dto.CodeNameDto;
import univ.tuit.krill_lotin.dto.ResponseText;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class TranslatorService {

    private static final List<CodeNameDto> latin;
    private static final List<CodeNameDto> cyrillic;
    static {
        latin = new ArrayList<>();
        latin.add(new CodeNameDto("O'", "Ў"));
        latin.add(new CodeNameDto("O’", "Ў"));
        latin.add(new CodeNameDto("O`", "Ў"));
        latin.add(new CodeNameDto("O‘", "Ў"));
        latin.add(new CodeNameDto("o'", "ў"));
        latin.add(new CodeNameDto("o’", "ў"));
        latin.add(new CodeNameDto("o‘", "ў"));
        latin.add(new CodeNameDto("o`", "ў"));

        latin.add(new CodeNameDto("G'", "Ғ"));
        latin.add(new CodeNameDto("G’", "Ғ"));
        latin.add(new CodeNameDto("G`", "Ғ"));
        latin.add(new CodeNameDto("G‘", "Ғ"));
        latin.add(new CodeNameDto("g`", "ғ"));
        latin.add(new CodeNameDto("g'", "ғ"));
        latin.add(new CodeNameDto("g’", "ғ"));
        latin.add(new CodeNameDto("g‘", "ғ"));

        latin.add(new CodeNameDto("'", "ъ"));
        latin.add(new CodeNameDto("’", "ъ"));
        latin.add(new CodeNameDto("`", "ъ"));

        latin.add(new CodeNameDto("SH", "Ш"));
        latin.add(new CodeNameDto("Sh", "Ш"));
        latin.add(new CodeNameDto("sH", "ш"));
        latin.add(new CodeNameDto("sh", "ш"));

        latin.add(new CodeNameDto("CH", "Ч"));
        latin.add(new CodeNameDto("Ch", "Ч"));
        latin.add(new CodeNameDto("ch", "ч"));
        latin.add(new CodeNameDto("cH", "ч"));

        latin.add(new CodeNameDto("Yo", "Ё"));
        latin.add(new CodeNameDto("YO", "Ё"));
        latin.add(new CodeNameDto("yo", "ё"));

        latin.add(new CodeNameDto("Yu", "Ю"));
        latin.add(new CodeNameDto("YU", "Ю"));
        latin.add(new CodeNameDto("yu", "ю"));

        latin.add(new CodeNameDto("YA", "Я"));
        latin.add(new CodeNameDto("Ya", "Я"));
        latin.add(new CodeNameDto("ya", "я"));

        latin.add(new CodeNameDto("E", "Э"));
        latin.add(new CodeNameDto("e", "е"));

        latin.add(new CodeNameDto("Ts", "Ц"));
        latin.add(new CodeNameDto("TS", "Ц"));
        latin.add(new CodeNameDto("ts", "ц"));

        latin.add(new CodeNameDto("A", "A"));
        latin.add(new CodeNameDto("a", "а"));

        latin.add(new CodeNameDto("B", "Б"));
        latin.add(new CodeNameDto("b", "б"));

        latin.add(new CodeNameDto("D", "Д"));
        latin.add(new CodeNameDto("d", "д"));

        latin.add(new CodeNameDto("E", "E"));
        latin.add(new CodeNameDto("e", "е"));

        latin.add(new CodeNameDto("F", "Ф"));
        latin.add(new CodeNameDto("f", "ф"));

        latin.add(new CodeNameDto("G", "Г"));
        latin.add(new CodeNameDto("g", "г"));

        latin.add(new CodeNameDto("H", "Ҳ"));
        latin.add(new CodeNameDto("h", "ҳ"));

        latin.add(new CodeNameDto("I", "И"));
        latin.add(new CodeNameDto("i", "и"));

        latin.add(new CodeNameDto("J", "Ж"));
        latin.add(new CodeNameDto("j", "ж"));

        latin.add(new CodeNameDto("K", "К"));
        latin.add(new CodeNameDto("k", "к"));

        latin.add(new CodeNameDto("L", "Л"));
        latin.add(new CodeNameDto("l", "л"));

        latin.add(new CodeNameDto("M", "M"));
        latin.add(new CodeNameDto("m", "м"));

        latin.add(new CodeNameDto("N", "Н"));
        latin.add(new CodeNameDto("n", "н"));

        latin.add(new CodeNameDto("O", "О"));
        latin.add(new CodeNameDto("o", "о"));

        latin.add(new CodeNameDto("P", "П"));
        latin.add(new CodeNameDto("p", "п"));

        latin.add(new CodeNameDto("Q", "Қ"));
        latin.add(new CodeNameDto("q", "қ"));

        latin.add(new CodeNameDto("R", "Р"));
        latin.add(new CodeNameDto("r", "р"));

        latin.add(new CodeNameDto("S", "С"));
        latin.add(new CodeNameDto("s", "с"));

        latin.add(new CodeNameDto("T", "Т"));
        latin.add(new CodeNameDto("t", "т"));

        latin.add(new CodeNameDto("U", "У"));
        latin.add(new CodeNameDto("u", "у"));

        latin.add(new CodeNameDto("V", "В"));
        latin.add(new CodeNameDto("v", "в"));

        latin.add(new CodeNameDto("X", "Х"));
        latin.add(new CodeNameDto("x", "х"));

        latin.add(new CodeNameDto("Y", "Й"));
        latin.add(new CodeNameDto("y", "й"));

        latin.add(new CodeNameDto("Z", "З"));
        latin.add(new CodeNameDto("z", "з"));


        cyrillic = new ArrayList<>();
        cyrillic.add(new CodeNameDto("ў", "o‘"));
        cyrillic.add(new CodeNameDto("ғ", "g‘"));
        cyrillic.add(new CodeNameDto("ъ", "'"));
        cyrillic.add(new CodeNameDto("Ъ", "'"));
        cyrillic.add(new CodeNameDto("ш", "sh"));
        cyrillic.add(new CodeNameDto("ч", "ch"));
        cyrillic.add(new CodeNameDto("ё", "yo"));
        cyrillic.add(new CodeNameDto("ю", "yu"));
        cyrillic.add(new CodeNameDto("я", "ya"));
        cyrillic.add(new CodeNameDto("э", "e"));
        cyrillic.add(new CodeNameDto("ц", "ts"));
        cyrillic.add(new CodeNameDto("ь", ""));

        cyrillic.add(new CodeNameDto("а", "a"));
        cyrillic.add(new CodeNameDto("б", "b"));
        cyrillic.add(new CodeNameDto("д", "d"));
        cyrillic.add(new CodeNameDto("е", "e"));
        cyrillic.add(new CodeNameDto("ф", "f"));
        cyrillic.add(new CodeNameDto("г", "g"));
        cyrillic.add(new CodeNameDto("ҳ", "h"));
        cyrillic.add(new CodeNameDto("и", "i"));
        cyrillic.add(new CodeNameDto("ж", "j"));
        cyrillic.add(new CodeNameDto("к", "k"));
        cyrillic.add(new CodeNameDto("л", "l"));
        cyrillic.add(new CodeNameDto("м", "m"));
        cyrillic.add(new CodeNameDto("н", "n"));
        cyrillic.add(new CodeNameDto("о", "o"));
        cyrillic.add(new CodeNameDto("п", "p"));
        cyrillic.add(new CodeNameDto("қ", "q"));
        cyrillic.add(new CodeNameDto("р", "r"));
        cyrillic.add(new CodeNameDto("с", "s"));
        cyrillic.add(new CodeNameDto("т", "t"));
        cyrillic.add(new CodeNameDto("у", "u"));
        cyrillic.add(new CodeNameDto("в", "v"));
        cyrillic.add(new CodeNameDto("х", "x"));
        cyrillic.add(new CodeNameDto("й", "y"));
        cyrillic.add(new CodeNameDto("з", "z"));

        cyrillic.add(new CodeNameDto("Ў", "O‘"));
        cyrillic.add(new CodeNameDto("Ғ", "G‘"));
        cyrillic.add(new CodeNameDto("Ш", "Sh"));
        cyrillic.add(new CodeNameDto("Ч", "Ch"));
        cyrillic.add(new CodeNameDto("Ё", "Yo"));
        cyrillic.add(new CodeNameDto("Ю", "Yu"));
        cyrillic.add(new CodeNameDto("Я", "Ya"));
        cyrillic.add(new CodeNameDto("Э", "E"));
        cyrillic.add(new CodeNameDto("Ц", "S"));

        cyrillic.add(new CodeNameDto("А", "A"));
        cyrillic.add(new CodeNameDto("Б", "B"));
        cyrillic.add(new CodeNameDto("Д", "D"));
        cyrillic.add(new CodeNameDto("Е", "E"));
        cyrillic.add(new CodeNameDto("Ф", "F"));
        cyrillic.add(new CodeNameDto("Г", "G"));
        cyrillic.add(new CodeNameDto("Ҳ", "H"));
        cyrillic.add(new CodeNameDto("И", "I"));
        cyrillic.add(new CodeNameDto("Ж", "J"));
        cyrillic.add(new CodeNameDto("К", "K"));
        cyrillic.add(new CodeNameDto("Л", "L"));
        cyrillic.add(new CodeNameDto("М", "M"));
        cyrillic.add(new CodeNameDto("Н", "N"));
        cyrillic.add(new CodeNameDto("О", "O"));
        cyrillic.add(new CodeNameDto("П", "P"));
        cyrillic.add(new CodeNameDto("Қ", "Q"));
        cyrillic.add(new CodeNameDto("Р", "R"));
        cyrillic.add(new CodeNameDto("С", "S"));
        cyrillic.add(new CodeNameDto("Т", "T"));
        cyrillic.add(new CodeNameDto("У", "U"));
        cyrillic.add(new CodeNameDto("В", "V"));
        cyrillic.add(new CodeNameDto("Х", "X"));
        cyrillic.add(new CodeNameDto("Й", "Y"));
        cyrillic.add(new CodeNameDto("З", "Z"));
    }

    public ResponseText transliterateToLatin(String text) {
        return getResponseText(text, cyrillic);
    }

    public ResponseText transliterateToCyrillic(String text) {
        return getResponseText(text, latin);
    }

    private ResponseText getResponseText(String text, List<CodeNameDto> letters) {
        String trans = "";
        if (text != null) {
            trans = text;
            for (CodeNameDto dto : letters) {
                trans = trans.replaceAll(dto.getCode(), dto.getName());
            }
        }
        ResponseText rt = new ResponseText();
        rt.setMessage(trans);
        return rt;
    }
}
