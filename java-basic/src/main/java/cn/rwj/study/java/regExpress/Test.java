package cn.rwj.study.java.regExpress;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author rwj
 * @since 2023/4/24
 */
public class Test {

    public static void main(String[] args) {
        String str = "aDemoField";
        System.out.println(underline(str));

        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        if(matcher.find()) {
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            System.out.println(sb);
            StringBuffer tailStr = matcher.appendTail(sb);
            System.out.println(tailStr);
        }

    }

    private static StringBuffer underline(String str) {
        Pattern pattern = Pattern.compile("[A-Z]");
        Matcher matcher = pattern.matcher(str);
        StringBuffer sb = new StringBuffer(str);
        if (matcher.find()) {
            sb = new StringBuffer();
            matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
            matcher.appendTail(sb);
        } else {
            return sb;
        }
        return underline(sb.toString());
    }
}
