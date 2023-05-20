package algorithm.dynamicProgramming.boj_1463_1로_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        int[] cntArr = new int[n + 1];
        cntArr[0] = 0;
        cntArr[1] = 0;

        for (int i = 2; i <= n; i++) {
            cntArr[i] = cntArr[i - 1] + 1;
            if (i % 2 == 0) {
                cntArr[i] = Math.min(cntArr[i], cntArr[i / 2] + 1);
            }
            if (i % 3 == 0) {
                cntArr[i] = Math.min(cntArr[i], cntArr[i / 3] + 1);
            }
        }

        bw.write(String.valueOf(cntArr[n]));
        bw.flush();
        bw.close();
        br.close();
    }
}
