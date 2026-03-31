package Day260328.BOJ3954;

import java.io.*;
import java.util.*;

public class Ju {
    static int M, C, I, count;
    static char[] script, input;
    static int[] data, couple;
    static int loopS, loopE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < N; tc++) {
            st = new StringTokenizer(br.readLine());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            I = Integer.parseInt(st.nextToken());

            data = new int[M];

            script = new char[C];
            couple = new int[C];
            String str = br.readLine();
            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < C; i++) {
                script[i] = str.charAt(i);

                if (script[i] == '[') {
                    stack.push(i);
                } else if (script[i] == ']') {
                    couple[i] = stack.pop();
                    couple[couple[i]] = i;
                }
            }

            input = new char[I];
            str = br.readLine();
            for (int i = 0; i < I; i++) {
                input[i] = str.charAt(i);
            }

            inputIdx = 0;
            pointer = 0;
            count = 0;
            scriptIdx = 0;
            while (count <= 50000000 && scriptIdx < C) {
                proceedProgram();
            }

            if (count == 50000001) {
                loopS = scriptIdx;
                loopE = scriptIdx;
                count = 0;
                while (count < 50000000) {
                    proceedProgram();
                    if (scriptIdx < loopS) loopS = scriptIdx;
                    else if (loopE < scriptIdx) loopE = scriptIdx;
                }

                sb.append("Loops ").append(loopS - 1).append(" ").append(loopE).append("\n");
            } else {
                sb.append("Terminates\n");
            }
        }
        System.out.println(sb);
        br.close();
    }

    static int pointer, inputIdx, scriptIdx;

    static void proceedProgram() {
        count++;

        switch (script[scriptIdx]) {
            case '-':
                data[pointer] = data[pointer] == 0 ? 255 : data[pointer]-1;
                break;
            case '+':
                data[pointer] = data[pointer]==255? 0 : data[pointer]+1;
                break;
            case '<':
                pointer = pointer==0 ? M-1 : pointer-1;
                break;
            case '>':
                pointer = pointer == M-1 ? 0 : pointer + 1;
                break;
            case '[':
                if (data[pointer] == 0) {
                    scriptIdx = couple[scriptIdx] + 1;
                    return;
                }
                break;
            case ']':
                if (data[pointer] != 0) {
                    scriptIdx = couple[scriptIdx] + 1;
                    return;
                }
                break;
            case '.':
                break;
            case ',':
                data[pointer] = inputIdx == I ? 255 : input[inputIdx];
                inputIdx++;
                break;
        }
        scriptIdx++;
    }
}
