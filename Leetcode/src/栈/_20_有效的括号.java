package 栈;

import java.util.HashMap;
import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/valid-parentheses/
 */
public class _20_有效的括号 {

    private static HashMap<Character, Character> map = new HashMap<>();

    static  {
        map.put('(',')');
        map.put('{','}');
        map.put('[',']');
    }

    public boolean isValid(String s) {

        Stack<Character> stack = new Stack<>();

        int len = s.length();

        if (len % 2 != 0) return false;

        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {//左字符
                stack.add(c);
            } else { //右括号
                if (stack.isEmpty()) return false; //栈为空则为无效
                if (c != map.get(stack.pop())) return false;
            }
        }
        return stack.isEmpty();
    }
}
