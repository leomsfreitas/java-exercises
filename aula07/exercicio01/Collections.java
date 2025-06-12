package aula07.exercicio01;

import java.util.*;

public class Collections {
    public static void main(String[] args) {
        List<String> wordsList = new ArrayList<String>();
        Map<String, Integer> wordsMap= new HashMap<String, Integer>();

        Scanner sc = new Scanner(System.in);

        while (true) {
            String word = sc.nextLine();

            if (word.equalsIgnoreCase("sair")) break;

            if (word.isBlank()) continue;

            wordsList.add(word);
            wordsMap.put(word, wordsMap.getOrDefault(word, 0) + 1);
        }
        sc.close();

        Set<String> wordsSet = new TreeSet<>(wordsList);

        System.out.println("Todas palavras digitadas:");
        for (String word : wordsList) {
            System.out.println(word);
        }

        System.out.println("As palavras digitadas sem repetição e em ordem alfabética:");
        for (String word : wordsSet) {
            System.out.println(word);
        }

        System.out.println("A frequência de cada palavra digitada:");
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }

}
