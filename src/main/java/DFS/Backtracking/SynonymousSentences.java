package DFS.Backtracking;

import java.util.*;

public class SynonymousSentences {
    //region Wrong
//    public List<String> generateSentences(List<List<String>> synonyms, String text) {
//        List<String> res = new ArrayList<>();
//        HashMap<String, List<String>> graph = new HashMap<>();
//        buildGraph(synonyms, graph);
//        HashSet<String> visited = new HashSet<>();
//        String[] texts = text.split("\\s+");
//        dfs(graph, texts, 0, new StringBuilder(), res, visited);
//        return res;
//    }
//
//    private void buildGraph(List<List<String>> synonyms, HashMap<String, List<String>> graph) {
//        for(List<String> list: synonyms) {
//           for(int i = 1; i < list.size(); i++) {
//               if(!graph.containsKey(list.get(0)))
//                   graph.put(list.get(0), new ArrayList<>());
//               graph.get(list.get(0)).add(list.get(i));
//               if(!graph.containsKey(i))
//                   graph.put(list.get(i), new ArrayList<>());
//               graph.get(list.get(i)).add(list.get(0));
//           }
//        }
//    }
//
//    private void dfs(HashMap<String, List<String>> graph, String[] text, int idx, StringBuilder sb, List<String> res, HashSet<String> visited) {
//        if(idx == text.length) {
//            res.add(sb.toString());
//            return;
//        }
//
//        for(int i = idx; i < text.length; i++) {
//            String cur = text[i];
//            if(graph.containsKey(cur) && !visited.contains(cur)) {
//                sb.append(cur).append(" ");
//                visited.add(cur);
//                for(String nei: graph.get(cur)) {
//                    dfs(graph, text, i+1, sb, res, visited);
//                }
//            } else {
//                sb.append(cur).append(" ");
//            }
//        }
//    }
    //endregion

    Map<String, String> parent = new HashMap<>();
    Set<String> words = new HashSet<>();
    Map<String, List<String>> map = new HashMap<>();

    List<String> res = new ArrayList<>();

    public List<String> generateSentences(List<List<String>> synonyms, String text) {

        for (List<String> sin : synonyms) {
            union(sin);
        }

        for (String key : parent.keySet()) {
            add(key);
        }

        for (List<String> v : map.values()) {
            Collections.sort(v);
        }

        dfs(text.split(" "), 0, new StringBuilder());

        return res;
    }

    private void dfs(String[] w, int index, StringBuilder sb) {
        if (index == w.length) {
            res.add(sb.substring(0, sb.length()-1));
            return;
        }

        if(words.contains(w[index])) {
            for (String ss : map.get(parent.get(w[index]))) {
                StringBuilder sb2 = new StringBuilder(sb);
                dfs(w, index+1, sb2.append(ss).append(" "));
            }
        } else {
            sb.append(w[index]).append(" ");
            dfs(w, index +1, sb);
        }
    }

    private void add(String s) {
        words.add(s);
        String p = getParent(s);
        map.putIfAbsent(p, new ArrayList<>());
        map.get(p).add(s);
    }

    private void union(List<String> sin) {
        String p1 = getParent(sin.get(0));
        String p2 = getParent(sin.get(1));

        if(parent.get(p1).equals(p1)) {
            parent.put(p2, p1);
        } else {
            parent.put(p1, p2);
        }
    }

    private String getParent(String string) {
        if (!parent.containsKey(string)) {
            parent.put(string, string);
            return string;
        } else {
            String p = parent.get(string);
            if(p.equals(string)) {
                return p;
            }

            return getParent(p);
        }
    }

    public static void main(String[] args) {
        List<List<String>> synonyms = new ArrayList<>();
        synonyms.add(new ArrayList<>(Arrays.asList("happy","joy")));
        //synonyms.add(new ArrayList<>(Arrays.asList("sad","sorrow")));
        synonyms.add(new ArrayList<>(Arrays.asList("joy","cheerful")));

        String text = "I am happy today but was sad yesterday";
        SynonymousSentences test = new SynonymousSentences();
        test.generateSentences(synonyms, text);
    }
}
