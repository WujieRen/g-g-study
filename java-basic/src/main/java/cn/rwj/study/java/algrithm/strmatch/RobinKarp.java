package cn.rwj.study.java.algrithm.strmatch;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author rwj
 * @since 2024/8/23
 */
public class RobinKarp {

    public static void main(String[] args) {
        RobinKarp rk = new RobinKarp();
//        System.out.println(rk.rk("dbacaac", "ac"));
//        System.out.println(rk.rk("dbacaac", "acd"));
        System.out.println(rk.rk("aaaaa", "aaa"));
    }

    List<Integer> list = new LinkedList<>();
    long[] substrCode;

    public List<Integer> rk(String mstr, String sstr) {
        int mlen = mstr.length(), slen = sstr.length();
        if(mlen < slen) return list;

        long scode = hash(sstr, slen - 1);
        long mcode = hash(mstr, slen - 1);

        int msCodeLen = mlen - slen + 1;
        substrCode = new long[msCodeLen];
        substrCode[0] = mcode;   //第一个特殊处理
        for (int i = 0; i < msCodeLen; i++) {
            if(substrCode[i] == scode && mstr.substring(i, i + slen).equals(sstr)) {    // code 对上了，校验字符串是否相等
                list.add(i);
            }

            int nextIdx = i+1;
            if(nextIdx < msCodeLen) substrCode[nextIdx] = nextHash(substrCode[i], mstr, nextIdx, slen);
        }

        return list;
    }

    public long nextHash(long lastCode, String mstr, int i, int slen) {
        return (long) (26 * (lastCode - Math.pow(26, slen - 1) * (mstr.charAt(i - 1) - 'a')) + (mstr.charAt(i + slen - 1) - 'a'));
    }

    public long hash(String str, int end) {
        long scode = 0;
        for(int i = end, j = 0; i >= 0; --i, ++j) {
            scode += (long) (Math.pow(26, i) * (str.charAt(j) - 'a'));
        }
        return scode;
    }

}
