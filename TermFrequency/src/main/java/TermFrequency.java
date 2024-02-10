import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TermFrequency {
    // Task A: Implementing TF (Term Frequency)
    public static double tf(List<String> doc, String term) {
        int count = 0;
        for (String word : doc) {
            if (word.equalsIgnoreCase(term)) {
                count++;
            }
        }
        return (double) count / doc.size();
    }

    // Task A: Implementing IDF (Inverse Document Frequency)
    public static double idf(List<List<String>> corpus, String term) {
        int numDocumentsContainingTerm = 0;
        for (List<String> doc : corpus) {
            if (doc.contains(term)) {
                numDocumentsContainingTerm++;
            }
        }
        return Math.log((double) corpus.size() / (numDocumentsContainingTerm + 1));
    }

    // Task A: Implementing TF-IDF (Term Frequency-Inverse Document Frequency)
    public static double tfIdf(List<String> doc, List<List<String>> corpus, String term) {
        double tf = tf(doc, term);
        double idf = idf(corpus, term);
        return tf * idf;
    }

    // Task B: Constructing document vectors
    public static Map<String, Double> getDocumentVector(List<String> doc, List<List<String>> corpus) {
        Map<String, Double> documentVector = new HashMap<>();
        Set<String> vocabulary = new HashSet<>();
        for (List<String> document : corpus) {
            vocabulary.addAll(document);
        }
        for (String term : vocabulary) {
            documentVector.put(term, tfIdf(doc, corpus, term));
        }
        return documentVector;
    }

    // Task C: Calculating cosine similarity
    public static double cosineSimilarity(Map<String, Double> doc1Vector, Map<String, Double> doc2Vector) {
        double dotProduct = 0;
        double doc1Norm = 0;
        double doc2Norm = 0;
        for (String term : doc1Vector.keySet()) {
            double doc1Value = doc1Vector.getOrDefault(term, 0.0);
            double doc2Value = doc2Vector.getOrDefault(term, 0.0);
            dotProduct += doc1Value * doc2Value;
            doc1Norm += Math.pow(doc1Value, 2);
            doc2Norm += Math.pow(doc2Value, 2);
        }
        doc1Norm = Math.sqrt(doc1Norm);
        doc2Norm = Math.sqrt(doc2Norm);
        return dotProduct / (doc1Norm * doc2Norm);
    }

    // Task D: Reading documents from files
    public static List<String> readDocumentFromFile(String filename) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into words, convert to lowercase, and add to the list
                String[] wordArray = line.toLowerCase().split("\\s+");
                words.addAll(Arrays.asList(wordArray));
            }
        } catch (IOException e) {
            // Handle the exception (e.g., print an error message)
            e.printStackTrace();
        }
        return words;
    }

    public static void main(String[] args) {
        // Task D: Read the documents from files
        List<String> doc1 = readDocumentFromFile("path_to_sports.txt");
        List<String> doc2 = readDocumentFromFile("path_to_entertainment.txt");
        List<String> doc3 = readDocumentFromFile("path_to_business.txt");

        List<List<String>> corpus = Arrays.asList(doc1, doc2, doc3);

        // Task B: Construct document vectors
        Map<String, Double> doc1Vector = getDocumentVector(doc1, corpus);
        Map<String, Double> doc2Vector = getDocumentVector(doc2, corpus);
        Map<String, Double> doc3Vector = getDocumentVector(doc3, corpus);

        // Task C: Calculate cosine similarity
        double similarityDoc1Doc2 = cosineSimilarity(doc1Vector, doc2Vector);
        double similarityDoc1Doc3 = cosineSimilarity(doc1Vector, doc3Vector);
        double similarityDoc2Doc3 = cosineSimilarity(doc2Vector, doc3Vector);

        System.out.println("Cosine Similarity between Doc1 and Doc2: " + similarityDoc1Doc2);
        System.out.println("Cosine Similarity between Doc1 and Doc3: " + similarityDoc1Doc3);
        System.out.println("Cosine Similarity between Doc2 and Doc3: " + similarityDoc2Doc3);
    }
}
