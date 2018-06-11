package kclc.scrabble;

import java.util.Arrays;
import java.util.Set;

public class ScrabbleUtils {
    public static String alphabetize(String unorderedString) {
        String majuscule = unorderedString.toUpperCase();
        char[] chars = majuscule.toCharArray();
        Arrays.sort(chars);
        
        return new String(chars);
    }
    
    public static int getCharPosition(char theChar) {
        return (int)(theChar - 'A');
    }
    
    /**
     * For debugging use
     */
    public static void printDictionary(TrieNode root) {
        if(root == null) {
            return;
        }
        
        Set<String> currentWords = root.getWords();
        if(!currentWords.isEmpty()) {
            for(String word : currentWords) {
                System.out.print(word + " ");
            }
            System.out.print("\n");
        }
        
        for(int i = 0; i < 26; i++) {
            printDictionary(root.getChild(i));
        }
    }
}
