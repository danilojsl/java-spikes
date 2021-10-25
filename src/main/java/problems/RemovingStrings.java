package problems;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RemovingStrings {

    static String removeChars(String string1, String string2) {

        List<Character> characters1 = new ArrayList<>();

        for(char c : string1.toCharArray()) {
            characters1.add(c);
        }

        List<Character> characters2 = new ArrayList<>();

        for(char c : string2.toCharArray()) {
            characters2.add(c);
        }

        List<Character> result = characters1.stream()
                .filter(c -> !characters2.contains(c))
                .collect(Collectors.toList());

        StringBuilder sb = new StringBuilder();
        for (Character ch: result) {
            sb.append(ch);
        }

        return sb.toString();
    }

}
