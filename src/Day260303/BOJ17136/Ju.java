package Day260303.BOJ17136;

import java.io.*;
import java.util.*;

public class Ju {
    static boolean[][] map;
    static int[] paperCnt = new int[]{0, 5, 5, 5, 5, 5};
    static int maxSize, answer, N = 10;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        answer = Integer.MAX_VALUE;
        map = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                if (st.nextToken().equals("1"))
                    map[i][j] = true;
            }
        }

        solve(0, 0, 0);
        System.out.println(answer==Integer.MAX_VALUE?-1:answer);
    }

    static void solve(int totalCnt, int n, int m) {
        if (answer <= totalCnt) {
            return;
        }
        if (m == 10) {
            if (n == 9) {
                answer = totalCnt;
                return;
            }
            m = 0;
            n += 1;
        }

        if (map[n][m]) {
            maxSize = 1;
            setMaxSize(2, n, m);
            for (int k = maxSize; 0 < k; k--) {
                if (0 < paperCnt[k]) {
                    fill(n, m, k, false);
                    paperCnt[k]--;
                    solve(totalCnt + 1, n, m + 1);
                    fill(n, m, k, true);
                    paperCnt[k]++;
                }
            }
            return;
        }
        solve(totalCnt, n, m + 1);
    }

    static void setMaxSize(int size, int n, int m) {
        int pin = size - 1;
        if (n + pin == 10 || m + pin == 10) return;
        for (int i = 0; i < size; i++) {
            if (!map[n + i][m + pin] || !map[n + pin][m + i]) {
                return;
            }
        }

        maxSize = size;
        if (size < 5)
            setMaxSize(size + 1, n, m);
    }

    static void fill(int n, int m, int size, boolean status) {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[n + i][m + j] = status;
            }
        }
    }
}
