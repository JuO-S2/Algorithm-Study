package Day260224.BOJ17070;

import java.io.*;
import java.util.*;

public class ju {
    static boolean[][] map;
    static int answer;
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Objects.equals(st.nextToken(), "0");
            }
        }

        N --;
        answer = 0;
        algo(0, 1, 0);
        System.out.println(answer);

        br.close();
    }

    static void algo(int pipeX, int pipeY, int status) {
        if(!map[pipeX][pipeY]) return;
        if (pipeX == N && pipeY == N) {
            answer ++;
            return;
        }
        switch (status) {
            // 가로
            case 0:
                if (pipeY < N) {
                    algo(pipeX, pipeY + 1, 0);
                    if (pipeX < N && map[pipeX + 1][pipeY] && map[pipeX][pipeY+1])
                        algo(pipeX + 1, pipeY + 1, 2);
                }
                return;
            // 세로
            case 1:
                if (pipeX < N) {
                    algo(pipeX + 1, pipeY, 1);
                    if (pipeY < N && map[pipeX + 1][pipeY] && map[pipeX][pipeY+1])
                        algo(pipeX + 1, pipeY + 1, 2);
                }
                return;
            // 대각선
            case 2:
                if (pipeY < N)
                    algo(pipeX, pipeY + 1, 0);
                if (pipeX < N) {
                    algo(pipeX + 1, pipeY, 1);
                    if (pipeY < N && map[pipeX + 1][pipeY] && map[pipeX][pipeY+1])
                        algo(pipeX + 1, pipeY + 1, 2);
                }
        }
    }
}
