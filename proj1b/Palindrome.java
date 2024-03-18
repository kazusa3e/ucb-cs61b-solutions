public class Palindrome {

    public Deque<Character> wordToDeque(String word) {
        Deque<Character> ret = new ArrayDeque<>();
        char[] chars = word.toCharArray();
        for (int ix = 0; ix != chars.length; ++ix) {
            ret.addLast(chars[ix]);
        }
        return ret;
    }

    private boolean isPalindrome(Deque<Character> word, CharacterComparator cc) {
        if (word.size() == 0 || word.size() == 1) {
            return true;
        }
        char l = word.removeFirst();
        char r = word.removeLast();
        if (!cc.equalChars(l, r)) {
            return false;
        }
        return isPalindrome(word, cc);
    }

    public boolean isPalindrome(String word) {
        return isPalindrome(word, new CharacterComparator() {
            @Override
            public boolean equalChars(char x, char y) {
                return x == y;
            }
        });
    }

    public boolean isPalindrome(String word, CharacterComparator cc) {
        Deque<Character> lst = wordToDeque(word);
        return isPalindrome(lst, cc);
    }

}