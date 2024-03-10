package backjoon.유형별로_풀어보기.다이나믹프로그래밍.boj_11052_카드_구매하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        int n = Integer.parseInt(br.readLine());
        int[] inputArr = new int[n + 1];

        String input = br.readLine();
        StringTokenizer st = new StringTokenizer(input);
        for (int i = 1; i <= n; i++) {
            inputArr[i] = Integer.parseInt(st.nextToken());
        }

        int[] dpArr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                dpArr[i] = Math.max((inputArr[j] + dpArr[i - j]), dpArr[i]);
            }
        }

        bw.write(String.valueOf(dpArr[n]));
        bw.flush();
        bw.close();
        br.close();
    }
}
