package data.structures.trie;

public class Trie {

    //Function to insert string into TRIE.
    static void insert(TrieNode root, String key)
    {
        int level;
        int length = key.length();
        int index;

        TrieNode trieNode = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (trieNode.children[index] == null) {
                trieNode.children[index] = new TrieNode();
            }
            trieNode = trieNode.children[index];
        }

        trieNode.isEndOfWord = true;

    }

    //Function to use TRIE data structure and search the given string.
    static boolean search(TrieNode root, String key)
    {
        int level;
        int length = key.length();
        int index;
        TrieNode trieNode = root;

        for (level = 0; level < length; level++) {
            index = key.charAt(level) - 'a';
            if (trieNode.children[index] == null) {
                return false;
            }
            trieNode = trieNode.children[index];
        }

        return (trieNode.isEndOfWord);
    }

}
