import java.util.*;

class Solution {

    private String s;
    private int index;

    public List<String> braceExpansionII(String expression) {
        s = expression;
        index = 0;

        Set<String> result = parseExpression();

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles union: a,b,c
    private Set<String> parseExpression() {
        Set<String> result = parseTerm();

        while (index < s.length() && s.charAt(index) == ',') {
            index++; // skip ','

            Set<String> next = parseTerm();
            result.addAll(next);
        }

        return result;
    }

    // Handles concatenation: ab{c,d}
    private Set<String> parseTerm() {
        Set<String> result = new HashSet<>();
        result.add("");

        while (index < s.length()
                && s.charAt(index) != '}'
                && s.charAt(index) != ',') {

            Set<String> next;

            char ch = s.charAt(index);

            if (ch == '{') {
                index++; // skip '{'

                next = parseExpression();

                index++; // skip '}'
            } else {
                next = new HashSet<>();
                next.add(String.valueOf(ch));
                index++;
            }

            result = combine(result, next);
        }

        return result;
    }

    // Cartesian product + concatenation
    private Set<String> combine(Set<String> a, Set<String> b) {
        Set<String> result = new HashSet<>();

        for (String x : a) {
            for (String y : b) {
                result.add(x + y);
            }
        }

        return result;
    }
}