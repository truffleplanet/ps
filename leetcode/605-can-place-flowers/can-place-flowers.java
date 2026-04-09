class Solution {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int cnt = 0;
        boolean prevPlanted = false;
        for (int i = 0; i < flowerbed.length; i++) {
            if (flowerbed[i] == 1) {
                prevPlanted = true;
            } else {
                if (prevPlanted) {
                    prevPlanted = false;
                } else if (i == flowerbed.length - 1 || flowerbed[i + 1] == 0){
                    cnt++;
                    prevPlanted = true;
                }
            }
        }

        return cnt >= n;
    }
}