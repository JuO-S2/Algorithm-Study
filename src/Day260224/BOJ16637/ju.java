package Day260224.BOJ16637;

import java.io.*;

public class ju {
    static char[] ops;
    static int[] nums;
    static int answer;
    static int opsSize;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String input = br.readLine();
        opsSize = (N - 1) / 2;
        ops = new char[(N - 1) / 2];
        nums = new int[(N - 1) / 2 + 1];

        nums[0] = Character.getNumericValue(input.charAt(0));
        for (int i = 1; i < N; i += 2) {
            ops[(i - 1) / 2] = input.charAt(i);
            nums[(i + 1) / 2] = Character.getNumericValue(input.charAt(i + 1));
        }

        answer = Integer.MIN_VALUE;
        dfs(0, nums[0]);
        System.out.println(answer);
        br.close();
    }

    static void dfs(int opIdx, int now) {
        if (opIdx == opsSize) {
            answer = Math.max(answer, now);
            return;
        }

        // 괄호 없음
        int nonParent = calc(opIdx, now, nums[opIdx+1]);
        dfs(opIdx+1, nonParent);

        // 다음 연산에 괄호 적용
        if(opIdx < opsSize - 1) {
            int parent = calc(opIdx + 1, nums[opIdx + 1], nums[opIdx + 2]);
            dfs(opIdx + 2, calc(opIdx, now, parent));
        }
    }

    static int calc(int opIdx, int n1, int n2) {
        switch (ops[opIdx]) {
            case '+':
                return n1 + n2;
            case '-':
                return n1 - n2;
            case '*':
                return n1 * n2;
        }
        return -1;
    }
}