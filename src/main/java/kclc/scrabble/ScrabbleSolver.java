package kclc.scrabble;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

public class ScrabbleSolver {
    private static TrieNode dictionaryRoot = new TrieNode();

    public static void main(String[] args) {
        buildDataStructure();

        displayInstructions();
        String userInputOriginal = getUserInput();

        while (!userInputOriginal.equals("XXX")) {
            String userInputAlphabetized = ScrabbleUtils.alphabetize(userInputOriginal);
            Set<String> answers = findAnagrams(dictionaryRoot, userInputAlphabetized);

            if (answers != null) {
                for (String option : answers) {
                    System.out.print(option + " ");
                }
                System.out.print("\n");
                System.out.println();
            }

            displayInstructions();
            userInputOriginal = getUserInput();
        }
    }

    private static void buildDataStructure() {
        Path wordListFileName = Paths.get("src/main/resources/ScrabbleDictionary.txt");
        Charset wordListFileCharset = StandardCharsets.US_ASCII;

        try (Scanner scanner = new Scanner(wordListFileName, wordListFileCharset)) {
            scanner.useDelimiter(" ");

            while (scanner.hasNext()) {
                String nextWordOriginal = scanner.next();

                String nextWordAlphabetical = ScrabbleUtils.alphabetize(nextWordOriginal);

                addWordToDictionary(dictionaryRoot, nextWordAlphabetical, nextWordOriginal);
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private static void addWordToDictionary(TrieNode root, String alphabetizedInput, String word) {
        if (alphabetizedInput.isEmpty()) {
            root.addWord(word);

            return;
        }

        char stringHead = alphabetizedInput.charAt(0);
        int charPosition = ScrabbleUtils.getCharPosition(stringHead);

        if (root.getChild(charPosition) == null) {
            root.setChild(charPosition);
        }

        addWordToDictionary(root.getChild(charPosition), alphabetizedInput.substring(1), word);
    }

    private static void displayInstructions() {
        System.out.println("Enter the rack to solve for:");
    }

    private static String getUserInput() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        scanner.close();

        return input;
    }

    private static Set<String> findAnagrams(TrieNode root, String searchString) {
        if (root == null) {
            return null;
        }

        if (searchString.isEmpty()) {
            return root.getWords();
        }

        char stringHead = searchString.charAt(0);
        int charPosition = ScrabbleUtils.getCharPosition(stringHead);

        return findAnagrams(root.getChild(charPosition), searchString.substring(1));
    }
}
