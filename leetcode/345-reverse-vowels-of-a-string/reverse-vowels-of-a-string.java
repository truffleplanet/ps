class Solution {

    public String reverseVowels(String s) {
        List<Integer> vowelIdx = new ArrayList<>();
        
        char[] cArr = s.toCharArray();
        for (int i = 0; i < cArr.length; i++) {
            if (isVowel(cArr[i])) {
                vowelIdx.add(i);
            }
        }

        for (int i = 0; i < vowelIdx.size() / 2; i++) {
            int head = vowelIdx.get(i);
            int tail = vowelIdx.get(vowelIdx.size() - i - 1);
            char temp = cArr[head];
            cArr[head] = cArr[tail];
            cArr[tail] = temp;
        }
        return String.valueOf(cArr);
    }

    private boolean isVowel(char c) {
        return (c == 'a' ||
                c == 'e' ||
                c == 'i' ||
                c == 'o' ||
                c == 'u' ||
                c == 'A' ||
                c == 'E' ||
                c == 'I' ||
                c == 'O' ||
                c == 'U');
    }
}