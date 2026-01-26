class Solution {
    String findSum(String s1, String s2) {

        int carry = 0;
        String s3 = "";

        // Remove leading zeros
        s1 = removeleadzero(s1);
        s2 = removeleadzero(s2);

        int i = s1.length() - 1, j = s2.length() - 1;

        while (i >= 0 && j >= 0) {
            int sum = (s1.charAt(i) - '0') + (s2.charAt(j) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            i--;
            j--;
        }

        while (i >= 0) {
            int sum = (s1.charAt(i) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            i--;
        }

        while (j >= 0) {
            int sum = (s2.charAt(j) - '0') + carry;
            int digit = sum % 10;
            carry = sum / 10;
            s3 = digit + s3;
            j--;
        }

        if (carry > 0)
            s3 = carry + s3;

        return s3;
    }

    String removeleadzero(String s) {
        int n = s.length();
        int i = 0;

        // Skip zeros but stop at second last index
        for (i = 0; i < n - 1; i++) {
            if (s.charAt(i) != '0')
                break;
        }

        return s.substring(i);
    }
}
