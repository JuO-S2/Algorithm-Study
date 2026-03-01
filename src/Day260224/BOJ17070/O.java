package Day260224.BOJ17070;

import java.io.*;
import java.util.*;

public class O {

    static int[][] map;
    static int N;
    static boolean flag;
    static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        flag = false;
        count = 0;
        map[0][0] = 2;
        map[0][1] = 2;

        dfs(1, 0, 0, 0, 0);

        System.out.println(count);

    }

    public static void dfs(int headX, int headY, int tailX, int tailY, int curDir) {
        if (headX == N - 1 && headY == N - 1) {
            count++;
            return;
        }

        if (curDir == 0) {
            moveRight(headX, headY, tailX, tailY, curDir);
            moveCross(headX, headY, tailX, tailY, curDir);
        } else if (curDir == 1) {
            moveRight(headX, headY, tailX, tailY, curDir);
            moveDown(headX, headY, tailX, tailY, curDir);
            moveCross(headX, headY, tailX, tailY, curDir);
        } else if (curDir == 2) {
            moveDown(headX, headY, tailX, tailY, curDir);
            moveCross(headX, headY, tailX, tailY, curDir);
        }
    }

    public static void moveRight(int headX, int headY, int tailX, int tailY, int curDir) {
        if (headX == N - 1 && headY == N - 1) {
            count++;
            return;
        }

        if (headX + 1 >= N || map[headY][headX + 1] != 0) {
            return;
        }


        map[tailY][tailX] = 0;
        map[headY][headX + 1] = 2;
        dfs(headX + 1, headY, headX, headY, 0);
        map[tailY][tailX] = 2;
        map[headY][headX + 1] = 0;

    }

    public static void moveDown(int headX, int headY, int tailX, int tailY, int curDir) {
        if (headX == N - 1 && headY == N - 1) {
            count++;
            return;
        }

        if (headY + 1 >= N || map[headY + 1][headX] != 0) {
            return;
        }

        map[tailY][tailX] = 0;
        map[headY + 1][headX] = 2;
        dfs(headX, headY + 1, headX, headY, 2);
        map[tailY][tailX] = 2;
        map[headY + 1][headX] = 0;
    }

    public static void moveCross(int headX, int headY, int tailX, int tailY, int curDir) {
        if (headX == N - 1 && headY == N - 1) {
            count++;
            return;
        }

        if (headY + 1 >= N || headX + 1 >= N) {
            return;
        }


        if (map[headY + 1][headX] != 0 || map[headY][headX + 1] != 0 || map[headY + 1][headX + 1] != 0) {
            return;
        }

        map[tailY][tailX] = 0;
        map[headY + 1][headX + 1] = 2;
        dfs(headX + 1, headY + 1, headX, headY, 1);
        map[tailY][tailX] = 2;
        map[headY + 1][headX + 1] = 0;

    }


}
