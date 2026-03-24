package Day260324.BOJ17281;

import java.io.*;
import java.util.*;

public class O {

    static int[][] result;
    static int[] select;
    static boolean[] visited;
    static int N;
    static int score;
    static int[] inGround;
    static int outCount;
    static int max;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        select = new int[9];
        visited = new boolean[9];
        inGround = new int[3];
        max = 0;

        N = Integer.parseInt(st.nextToken());
        result = new int[N][9];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 9; j++) {
                result[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0);

        System.out.println(max);

    }

    public static void dfs(int depth) {
        if (depth == 9) {
            play();
            return;
        }
        if( depth == 3){
            dfs(depth+1);
            return ;
        }

        for (int i = 1; i < 9; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            select[depth] = i;
            dfs(depth + 1);
            visited[i] = false;

        }
    }

    static void play() {
        score =0 ;
        int player = 0;

        for (int i = 0; i < N; i++) {
            outCount = 0;
            while (outCount != 3) {
                if (result[i][select[player]] == 1) {
                    score += hit(1);
                } else if (result[i][select[player]] == 2) {
                    score += hit(2);
                } else if (result[i][select[player]] == 3) {
                    score += hit(3);
                } else if (result[i][select[player]] == 4) {
                    score += hit(4);
                } else if (result[i][select[player]] == 0) {
                    outCount++;
                }

                if (player + 1 == 9) {
                    player = 0;
                }else{
                    player += 1;
                }

            }

            inGround[0]=0;
            inGround[1]=0;
            inGround[2]=0;
        }

        if(max < score){
            max = score;
        }

    }

    static int hit(int distance) {
        int []newInGround = new int[7];
        int hitScore = 0;

        for (int i = 0; i < 3; i++) {
            if (inGround[i] == 1) {
                newInGround[i+distance]=1;
            }
        }

        for(int i=3; i<7; i++){
            if(newInGround[i]==1){
                hitScore++;
            }
        }

        for(int i=0; i<3; i++){
            inGround[i]=newInGround[i];
        }

        if(distance !=4){
            inGround[distance-1]=1;
        }else{
            hitScore++;
        }

        return hitScore;

    }
}
