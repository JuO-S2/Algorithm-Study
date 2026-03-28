package Day260328.BOJ3954;

import java.util.*;
import java.io.*;

public class O {

    static char[] cmd;
    static char[] str;
    static int[] array;
    static int[] open, close;
    static int result;
    static int arraySize, cmdSize, stringSize;
    static boolean isLoop;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int tcLen = Integer.parseInt(st.nextToken());

        for (int tc = 0; tc < tcLen; tc++) {

            st = new StringTokenizer(br.readLine());
            arraySize = Integer.parseInt(st.nextToken());
            cmdSize = Integer.parseInt(st.nextToken());
            stringSize = Integer.parseInt(st.nextToken());
            isLoop = false;
            result = 0;

            cmd = new char[cmdSize];
            str = new char[stringSize];
            array = new int[arraySize];

            st = new StringTokenizer(br.readLine());
            cmd = st.nextToken().toCharArray();
            st = new StringTokenizer(br.readLine());
            str = st.nextToken().toCharArray();

            open = new int[cmdSize];
            close = new int[cmdSize];

            Stack<Integer> stack = new Stack<>();
            for (int i = 0; i < cmdSize; i++) {
                if (cmd[i] == '[') {
                    stack.push(i);
                } else if (cmd[i] == ']') {
                    int tempOpen = stack.pop();
                    open[tempOpen] = i;
                    close[i] = tempOpen;
                }
            }

            parseCmd();

            if (isLoop) {
                System.out.println("Loops " + close[result] + " " + result);
            } else {
                System.out.println("Terminates");
            }


        }
    }

    static public void parseCmd() {
        int cmdIndex = 0;
        int pointer = 0;
        int stringIndex = 0;
        long count = 0;
        while (cmdIndex < cmdSize) {
            switch (cmd[cmdIndex]) {
                case '-':
                    array[pointer] = (array[pointer] + 255) % 256;
                    cmdIndex++;
                    break;
                case '+':
                    array[pointer] = (array[pointer] + 1) % 256;
                    cmdIndex++;
                    break;
                case '<':
                    pointer = pointer == 0 ? arraySize - 1 : pointer - 1;
                    cmdIndex++;
                    break;
                case '>':
                    pointer = pointer == arraySize - 1 ? 0 : pointer + 1;
                    cmdIndex++;
                    break;
                case '[':
                    if (array[pointer] == 0) {
                        cmdIndex = open[cmdIndex];
                        cmdIndex++;
                    } else {
                        cmdIndex++;
                    }
                    break;
                case ']':
                    if (array[pointer] != 0) {

                        if (count > 50_000_000) {
                            result = Math.max(result, cmdIndex);
                        }
                        cmdIndex = close[cmdIndex];
                    }
                    cmdIndex++;
                    break;
                case '.':
                    cmdIndex++;
                    break;
                case ',':
                    if (stringIndex >= stringSize) {
                        array[pointer] = 255;
                    } else {
                        array[pointer] = str[stringIndex];
                    }
                    stringIndex++;
                    cmdIndex++;
                    break;
                default:
                    continue;
            }
            if (count > 1_0000_0000) {
                isLoop = true;
                break;
            }
            count++;
        }
    }
}
