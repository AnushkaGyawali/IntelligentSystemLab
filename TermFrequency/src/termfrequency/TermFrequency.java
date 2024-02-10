/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package termfrequency;

/**
 *
 * @author Dell
 */
import java.util.*;

import java.util.Arrays;
import java.util.List;
public class TermFrequency {
 
    public double tf(List<String> document, String term) {
        //method/function tf() which takes a document and a term as an argument. The 
        //function returns the occurrence count of the term in that document. [Note: It is often useful 
        //to divide the count by number of words in the document. Could you see the reason why this is 
        //useful?]
        int count = Collections.frequency(document, term);
        int totalWords = document.size();
        return (double) count / totalWords;
    }

    public double idf(List<List<String>> documentCollection, String term) {
        // a method idf() which takes the document collection (in our case the list/array) and the 
        //term as an argument. The function returns the idf score for the term.         
        //IDFt  = log(N/ DFt )  
        //Here N is the number of documents in the collection. DFt is the document frequency of the term t. It is 
        //the number of documents in the collection that contains the term.
        int documentFrequency = 0;
        for (List<String> document : documentCollection) {
            if (document.contains(term)) {
                documentFrequency++;
            }
        }
        int totalDocuments = documentCollection.size();
        return Math.log((double) totalDocuments / (documentFrequency));
    }

    public double tfIdf(List<String> document, List<List<String>> documentCollection, String term) {
        double termFrequency = tf(document, term);
        double inverseDocumentFrequency = idf(documentCollection, term);
        return termFrequency * inverseDocumentFrequency;
    }
    
    
    public void printList(List<String> doc) {
    for(String word : doc) {
    System.out.print(word+" ");
    }
    System.out.print("\n");
    }

    public void printListofLists(List<List<String>> docs) {
    for(List<String> doc : docs) {
    for(String word : doc) {
    System.out.print(word+" ");
    }
    System.out.print("\n");
    }
    }

   public double cosineSimilarity(List<Double> vector1, List<Double> vector2) {
        if (vector1.size() != vector2.size()) {
            throw new IllegalArgumentException("Vectors must have the same dimension");
        }

        double dotProduct = 0;
        double norm1 = 0;
        double norm2 = 0;

        for (int i = 0; i < vector1.size(); i++) {
            dotProduct += vector1.get(i) * vector2.get(i);
            norm1 += Math.pow(vector1.get(i), 2);
            norm2 += Math.pow(vector2.get(i), 2);
        }

        norm1 = Math.sqrt(norm1);
        norm2 = Math.sqrt(norm2);

        return dotProduct / (norm1 * norm2);
    }



    public static void main(String[] args) {
    TermFrequency freq = new TermFrequency();
    List<String> doc1 = Arrays.asList("the","sky", "is", "blue");
    List<String> doc2 = Arrays.asList("the","sun","is","bright");
    List<String> doc3 =Arrays.asList("the","sun","in","the","sky","is","bright");
    List<List<String>> corpus = Arrays.asList(doc1, doc2, doc3);

    freq.printList(doc1);
    freq.printList(doc2);

    System.out.println("\nDocument Collection:");
    freq.printListofLists(corpus);

   System.out.println("TF-IDF score for term 'bright' in Doc1: " + freq.tfIdf(doc1, corpus, "bright"));
   System.out.println("TF-IDF score for term 'the' in Doc1: " + freq.tfIdf(doc1, corpus, "the"));
   System.out.println("TF-IDF score for term 'blue' in Doc1: " + freq.tfIdf(doc1, corpus, "blue"));
   System.out.println("TF-IDF score for term 'blue' in Doc1: " + freq.tfIdf(doc1, corpus, "sky"));
   
   
//    1. Why is the tf-idf score for the term “bright” zero for Doc1?
//    => because tf = 0 i.e. since there is no any 'bright' word in the DOC1
//    2. Why is the tf-idf score for the term “the” zero for Doc1?
//    => because idf =0; i.e total num of documents(N)= docement frequency of term 'the' (DFt)
//    3. What can we tell from seeing that tf-idf score for the term “blue” is larger than “sky”?
//    => we can say that weight of term "blue" is greater than "sky" in DOC1


//TASK B: 
//1. Write a function cosineSimilarity(d1, d2) that calculates cosine similarity between two 
//document vectors passed as arguments.
//2. Write a function score(q,d) that calculates score for a query (a search string like what you give 
//to google search) in document d. You will implement this formula


//FIRST LET'S FIND OUT TF-IDF VALUES OF ALL VOCABULARY{the=a, sky=b, is=c, blue=d, sun=e, bright=f, in=g} FOR DOC1 AND DOC2
       
        double a1,b1,c1,d1,e1,f1,g1,a2,b2,c2,d2,e2,f2,g2,a3,b3,c3,d3,e3,f3,g3;
        //for doc1
        a1=freq.tfIdf(doc1, corpus, "the");
        b1=freq.tfIdf(doc1, corpus, "sky");
        c1=freq.tfIdf(doc1, corpus, "is");
        d1=freq.tfIdf(doc1, corpus, "blue");
        e1=freq.tfIdf(doc1, corpus, "sun");
        f1= freq.tfIdf(doc1, corpus, "bright");
        g1=freq.tfIdf(doc1, corpus, "in");
        //for doc2
        a2=freq.tfIdf(doc2, corpus, "the");
        b2=freq.tfIdf(doc2, corpus, "sky");
        c2=freq.tfIdf(doc2, corpus, "is");
        d2=freq.tfIdf(doc2, corpus, "blue");
        e2=freq.tfIdf(doc2, corpus, "sun");
        f2= freq.tfIdf(doc2, corpus, "bright");
        g2=freq.tfIdf(doc2, corpus, "in");
        //for doc
        a3=freq.tfIdf(doc3, corpus, "the");
        b3=freq.tfIdf(doc3, corpus, "sky");
        c3=freq.tfIdf(doc3, corpus, "is");
        d3=freq.tfIdf(doc3, corpus, "blue");
        e3=freq.tfIdf(doc3, corpus, "sun");
        f3= freq.tfIdf(doc3, corpus, "bright");
        g3=freq.tfIdf(doc3, corpus, "in");
                
        List<Double> v1 = Arrays.asList(a1,b1,c1,d1,e1,f1,g1);
        List<Double> v2 = Arrays.asList(a2,b2,c2,d2,e2,f2,g2);
        List<Double> v3 = Arrays.asList(a3,b3,c3,d3,e3,f3,g3);
        System.out.println("Cosine similarity between Doc1 and Doc2: " + freq.cosineSimilarity(v2, v1));
        System.out.println("Cosine similarity between Doc2 and Doc3: " + freq.cosineSimilarity(v2, v3));


    }
}
