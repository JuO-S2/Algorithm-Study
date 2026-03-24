package Day260324.BOJ17281;

import java.io.*;
import java.util.*;

public class Ju {
    static int[][] scores;
    static int[] players;
    static boolean[] selected, ground;
    static int N, answer;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        scores = new int[N][9];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                scores[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        answer = 0;
        selected = new boolean[9];
        players = new int[9];
        players[3] = 0;
        ground = new boolean[3];

        selectPlayer(0);

        System.out.println(answer);
    }

    static void selectPlayer(int idx) {
        if (idx == 9) {
            play(0, 0, 0);
            return;
        }
        if (idx == 3) {
            selectPlayer(idx + 1);
            return;
        }

        for (int i = 1; i < 9; i++) {
            if (!selected[i]) {
                selected[i] = true;
                players[idx] = i;

                selectPlayer(idx + 1);

                selected[i] = false;
            }
        }
    }

    static void play(int round, int score, int playerIdx) {
        if (N == round) {
            if (answer < score)
                answer = score;
            return;
        }

        int outCnt = 0;
        ground[0] = false;
        ground[1] = false;
        ground[2] = false;

        while (outCnt < 3) {
            switch (scores[round][players[playerIdx]]) {
                case 0:
                    outCnt++;
                    break;
                case 1:
                    if (ground[2]) score++;
                    ground[2] = ground[1];
                    ground[1] = ground[0];
                    ground[0] = true;
                    break;
                case 2:
                    if (ground[2]) score++;
                    if (ground[1]) score++;
                    ground[2] = ground[0];
                    ground[0] = false;
                    ground[1] = true;
                    break;
                case 3:
                    if (ground[2]) score++;
                    if (ground[1]) score++;
                    if (ground[0]) score++;
                    ground[2] = true;
                    ground[1] = false;
                    ground[0] = false;
                    break;
                case 4:
                    if (ground[2]) score++;
                    if (ground[1]) score++;
                    if (ground[0]) score++;
                    score++;
                    ground[0] = false;
                    ground[1] = false;
                    ground[2] = false;
                    break;
            }
            playerIdx++;
            if (playerIdx == 9) {
                playerIdx = 0;
            }
        }

        play(round + 1, score, playerIdx);
    }
}
