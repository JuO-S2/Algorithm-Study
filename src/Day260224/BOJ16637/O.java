package Day260224.BOJ16637;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class O {

    static BufferedReader br;
    static StringTokenizer st;
    static int N;
    static int max;


    public static void main(String[] args) throws Exception {

        LinkedList<Integer> nums = new LinkedList<>();
        LinkedList<Character> ops = new LinkedList<>();

        br = new BufferedReader(new InputStreamReader(System.in));
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        max = Integer.MIN_VALUE;


        st = new StringTokenizer(br.readLine());
        char[] input = st.nextToken().toCharArray();
        for (int i = 0; i < N; i++) {
            if (i % 2 == 0) {
                nums.add(input[i] - '0');
            } else {
                ops.add(input[i]);
            }
        }

        // 1. 괄호 씌우고 시작
        dfs(0, 1, nums, ops);
        // 2. 괄호 없이 시작
        dfs(0, 0, nums, ops);

        System.out.println(max);

    }

    static void dfs(int x, int flag, LinkedList<Integer> nums, LinkedList<Character> ops) {
        LinkedList<Character> o = new LinkedList<>(ops);
        LinkedList<Integer> n = new LinkedList<>(nums);

        if (x > o.size() - 1 || x > (N/2) - 1) {
            while (n.size() > 1) {
                calc(n, o, 0);
            }
            max = Math.max(max, n.get(0));
            return;
        }

        if(flag == 1) {
            calc(n, o, x);
        }

        dfs(x + 1, 1, n, o);
        dfs(x + 1, 0, n, o);

    }

    static void calc(LinkedList<Integer> n, LinkedList<Character> o, int idx) {
        int num1 = n.remove(idx);
        int num2 = n.remove(idx);
        char op = o.remove(idx);

        if (op == '+') {
            n.add(idx,num1 + num2);
        } else if (op == '-') {
            n.add(idx,num1 - num2);
        } else if (op == '*') {
            n.add(idx,num1 * num2);
        }
    }

}
