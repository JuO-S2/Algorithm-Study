package Day260303.BOJ17136;

import java.util.*;
import java.io.*;

public class O {

    static int[][] map;
    static int[] paperCnt;
    static int min;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        map = new int[10][10];
        paperCnt = new int[]{0, 5, 5, 5, 5, 5};
        min = Integer.MAX_VALUE;

        for (int i = 0; i < 10; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 10; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        Dfs(0, 0, 0);

        System.out.println(min == Integer.MAX_VALUE ? -1 : min);

    }

    private static void Dfs(int x, int y, int cnt) {
        if (x > 9 && y >= 9) {
            min = Math.min(cnt, min);
            return;
        }

        if (min <= cnt) {
            return;
        }

        if (x > 9) {
            Dfs(0, y + 1, cnt);
            return;
        }

        if (map[y][x] == 1) {
            for (int i = 5; i >= 1; i--) {
                if (isAttachPaper(x, y, i) && paperCnt[i] > 0) {
                    fillPaper(x, y, i, 0);
                    paperCnt[i]--;
                    Dfs(x + 1, y, cnt + 1);
                    fillPaper(x, y, i, 1);
                    paperCnt[i]++;
                }
            }
        } else {
            Dfs(x + 1, y, cnt);
        }

    }


    static boolean isAttachPaper(int startX, int startY, int size) {
        for (int i = startY; i < startY + size; i++) {
            for (int j = startX; j < startX + size; j++) {
                if (i < 0 || i >= 10 || j < 0 || j >= 10) {
                    return false;
                }
                if (map[i][j] != 1) {
                    return false;
                }
            }
        }
        return true;
    }

    static void fillPaper(int startX, int startY, int size, int flag) {
        for (int i = startY; i < startY + size; i++) {
            for (int j = startX; j < startX + size; j++) {
                map[i][j] = flag;
            }
        }
    }
}
