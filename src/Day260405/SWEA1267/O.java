package Day260405.SWEA1267;

import java.util.*;
import java.io.*;

public class O {

    static int V;
    static boolean [][]road;
    static boolean []visited;

    public static void main(String args[]) throws Exception
    {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st ;


        for(int test_case = 1; test_case <= 10; test_case++)
        {
            st = new StringTokenizer(br.readLine(), " ");
            V = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());
            road = new boolean[V+1][V+1];
            visited = new boolean[V+1];

            st = new StringTokenizer(br.readLine(), " ");
            for(int i=0; i<E; i++){
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                road[from][to] = true;
            }

            System.out.print("#" +test_case + " ");

            for(int i=1; i<=V; i++){
                if(!visited[i]){
                    dfs(i);
                }
            }
            System.out.println();
        }
    }

    static void dfs(int vertex){
        visited[vertex]= true;

        for(int i=1; i<V; i++){
            if(visited[i]){
                continue;
            }
            if(!road[i][vertex]){
                continue;
            }
            dfs(i);
        }

        System.out.print(vertex + " ");
    }
}
