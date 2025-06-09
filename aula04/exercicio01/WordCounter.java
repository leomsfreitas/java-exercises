package aula04.exercicio01;

public class WordCounter {
    public int countSubstring(String string, String word) {
        if (string == null || word == null) return 0;

        String[] words = string.split(" ");

        int acc = 0;
        for (String w : words) {
            if (w.equals(word)) acc++;
        }
        return acc;
    }
}
