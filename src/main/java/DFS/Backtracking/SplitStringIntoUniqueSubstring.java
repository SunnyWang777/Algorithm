package DFS.Backtracking;

import java.util.HashSet;

//1593. Split a String Into the Max Number of Unique Substrings
public class SplitStringIntoUniqueSubstring {
    int max = Integer.MIN_VALUE;

    public int maxUniqueSplit(String s) {
        if(s == null || s.length() == 0)
            return 0;
        HashSet<String> set = new HashSet<>();
        dfs(s, set, 0);
        return max;
    }

    private int dfs(String s, HashSet<String> set, int start) {
        if(start >= s.length())
            return set.size();

        int ans = Integer.MIN_VALUE;
        for(int i = start+1; i <= s.length(); i++) {
            String temp = s.substring(start, i);
            if(set.contains(temp))
                continue;
            set.add(temp);
            ans = dfs(s, set, i);
            set.remove(temp);
        }

        max = Math.max(ans, max);
        return ans;
    }

    public static void main(String[] args) {
        String s = "ababccc";
        SplitStringIntoUniqueSubstring test = new SplitStringIntoUniqueSubstring();
        test.maxUniqueSplit(s);
    }
}
