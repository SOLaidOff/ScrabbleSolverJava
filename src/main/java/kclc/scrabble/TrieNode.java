package kclc.scrabble;

import java.util.HashSet;
import java.util.Set;

public class TrieNode {
    private TrieNode[] children = new TrieNode[26];
    private Set<String> wordList = new HashSet<String>();
    
    public TrieNode getChild(int index) {
        return children[index];
    }
    
    public void setChild(int index) {
        children[index] = new TrieNode();
    }
    
    public void addWord(String word) {
        wordList.add(word);
    }
    
    public Set<String> getWords() {
        return wordList;
    }
}
