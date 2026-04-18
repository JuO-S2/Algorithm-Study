package Day260418.BOJ17406;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Ju {
    static int N, M, K, answer;
    static int[][] A, revolvedA, command;
    static boolean[] picked;
    static int[] pick;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        A = new int[N][M];
        revolvedA = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        command = new int[K][3];
        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken()) - 1;
            command[i][1] = Integer.parseInt(st.nextToken()) - 1;
            command[i][2] = Integer.parseInt(st.nextToken());
        }

        answer = Integer.MAX_VALUE;
        picked = new boolean[K];
        pick = new int[K];
        order(0);

        System.out.println(answer);
    }

    static void order(int idx) {
        if (idx == K) {
            solve();
            return;
        }

        for (int i = 0; i < K; i++) {
            if (!picked[i]) {
                picked[i] = true;
                pick[idx] = i;
                order(idx + 1);
                picked[i] = false;
            }
        }
    }

    static void solve() {
        for(int i=0; i<N; i++) {
            System.arraycopy(A[i], 0, revolvedA[i], 0, M);
        }

        for(int i=0; i<K; i++) {
            int topLeftY = command[pick[i]][0] - command[pick[i]][2];
            int topLeftX = command[pick[i]][1] - command[pick[i]][2];

            revolve(topLeftY, topLeftX, command[pick[i]][2]*2+1);
        }

        findMinLine();
    }

    static void revolve(int Y, int X, int len){
        for(int i=len; 1 < i; i-=2) {
            int start = revolvedA[Y+1][X];

            // 좌(아래로)
            for(int j=Y+1; j<Y+i-1; j++) {
               revolvedA[j][X] = revolvedA[j+1][X];
            }
            // 하(오른쪽으로)
            for(int j=X; j<X+i-1; j++) {
                revolvedA[Y+i-1][j] = revolvedA[Y+i-1][j+1];
            }
            // 우(위쪽으로)
            for(int j=Y+i-1; Y < j; j--){
                revolvedA[j][X+i-1] = revolvedA[j-1][X+i-1];
            }
            // 상(왼쪽으로)
            for(int j=X+i-1; X < j; j--){
                revolvedA[Y][j] = revolvedA[Y][j-1];
            }

            revolvedA[Y][X] = start;
            Y++;
            X++;
        }
    }

    static void findMinLine(){
        int sum = 0;
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++){
                sum += revolvedA[i][j];
            }

            if(sum < answer) answer = sum;
            sum = 0;
        }
    }
}
