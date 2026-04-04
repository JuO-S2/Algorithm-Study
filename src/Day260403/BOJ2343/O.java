package Day260403.BOJ2343;

import java.io.*;
import java.util.*;

public class O {

    static int[] array, temp;
    static int min, sliceCnt, arraySize;

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        arraySize = Integer.parseInt(st.nextToken());
        sliceCnt = Integer.parseInt(st.nextToken());

        array = new int[arraySize];

        int start = 0, end = 0, mid = 0;

        min = Integer.MIN_VALUE;

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < arraySize; i++) {
            array[i] = Integer.parseInt(st.nextToken());
            end += array[i];
            start = Math.max(start, array[i]);
        }

        while (start <= end) {
            mid = (start + end) / 2;

            int slice = howManySlice(mid);
            if (slice <= sliceCnt){
                end  =  mid -1 ;
            }else{
                start = mid + 1;
            }
        }
        System.out.println(start);
    }

    static int howManySlice(int size) {
        int sum = 0;
        int count = 1;
        for (int i = 0; i < arraySize; i++) {
            sum += array[i];

            if(sum>size){
                count++;
                sum = array[i];
            }
        }
        return count;
    }

}
