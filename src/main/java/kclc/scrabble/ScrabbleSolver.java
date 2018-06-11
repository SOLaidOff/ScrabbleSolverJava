package kclc.scrabble;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ScrabbleSolver {

    public static void main(String[] args) {
        buildDataStructure();
    }

    private static void buildDataStructure() {
        Path wordListFileName = Paths.get("src/main/resources/ScrabbleDictionary.txt");
        Charset wordListFileCharset = StandardCharsets.US_ASCII;

        List<String> words = new ArrayList<String>();//DEBUG
        try (Scanner scanner = new Scanner(wordListFileName, wordListFileCharset)) {
            scanner.useDelimiter(" ");
            
            int i = 0;//DEBUG
            while(scanner.hasNext()) {
                String nextWord = scanner.next();
                
                System.out.print(nextWord + " ");
                
                if(i == 7) {
                    System.out.print("\n");
                    i = 0;
                }
                else
                    i++;
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
