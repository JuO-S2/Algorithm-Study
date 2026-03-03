package Day260227.BOJ17135;

import java.io.*;
import java.util.*;

public class Ju {
    static boolean[][] map;
    static int N, M, D;
    static int answer, count, n;
    static int[] picked = new int[3];
    static int[][] now;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        D = Integer.parseInt(st.nextToken());
        map = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                if (st.nextToken().equals("1")) {
                    map[i][j] = true;
                }
            }
        }

        answer = 0;
        pick(0,0);
        n = N;

        System.out.println(answer);
    }

    static void pick(int cnt, int idx){
        if(cnt == 3){
            count = 0;
            proceed();
            answer = Math.max(answer, count);
            return;
        }

        for(int i=idx; i<M; i++){
            picked[cnt] = i;
            pick(cnt+1, i+1);
        }
    }

    // 궁수 세 명이 공격
    static void proceed() {
        now = new int[N][M];

        for (int i = N; 0 < i; i--) {
            for(int pick: picked){
                int kill = findTarget(i, pick);
                if(kill == -1) continue;
                count += kill;
            }
        }
    }

    static int findTarget(int x, int y) {
        ArrayDeque<int[]> queue = new ArrayDeque<>();
        queue.add(new int[] {x-1, y});
        int n, m;
        while(!queue.isEmpty()){
            int[] target = queue.poll();
            n = target[0];
            m = target[1];
            int d = Math.abs(x-n)+Math.abs(y-m);

            // 타겟 발견
            if(map[n][m]){
                if(now[n][m] == x){
                    return 0;
                }
                if(now[n][m] == 0) {
                    now[n][m] = x;
                    return 1;
                }
            }

            if(d < D) {
                if (0 <= m - 1 && m<=y) queue.add(new int[] {n,m-1});
                if(0 <= n-1 && m == y) queue.add(new int[] {n-1, m});
                if(m+1 < M && y <= m) queue.add(new int[] {n, m+1});
            }
        }
        return -1;
    }
}
