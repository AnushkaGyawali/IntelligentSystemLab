/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package languagemodeling;

/**
 *
 * @author Dell
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LanguageModeling {

    private HashMap<String, Integer> wordMap = new HashMap<>();

    public void readFile(String file) {
        String word = "";
        Scanner sc;
        try {
            sc = new Scanner(new File(file));
            sc.useDelimiter("[\\s,;:.?!()]"); // Split on whitespace and common punctuation
            while (sc.hasNext()) {
                word = sc.next().toLowerCase(); // Convert words to lowercase
                // Store words as keys and frequencies as values
                if (!word.isEmpty()) {
                    wordMap.put(word, wordMap.getOrDefault(word, 0) + 1);
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist!" + e);
        }
    }

    public void plotTop50Words() {
        
        //i've copied this function from @stackoverflow
        List<Map.Entry<String, Integer>> sortedWords = new ArrayList<>(wordMap.entrySet());
        sortedWords.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        int count = 0;
        for (Map.Entry<String, Integer> entry : sortedWords) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
            count++;
            if (count == 50) {
                break;
            }
        }
    }

    public void printLeastFrequentWords() {
        System.out.println("Frequency\tWord Count\tExample Words");
        for (int freq = 5; freq >= 1; freq--) {
            List<String> words = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
                if (entry.getValue() == freq) {
                    words.add(entry.getKey());
                }
            }
            System.out.println(freq + "\t\t" + words.size() + "\t\t" + words.subList(0, Math.min(5, words.size())));
        }
    }

    public void findMostFrequentPairs() {
        List<Map.Entry<String, Integer>> sortedPairs = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            if (wordMap.containsKey(word )) {
                sortedPairs.add(new AbstractMap.SimpleEntry<>(word + " ", entry.getValue()));
            }
        }
        sortedPairs.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Rank\tWord Pair\tFrequency");
        for (int i = 0; i < Math.min(10, sortedPairs.size()); i++) {
            System.out.println((i + 1) + "\t\t" + sortedPairs.get(i).getKey() + "\t\t" + sortedPairs.get(i).getValue());
        }
    }


    public static void main(String[] args) {
        LanguageModeling languageModeling = new LanguageModeling();
        languageModeling.readFile("C:\\Users\\Lucifer Shrestha\\OneDrive\\Documents\\NetBeansProjects\\LanguageModeling\\shakespeare.txt");

        // Task A - Output
        System.out.println("Task A - Top 50 words:");
        languageModeling.plotTop50Words();

        System.out.println("\nTask A - Least frequent words:");
        languageModeling.printLeastFrequentWords();

 
    }
}
