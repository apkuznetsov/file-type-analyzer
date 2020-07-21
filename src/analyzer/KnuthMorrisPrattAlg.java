package analyzer;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class KnuthMorrisPrattAlg {

    public static @NotNull
    List<Integer> searchOccurrencesByKmpAlg(@NotNull String text, @NotNull String pattern) {

        int[] prefixFunc = calcPrefixFunc(pattern);
        ArrayList<Integer> occurrences = new ArrayList<Integer>();

        int patternIndex = 0;
        for (int textIndex = 0; textIndex < text.length(); textIndex++) {

            while (patternIndex > 0 && text.charAt(textIndex) != pattern.charAt(patternIndex)) {
                patternIndex = prefixFunc[patternIndex - 1];
            }

            if (text.charAt(textIndex) == pattern.charAt(patternIndex)) {
                patternIndex += 1;
            }

            if (patternIndex == pattern.length()) {
                occurrences.add(textIndex - patternIndex + 1);
                patternIndex = prefixFunc[patternIndex - 1];
            }
        }

        return occurrences;
    }

    public static int[] calcPrefixFunc(@NotNull String str) {

        int[] prefixFunc = new int[str.length()];

        for (int strIndex = 1; strIndex < str.length(); strIndex++) {

            int j = prefixFunc[strIndex - 1];

            while (j > 0 && str.charAt(strIndex) != str.charAt(j)) {
                j = prefixFunc[j - 1];
            }

            if (str.charAt(strIndex) == str.charAt(j)) {
                j += 1;
            }

            prefixFunc[strIndex] = j;
        }

        return prefixFunc;
    }

}