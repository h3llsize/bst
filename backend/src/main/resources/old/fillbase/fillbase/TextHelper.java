package old.fillbase.fillbase;

import java.text.Normalizer;
import java.util.Locale;
import java.util.regex.Pattern;

public class TextHelper {
    public static String convertToSlug(String inputText) {
        // Замена русских букв на латиницу
        String latinText = inputText.toLowerCase(Locale.ROOT)
                .replaceAll("а", "a").replaceAll("б", "b").replaceAll("в", "v")
                .replaceAll("г", "g").replaceAll("д", "d").replaceAll("е", "e")
                .replaceAll("ё", "e").replaceAll("ж", "zh").replaceAll("з", "z")
                .replaceAll("и", "i").replaceAll("й", "y").replaceAll("к", "k")
                .replaceAll("л", "l").replaceAll("м", "m").replaceAll("н", "n")
                .replaceAll("о", "o").replaceAll("п", "p").replaceAll("р", "r")
                .replaceAll("с", "s").replaceAll("т", "t").replaceAll("у", "u")
                .replaceAll("ф", "f").replaceAll("х", "kh").replaceAll("ц", "ts")
                .replaceAll("ч", "ch").replaceAll("ш", "sh").replaceAll("щ", "sch")
                .replaceAll("ъ", "").replaceAll("ы", "y").replaceAll("ь", "")
                .replaceAll("э", "e").replaceAll("ю", "yu").replaceAll("я", "ya");

        // Замена пробелов на "-"
        String slug = latinText.replaceAll(" ", "-").replaceAll("[^a-zA-Z0-9-]", "");

        return slug;
    }
}
