import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class LanguageModeling {

    private TreeMap<String, Integer> wordMap = new TreeMap<>(); // Change HashMap to TreeMap

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
            if (wordMap.containsKey(word + " ")) {
                sortedPairs.add(new AbstractMap.SimpleEntry<>(word + " ", entry.getValue()));
            }
        }
        sortedPairs.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Rank\tWord Pair\tFrequency");
        for (int i = 0; i < Math.min(10, sortedPairs.size()); i++) {
            System.out.println((i + 1) + "\t\t" + sortedPairs.get(i).getKey() + "\t\t" + sortedPairs.get(i).getValue());
        }
    }

    public void findMostFrequentTrigrams() {
        List<Map.Entry<String, Integer>> sortedTrigrams = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordMap.entrySet()) {
            String word = entry.getKey();
            if (wordMap.containsKey(word + " ")) {
                String nextWord = wordMap.higherKey(word + " ");
                if (nextWord != null && nextWord.startsWith(word + " ")) {
                    sortedTrigrams.add(new AbstractMap.SimpleEntry<>(word + " " + nextWord, entry.getValue()));
                }
            }
        }
        sortedTrigrams.sort((a, b) -> b.getValue().compareTo(a.getValue()));

        System.out.println("Rank\tTrigrams\tFrequency");
        for (int i = 0; i < Math.min(10, sortedTrigrams.size()); i++) {
            System.out.println((i + 1) + "\t\t" + sortedTrigrams.get(i).getKey() + "\t\t" + sortedTrigrams.get(i).getValue());
        }
    }

    public double calculateRelativeFrequency(String word) {
        int totalCount = 0;
        for (int count : wordMap.values()) {
            totalCount += count;
        }
        int wordCount = wordMap.getOrDefault(word, 0);
        return (double) wordCount / totalCount;
    }

    public double calculateConditionalProbability(String wordGiven, String condition) {
        int conditionCount = wordMap.getOrDefault(condition, 0);
        if (conditionCount == 0) {
            return 0.0; // Word not found in the text
        }
        String wordCondition = condition + " " + wordGiven;
        int wordConditionCount = wordMap.getOrDefault(wordCondition, 0);
        return (double) wordConditionCount / conditionCount;
    }

    public double calculateJointProbability(String... words) {
        int jointCount = wordMap.getOrDefault(String.join(" ", words), 0);
        int denominatorCount = wordMap.getOrDefault(words[0], 0);
        for (int i = 1; i < words.length; i++) {
            denominatorCount = wordMap.getOrDefault(words[i - 1] + " " + words[i], 0);
        }
        if (denominatorCount == 0) {
            return 0.0; // Word sequence not found in the text
        }
        return (double) jointCount / denominatorCount;
    }

    public double calculateAddOneSmoothedProbability(String word) {
        int totalCount = 0;
        for (int count : wordMap.values()) {
            totalCount += count;
        }
        int wordCount = wordMap.getOrDefault(word, 0);
        int vocabularySize = wordMap.size();
        return (double) (wordCount + 1) / (totalCount + vocabularySize);
    }

    public double calculateAddOneSmoothedConditionalProbability(String wordGiven, String condition) {
        int conditionCount = wordMap.getOrDefault(condition, 0);
        int wordConditionCount = wordMap.getOrDefault(condition + " " + wordGiven, 0);
        int vocabularySize = wordMap.size();
        return (double) (wordConditionCount + 1) / (conditionCount + vocabularySize);
    }

    public double calculateBackgroundProbability(String word) {
        int totalCount = 0;
        for (int count : wordMap.values()) {
            totalCount += count;
        }
        int wordCount = wordMap.getOrDefault(word, 0);
        return (double) wordCount / totalCount;
    }

    public double calculateJelinekMercerSmoothedProbability(String word, double lambda) {
        double backgroundProbability = calculateBackgroundProbability(word);
        double addOneSmoothedProbability = calculateAddOneSmoothedProbability(word);
        return (lambda * addOneSmoothedProbability) + ((1 - lambda) * backgroundProbability);
    }

    public static void main(String[] args) {
        LanguageModeling languageModeling = new LanguageModeling();
        languageModeling.readFile("shakespeare.txt");

        // Task A - Output
        System.out.println("Task A - Top 50 words:");
        languageModeling.plotTop50Words();

        System.out.println("\nTask A - Least frequent words:");
        languageModeling.printLeastFrequentWords();

        System.out.println("\nTask A - 10 most frequent word-pairs (bigrams):");
        languageModeling.findMostFrequentPairs();

        System.out.println("\nTask A - 10 most frequent three-word sequences (trigrams):");
        languageModeling.findMostFrequentTrigrams();

        // Task B and Task C - Output (unchanged from previous response)

        System.out.println("\nTask B - Relative Frequencies:");

        // ... (remaining code for Task B and Task C as shown in the previous response)

        double lambda = 0.5;
        String[] wordsToCalculateJelinekMercerSmoothedProbability = {"the", "become", "brave", "treason"};
        for (String word : wordsToCalculateJelinekMercerSmoothedProbability) {
            System.out.println("P(" + word + ") = " + languageModeling.calculateJelinekMercerSmoothedProbability(word, lambda));
        }
    }
}
