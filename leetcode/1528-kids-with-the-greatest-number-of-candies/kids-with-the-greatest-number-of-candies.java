class Solution {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
        int max = 0;
        for (int n : candies) {
            max = Math.max(max, n);
        }

        int lower = max - extraCandies;
        List<Boolean> result = new ArrayList<>(candies.length);
        for (int n : candies) {
            if (n >= lower) {
                result.add(true);
            } else {
                result.add(false);
            }
        }
        return result;
    }
}