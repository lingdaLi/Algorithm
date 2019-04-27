public class RepeatedStringMatch {
    public int repeatedStringMatch(String A, String B) {
        StringBuilder sb = new StringBuilder(A);
        int times = 1;
        while(sb.length() < B.length()) {
            sb.append(A);
            ++times;
        }

        if(sb.indexOf(B) >= 0) return times;
        if(sb.append(A).indexOf(B) >= 0) return ++times;

        return -1;
    }
}
