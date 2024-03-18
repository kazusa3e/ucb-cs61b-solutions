public class Palindrome {
    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new ArrayDeque<>();
        char[] chars = word.toCharArray();
        for (int ix = 0; ix!= chars.length; ++ix) {
            ret.addLast(chars[ix]);
        }
        return ret;
    }
}