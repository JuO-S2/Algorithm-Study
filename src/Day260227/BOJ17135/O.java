package Day260227.BOJ17135;

import java.io.*;
import java.util.*;

public class O {

    static int maxCount;
    static int count;
    static int[] location;
    static boolean[][] visited;
    static int height, width, range;
    static int[][] map, tempMap;
    static int[] dx = {-1, 0, 1};
    static int[] dy = {0, -1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = 0;
        width = 0;
        range = 0;
        count = 0;
        maxCount = 0;
        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        range = Integer.parseInt(st.nextToken());

        location = new int[3];
        map = new int[height][width];
        tempMap = new int[height][width];

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                tempMap[i][j] = map[i][j];
            }
        }

        dfs(0, 0);

        System.out.println(maxCount);
    }

    public static void dfs(int start, int depth) {
        if (depth == 3) {
            count = 0;
            // 궁수 1번 2번 3번 차례대로 가장 가까운 적 공격 로직 , range 에 따라서
            // 1. attack 2. move
            map = new int[height][width];
            for (int i = 0; i < height; i++) {
                map[i] = Arrays.copyOf(tempMap[i], width);
            }
            do {
                // 궁수 1,2,3 공격
                for (int i = 0; i < 3; i++) {
                    attack(location[i]);
                }

            }
            // 병사 이동
            while (!move());

            maxCount = Math.max(count, maxCount);

            return;
        }

        for (int i = start; i < width; i++) {
            location[depth] = i;
            dfs(i + 1, depth + 1);
        }

    }

    public static boolean move() {
        boolean isLast = true;

        for (int i = height - 1; i >= 0; i--) {
            for (int j = width - 1; j >= 0; j--) {
                // 병사가 있다면
                if (map[i][j] == 1) {
                    // 병사가 있고, 마지막 병사라면 없애버림
                    if (i == height - 1) {
                        map[i][j] = 0;
                    }
                    // 병사를 전진시킴
                    else {
                        map[i][j] = 0;
                        map[i + 1][j] = 1;
                    }
                    isLast = false;
                }

                // 공격당한 병사라면 count 증가 후 0으로 변경
                if (map[i][j] != 0) {
                    count++;
                    map[i][j] = 0;
                }
            }
        }
        return isLast;
    }


    public static void attack(int archor) {
        Queue<int[]> q = new LinkedList<>();
        visited = new boolean[height][width];

        if (map[height - 1][archor] != 0) {
            map[height - 1][archor]++;
            return;
        }

        visited[height - 1][archor] = true;
        q.add(new int[]{height - 1, archor, 1});

        while (!q.isEmpty()) {

            int[] cur = q.poll();

            if (cur[2] > range) {
                return;
            }

            if (map[cur[0]][cur[1]] != 0) {
                map[cur[0]][cur[1]]++;
                return;
            }

            for (int i = 0; i < 3; i++) {
                int attackX = dx[i] + cur[1];
                int attackY = dy[i] + cur[0];

                if (attackX < 0 || attackX >= width || attackY < 0 || attackY >= height) {
                    continue;
                }

                if (visited[attackY][attackX]) {
                    continue;
                }

                visited[attackY][attackX] = true;
                q.offer(new int[]{attackY, attackX, cur[2] + 1});
            }
        }
    }
}
