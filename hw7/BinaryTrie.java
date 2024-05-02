import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class BinaryTrie implements Serializable {

    private static final char NON_CHARACTER = '\0';

    private static class Node implements Serializable, Comparable<Node> {

        Node left, right;
        int frequency;
        char ch;

        public Node(Node l, Node r, int f, char c) {
            left = l;
            right = r;
            frequency = f;
            ch = c;
        }

        @Override
        public int compareTo(Node o) {
            return this.frequency - o.frequency;
        }

    }

    private Node root;
    private Map<Character, BitSequence> lookupTable;

    public BinaryTrie(Map<Character, Integer> frequencyTable) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (Map.Entry<Character, Integer> entry : frequencyTable.entrySet()) {
            pq.add(new Node(null, null, entry.getValue(), entry.getKey()));
        }
        while (pq.size() > 1) {
            Node left = pq.poll();
            Node right = pq.poll();
            pq.add(new Node(left, right, left.frequency + right.frequency, NON_CHARACTER));
        }
        this.root = pq.poll();
        this.lookupTable = null;
    }

    public Match longestPrefixMatch(BitSequence querySequence) {
        BitSequence seq = new BitSequence();
        int sz = 0;
        Node iter = this.root;
        while (iter.ch == NON_CHARACTER) {
            int b = querySequence.bitAt(sz);
            seq = seq.appended(b);
            sz += 1;
            iter = (b == 0) ? iter.left : iter.right;
        }
        return new Match(seq, iter.ch);
    }

    public Map<Character, BitSequence> buildLookupTable() {
        if (this.lookupTable != null) {
            return this.lookupTable;
        }
        this.lookupTable = new HashMap<>();
        helper(new BitSequence("0"), root.left);
        helper(new BitSequence("1"), root.right);
        return this.lookupTable;
    }

    private void helper(BitSequence prefix, Node node) {
        if (node.ch != NON_CHARACTER) {
            this.lookupTable.put(node.ch, prefix);
            return;
        }
        if (node.left != null) {
            helper(prefix.appended(0), node.left);
        }
        if (node.right != null) {
            helper(prefix.appended(1), node.right);
        }
    }

}
