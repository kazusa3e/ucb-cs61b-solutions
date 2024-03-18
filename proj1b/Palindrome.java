public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new ArrayDeque<>();
        char[] chars = word.toCharArray();
        for (int ix = 0; ix != chars.length; ++ix) {
            ret.addLast(chars[ix]);
        }
        return ret;
    }

    private boolean isPalindrome(Deque<Character> word) {
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        char l = word.removeFirst();
        char r = word.removeLast();
        if (l != r) {
            return false;
        }
        return isPalindrome(word);
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(wordToDeque(word));
    }

}