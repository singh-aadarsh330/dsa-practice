class Solution {
    public int reverse(int x) {
        int rev = 0;
        int max = Integer.MAX_VALUE;
        int min = Integer.MIN_VALUE;

        while (x != 0) {
            int digit = x % 10;
            x /= 10;

            if (rev > max / 10 || (rev == max / 10 && digit > 7)) return 0;
            if (rev < min / 10 || (rev == min / 10 && digit < -8)) return 0;

            rev = rev * 10 + digit;
        }
        return rev;
    }
}
