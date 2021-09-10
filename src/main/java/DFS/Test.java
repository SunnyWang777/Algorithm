package DFS;
import java.util.*;


public class Test {
    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public String reorderSpaces(String text) {
        String s = text.trim();
        String[] words = s.split("\\s+");
        String temp = String.join("", words);
        int totSpace = text.length() - temp.length();
        if(totSpace == 0)
            return text;
        if(words.length == 1) {
            StringBuilder sb = new StringBuilder(words[0]);
            for(int i = 0; i < totSpace; i++)
                sb.append(" ");
            return sb.toString();
        }

        int cnt = totSpace / (words.length -1);
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < words.length; i++) {
            sb.append(words[i]);
            for(int c = 0; c < cnt && i != words.length -1; c++) {
                sb.append(" ");
            }
            if(i != words.length -1)
                totSpace -= cnt;
        }

        while (--totSpace >=0) {
            sb.append(" ");
        }

        return sb.toString();
    }


    public static void main(String[] args) {
        Test test = new Test();

        test.reorderSpaces( "  hello");


        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
//        ListNode n3 = new ListNode(3);
//        ListNode n4 = new ListNode(4);
//        ListNode n5 = new ListNode(5);
//        n1.next = n2; n2.next = n3; n3.next = n4; n4.next = n5;
        n1.next = n2;
        List<String> temp = new ArrayList<>();
        temp.addAll(Arrays.asList("a", "abc", "d", "de", "def"));
        //test.shortestBridge(new int[][]{{0,1},{1,0}});
        List<List<Integer>> beforeItems = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        beforeItems.add(new ArrayList<>(list));
        list.add(6);
        beforeItems.add(new ArrayList<>(list));
        list.clear();
        list.add(5);
        beforeItems.add(new ArrayList<>(list));
        list.clear();
        list.add(6);
        beforeItems.add(new ArrayList<>(list));
        list.clear();
        list.add(3);
        list.add(6);
        beforeItems.add(new ArrayList<>(list));
        list.clear();
        beforeItems.add(new ArrayList<>(list));
        beforeItems.add(new ArrayList<>(list));
        beforeItems.add(new ArrayList<>(list));

        //test.sortItems(8,2, new int[]{-1,-1,1,0,0,1,0,-1}, beforeItems);

//        List<List<String>> edges = new ArrayList<>();
//        List<String> l1 = new ArrayList<>();
//        l1.add("a");l1.add("b");
//        List<String> l2 = new ArrayList<>();
//        l2.add("b");l2.add("c");
//        edges.add(l1); edges.add(l2);

//        double[] values = {2.0,3.0};
//        List<List<String>> query = new ArrayList<>();
//        List<String> q = new ArrayList<>();
//        q.add("a");q.add("c");
//        query.add(q);

        //String[][] query = {{"a","c"},{"b","a"},{"a","e"},{"a","a"},{"x","x"}};


        char[][] max = {{'#', '.', '*', '.'}, {'#', '#', '*', '.'}};
        //test.minInsertions("()())))()");
        //test.leadsToDestination(4,new int[][] {{0,1},{0,2},{1,3},{2,3}},0,3);

//        TreeNode n1 = new TreeNode(3);
//        TreeNode n2 = new TreeNode(5);
//        TreeNode n3 = new TreeNode(1);
//        TreeNode n4 = new TreeNode(6);
//        TreeNode n5 = new TreeNode(2);
//        TreeNode n6 = new TreeNode(0);
//        TreeNode n7 = new TreeNode(8);
//        TreeNode n8 = new TreeNode(7);
//        TreeNode n9 = new TreeNode(4);
//
//        n1.left = n2;
//        n1.right = n3;
//        n2.left = n4;
//        n2.right = n5;
//        n3.left = n6;
//        n3.right = n7;
//        n5.left = n8;
//        n5.right = n9;

        // test.findDistance(n1, 5,0);
    }
}

