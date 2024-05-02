import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HuffmanEncoder {

    public static Map<Character, Integer> buildFrequencyTable(char[] inputSymbols) {
        Map<Character, Integer> frequencyTable = new HashMap<>();
        for (char ch : inputSymbols) {
            frequencyTable.put(ch, frequencyTable.getOrDefault(ch, 0) + 1);
        }
        return frequencyTable;
    }

    public static void main(String[] args) {
        // 1. read the file as 8 bit symbols.
        char[] symbols = FileUtils.readFile(args[0]);

        // 2. build frequency table.
        Map<Character, Integer> frequencyTable = buildFrequencyTable(symbols);

        // 3. use frequency table to construct a binary decoding trie.
        BinaryTrie binaryDecodingTrie = new BinaryTrie(frequencyTable);

        // 4. write the binary decoding tries to the .huf file.
        ObjectWriter ow = new ObjectWriter(args[0] + ".huf");
        ow.writeObject(binaryDecodingTrie);

        // 5. write the number of symbols to the .huf file.
        ow.writeObject(symbols.length);

        // 6. use binary trie to create lookup table for encoding.
        Map<Character, BitSequence> lookupTable = binaryDecodingTrie.buildLookupTable();

        // 7. create a list of bit sequences.
        List<BitSequence> bseqs = new ArrayList<>();

        // 8. for each 8 bit symbol:
        //      lookup that symbol in the lookup table.
        //      add the appropriate bit sequences to the list of bit sequences.
        for (char ch : symbols) {
            BitSequence bs = lookupTable.get(ch);
            bseqs.add(bs);
        }

        // 9. assemble all bit sequences into one huge bit sequence.
        BitSequence allbseq = BitSequence.assemble(bseqs);

        // 10. write the huge bit sequence to the .huf file.
        ow.writeObject(allbseq);
    }

}
