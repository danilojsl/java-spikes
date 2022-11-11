package algorithms;

// Java program for implementation of Aho Corasick algorithm for String matching
import java.util.*;

//https://dl.acm.org/doi/10.1145/360825.360855
//https://doi.org/10.1515/jib-2014-253

//another option:
//https://www.sanfoundry.com/java-program-implement-aho-corasick-algorithm-string-matching/#:~:text=This%20is%20a%20java%20program,It%20matches%20all%20patterns%20simultaneously.

class AhoCorasickModified {

    static String englishAlphabet = "abcdefghijklmnopqrstuvwxyz ";

    // Max number of states in the matching machine.
    // Should be equal to the sum of the length of all keywords.
    static int MAX_STATES = 100;

    // Maximum number of characters in input alphabet
    static int MAX_EDGES = englishAlphabet.length();

    static List<Character> edges = new ArrayList<>();

    // OUTPUT FUNCTION IS IMPLEMENTED USING out[]
    // Bit i in this mask is one, if the word with index i appears when the machine enter this state.
    static int [] out = new int[MAX_STATES];

    // FAILURE FUNCTION IS IMPLEMENTED USING f[]
    static int [] failures = new int[MAX_STATES];

    // GOTO FUNCTION (OR TRIE) IS IMPLEMENTED USING g[][]
    static int [][] transitionTable = new int[MAX_STATES][MAX_EDGES];

    // Builds the String matching machine.
// arr - array of words. The index of each keyword is important:
//		 "out[state] & (1 << i)" is > 0 if we just found word[i] in the text.
// Returns the number of states that the built machine has.
// States are numbered 0 up to the return value - 1, inclusive.
    static void buildMatchingMachine(String[] keywords)
    {
        initializeValues();
        buildGoToGraph(keywords);
        buildFailureLink();
    }

    static void initializeValues() {

        for (Character character : englishAlphabet.toCharArray()) {
            edges.add(character);
        }

        Arrays.fill(out, 0);

        for(int i = 0; i < MAX_STATES; i++) {
            Arrays.fill(transitionTable[i], -1);
        }

        // Initialize values in fail function
        Arrays.fill(failures, -1);

    }

    static void buildGoToGraph(String[] keywords) {
        int keywordsLength = keywords.length;
        // Initially, we just have the 0 state
        int states = 1;

        // Convalues for goto function, i.e., fill g[][]
        // This is same as building a Trie for arr[]
        for(int keywordIndex = 0; keywordIndex < keywordsLength; keywordIndex++)
        {
            String keyword = keywords[keywordIndex];
            int currentState = 0;

            // Insert all characters of current word in arr[]
            for(int charIndex = 0; charIndex < keyword.length(); charIndex++)
            {
                int edgeIndex = edges.indexOf(keyword.charAt(charIndex));

                // Allocate a new node (create a new state) if a node for ch doesn't exist.
                if (transitionTable[currentState][edgeIndex] == -1) {
                    transitionTable[currentState][edgeIndex] = states++;
                }

                currentState = transitionTable[currentState][edgeIndex];
            }

            // Add current word in output function
            if (keyword.equals("doctor john snow") ) {
                System.out.println("debug");
            }
            out[currentState] |= (1 << keywordIndex);
        }

        // For all characters which don't have an edge from root (or state 0) in Trie,
        // add a goto edge to state 0 itself
        for(int charIndex = 0; charIndex < MAX_EDGES; charIndex++) {
            if (transitionTable[0][charIndex] == -1) {
                transitionTable[0][charIndex] = 0;
            }
        }
    }

    static void buildFailureLink() {

        // Failure function is computed in breadth first order using a queue
        Queue<Integer> queue = new LinkedList<>();

        // Iterate over every possible input
        for(int charIndex = 0; charIndex < MAX_EDGES; charIndex++)
        {

            // All nodes of depth 1 have failure function value as 0.
            // For example, in above diagram we move to 0 from states 1 and 3.
            if (transitionTable[0][charIndex] != 0)
            {
                int nodeDepthOne = transitionTable[0][charIndex];
                failures[nodeDepthOne] = 0;
                queue.add(nodeDepthOne);
            }
        }

        // Now queue has states 1 and 3
        while (!queue.isEmpty())
        {

            // Remove the front state from queue
            int state = queue.peek();
            queue.remove();

            // For the removed state, find failure function for all those characters
            // for which goto function is not defined.
            for(int charIndex = 0; charIndex < MAX_EDGES; charIndex++)
            {

                // If goto function is defined for character 'ch' and 'state'
                if (transitionTable[state][charIndex] != -1)
                {

                    // Find failure state of removed state
                    int failure = failures[state];

                    // Find the deepest node labeled by proper suffix of String from root to current state.
                    if (transitionTable[failure][charIndex] == -1) {
                        failure = failures[failure];
                    }

                    failure = transitionTable[failure][charIndex];
                    int nextNode = transitionTable[state][charIndex];

                    failures[nextNode] = failure;
                    out[nextNode] |= out[failure]; // Merge output values

                    // Insert the next level node (of Trie) in Queue
                    queue.add(nextNode);
                }
            }
        }
    }

    // This function finds all occurrences of all array words in text.
    static void searchWords(String[] keywords, String text)
    {
        int keywordsLength = keywords.length;
        // Initialize current state
        int currentState = 0;

        // Traverse the text through the built machine to find all occurrences of words in arr[]
        for(int textIndex = 0; textIndex < text.length(); textIndex++)
        {
            char currentChar = text.charAt(textIndex);

            currentState = findNextState(currentState, currentChar);

            // If match not found, move to next state
            if (out[currentState] == 0)
                continue;

            // Match found, print all matching words of arr[] using output function.
            for(int keywordIndex = 0; keywordIndex < keywordsLength; ++keywordIndex)
            {
                int bitwiseAndResult = out[currentState] & 1 << keywordIndex;
                if (bitwiseAndResult > 0)
                {
                    System.out.print("Word " + keywords[keywordIndex] +
                            " appears from " +
                            (textIndex - keywords[keywordIndex].length() + 1) +
                            " to " + textIndex + " with bitwise: " + bitwiseAndResult +
                            "and currentState: " + currentState +
                            "\n");
                }
            }

        }
    }

    // Returns the next state the machine will transition to using goto  and failure functions.
    // currentState - The current state of the machine. Must be between
    //	0 and the number of states - 1, inclusive.
    // nextInput - The next character that enters into the machine.
    static int findNextState(int state, char edge)
    {
        int currentState = state;
        int alphabetIndex = edges.indexOf(edge);

        // If goto is not defined, use failure function
        int nextState = transitionTable[currentState][alphabetIndex];

        if (nextState == -1) {
            currentState = failures[currentState];
            nextState = transitionTable[currentState][alphabetIndex];
        }

        if (nextState == -1) {
            return 0;
        }

        return nextState;
    }

    // Driver code
    public static void main(String[] args)
    {

//        String[] keywords = { "he", "she", "his", "hers", "babe and", "his" };
        String[] keywords = { "jon", "snow", "john", "doctor john snow", "winterfell", "john snow", "eddard", "jon snow", "eddard stark", "stark" };
//        String[] keywords = { "abc", "adec", "cad"};
//        String[] arr = { "at", "tea", "cute", "cat" };
//        String text = "she is my babe and hers body is amazing"; //TODO: Test with text ahishers
          String text = "doctor john snow lives in winterfell";

        // Preprocess patterns.
        // Build machine with goto, failure and output functions
        buildMatchingMachine(keywords);

        searchWords(keywords, text);
    }

}

// This code is contributed by Princi Singh
