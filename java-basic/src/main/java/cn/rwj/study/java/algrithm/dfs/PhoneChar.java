package cn.rwj.study.java.algrithm.dfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/24
 */
public class PhoneChar {

    List<List<String>> phoneChar = new ArrayList<>() {{
        add(new ArrayList<>() {{
            add("a");
            add("b");
            add("c");
        }});
        add(new ArrayList<>() {{
            add("d");
            add("e");
            add("f");
        }});
        add(new ArrayList<>() {{
            add("g");
            add("h");
            add("i");
        }});
        add(new ArrayList<>() {{
            add("j");
            add("k");
            add("l");
        }});
        add(new ArrayList<>() {{
            add("m");
            add("n");
            add("o");
        }});
        add(new ArrayList<>() {{
            add("p");
            add("q");
            add("r");
            add("s");
        }});
        add(new ArrayList<>() {{
            add("t");
            add("u");
            add("v");
        }});
        add(new ArrayList<>() {{
            add("w");
            add("x");
            add("y");
            add("z");
        }});
    }};

    public static void main(String[] args) {
        System.out.println(new PhoneChar().digitsToChars("5377977"));
    }

    public List<String> digitsToChars(String digits) {
        List<List<String>> list = new ArrayList<>();
        List<String> res = new LinkedList<>();

        for (int i = 0; i < digits.length(); i++) {
            int idx = Integer.parseInt(digits.substring(i, i + 1));
            if(idx - 2 < 0) return res;
            list.add(phoneChar.get(idx - 2));
        }

        backTrack(list, 0, 0, res, "");
        return res;
    }

    public void backTrack(List<List<String>> list, int pi, int ci, List<String> res, String curStr) {
        if (curStr.length() == list.size()) {
            res.add(curStr);
            return;
        }
        if (pi >= list.size() || ci >= list.get(pi).size()) return;

        backTrack(list, pi + 1, 0, res, curStr + list.get(pi).get(ci));
        backTrack(list, pi, ci + 1, res, curStr);
    }

}
