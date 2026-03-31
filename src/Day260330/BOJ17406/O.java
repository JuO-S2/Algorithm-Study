package Day260330.BOJ17406;

import java.util.*;
import java.io.*;

public class O {

    static int[][] array, copyArray,realArray;
    static int width, height, commandSize,min;
    static int[][] command;
    static boolean[] visited;
    static int[] select;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        height = Integer.parseInt(st.nextToken());
        width = Integer.parseInt(st.nextToken());
        commandSize = Integer.parseInt(st.nextToken());
        array = new int[height][width];
        copyArray = new int[height][width];
        realArray = new int[height][width];
        command = new int[commandSize][3];
        visited = new boolean[commandSize];
        select = new int[commandSize];
        min = Integer.MAX_VALUE;

        for (int i = 0; i < height; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < width; j++) {
                realArray[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < commandSize; i++) {
            st = new StringTokenizer(br.readLine());
            command[i][0] = Integer.parseInt(st.nextToken())-1;
            command[i][1] = Integer.parseInt(st.nextToken())-1;
            command[i][2] = Integer.parseInt(st.nextToken());
        }

        dfs(0);
        System.out.println(min);

    }

    static public void dfs(int depth){

        if(depth==commandSize){
            for(int i=0; i<array.length; i++){
                array[i]= Arrays.copyOf(realArray[i],realArray[i].length);
                copyArray[i]= Arrays.copyOf(realArray[i],realArray[i].length);
            }

            for(int i=0; i<commandSize; i++){
                int y = command[select[i]][0];
                int x = command[select[i]][1];
                int range = command[select[i]][2];
                rotate(x,y,range,0);
            }


            for (int[] ints : array) {
                int sum=0;
                for(int i : ints)
                {
                    sum+=i;
                }
                min = Math.min(sum,min);

            }

            return;
        }

        for(int i=0; i<commandSize; i++){
            if(visited[i]){
                continue;
            }

            visited[i]=true;
            select[depth]=i;
            dfs(depth+1);
            visited[i]=false;
        }

    }

    static public void rotate(int centerX, int centerY, int range,int depth) {

        if(depth==range+1){
            return;
        }

        for(int i=centerX-depth; i<centerX+depth; i++){
             copyArray[centerY-depth][i+1]= array[centerY-depth][i];
        }

        for(int i=centerY-depth; i<centerY+depth; i++){
            copyArray[i+1][centerX+depth]= array[i][centerX+depth];
        }

        for(int i=centerX+depth; i>centerX-depth; i--){
            copyArray[centerY+depth][i-1]= array[centerY+depth][i];
        }

        for(int i=centerY+depth; i>centerY-depth; i--){
            copyArray[i-1][centerX-depth]= array[i][centerX-depth];
        }

        for(int i=0; i<array.length; i++){
            array[i]= Arrays.copyOf(copyArray[i],copyArray[i].length);
        }

        rotate(centerX, centerY, range, depth+1);

    }
}
