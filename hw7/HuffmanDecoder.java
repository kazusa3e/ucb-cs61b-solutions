public class HuffmanDecoder {

    public static void main(String[] args) {
        // 1. read the Huffman coding trie.
        ObjectReader or = new ObjectReader(args[0]);
        BinaryTrie trie = (BinaryTrie) or.readObject();

        // 2.read the number of symbols.
        int numOfSymbols = (int) or.readObject();

        // 3. read the massive bit sequence corresponding to the original txt.
        BitSequence allbseq = (BitSequence) or.readObject();

        // 4. repeat until there are no more symbols:
        //      4a: perform a longest prefix match on the massive sequence.
        //      4b: record the symbol in some data structure.
        //      4c: create a new bit sequence containing the remaining unmatched bits.
        char[] chs = new char[numOfSymbols];
        int sz = 0;
        while (allbseq.length() != 0) {
            Match match = trie.longestPrefixMatch(allbseq);
            chs[sz++] = match.getSymbol();
            allbseq = allbseq.allButFirstNBits(match.getSequence().length());
        }

        // 5. write the symbols in some data structure to the specified file.
        FileUtils.writeCharArray(args[1], chs);
    }

}
