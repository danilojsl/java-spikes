package data.structures.trie;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    public void shouldSearchStringInTrie() {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any", "by", "bye", "their"};

        TrieNode root = new TrieNode();
        // Construct trie
        int i;
        for (i = 0; i < keys.length ; i++)
            Trie.insert(root, keys[i]);

        boolean isFound = Trie.search(root, "the");

        assertTrue(isFound);
    }

}