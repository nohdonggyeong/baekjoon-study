package backjoon.알고리즘_분류.브루트포스.boj_2309_일곱_난쟁이;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int sum = 0;
        int[] heightArr = new int[9];
        for (int i = 0; i < 9; i++) {
            heightArr[i] = Integer.parseInt(br.readLine());
            sum += heightArr[i];
        }

        loopOut:
        for (int i = 0; i < 8; i++) {
            for (int j = i + 1; j < 9; j++) {
                if (sum - heightArr[i] - heightArr[j] == 100) {
                    heightArr[i] = 0;
                    heightArr[j] = 0;

                    break loopOut;
                }
            }
        }

        Arrays.sort(heightArr);
        for (int i = 2; i < 9; i++) {
            bw.write(String.valueOf(heightArr[i]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
