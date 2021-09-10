package DFS.Backtracking;

import java.util.*;

//Input: low = 0, high = 21
//Output: [0,1,2,3,4,5,6,7,8,9,10,12,21]
public class SteppingNumbers {

    public ArrayList<Integer> countSteppingNumbers(int low, int high) {
        ArrayList<Integer> res = new ArrayList<>();
        if (low > high) return res;

        Queue<Long> queue = new LinkedList<>();
        for (long i = 1; i <= 9; i++) queue.add(i);

        if (low == 0) res.add(0);
        while (!queue.isEmpty()) {
            long p = queue.poll();
            if (p < high) {
                long last = p % 10;
                if (last > 0) queue.add(p * 10 + last - 1);
                if (last < 9) queue.add(p * 10 + last + 1);
            }
            if (p >= low && p <= high) {
                res.add((int) p);
            }
        }
        return res;
    }

    public List<Integer> countSteppingNumbersII(int low, int high) {
        List<Integer> res = new ArrayList<>();
        HashSet<Integer> set = new HashSet<>();
        dfs(low, high, res, low, set);
        return res;
    }

    private void dfs(int low, int high, List<Integer> res, int curVal, HashSet<Integer> set) {
        if(curVal > high) {
            return;
        }

        for(int i = curVal; i <=high; i++) {
            if(isSteppingNumber(i) && !set.contains(i)) {
                res.add(i);
                set.add(i);
                dfs(curVal, high, res, i, set);
            }
        }
    }

    private boolean isSteppingNumber(int num) {
        char[] temp = String.valueOf(num).toCharArray();
        for(int i = 0; i < temp.length - 1; i++) {
            if(Math.abs(temp[i] - temp[i+1]) != 1)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        SteppingNumbers test = new SteppingNumbers();
        test.countSteppingNumbers(0,21);
    }

}
