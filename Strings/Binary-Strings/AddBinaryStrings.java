class Solution {
    public String addBinary(String s1, String s2) {
        int i = s1.length() - 1;
        int j = s2.length() - 1;
        int carry = 0;

        StringBuilder result = new StringBuilder();

        while (i >= 0 || j >= 0 || carry == 1) {
            int sum = carry;

            if (i >= 0) sum += s1.charAt(i--) - '0';
            if (j >= 0) sum += s2.charAt(j--) - '0';

            result.append(sum % 2);
            carry = sum / 2;
        }

        return result.reverse().toString();
    }
}
