package cn.rwj.study.java.algrithm.strmatch;

import java.util.LinkedList;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/23
 */
public class BrouteForce {

    public static void main(String[] args) {
        BrouteForce bf = new BrouteForce();
        System.out.println(bf.bf("aaaaa", "aaa"));
    }

    List<Integer> list = new LinkedList<>();
    public List<Integer> bf(String mstr, String sstr) {
        if(mstr.isBlank() || sstr.isBlank()) return list;
        int mlen = mstr.length(), slen = sstr.length();
        for(int i = 0; i <= mlen - slen; i++) {
            if(mstr.substring(i, i + slen).equals(sstr)) {
                list.add(i);
            }
        }
        return list;
    }

}
