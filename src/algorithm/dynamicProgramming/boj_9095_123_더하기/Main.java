package algorithm.dynamicProgramming.boj_9095_123_더하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());
        for (int j = 0; j < num; j++) {
            int n = Integer.parseInt(br.readLine());
            // 런타임 에러 (ArrayIndexOutOfBounds) 해결하기 위해 배열 크기를 12로 설정
            int[] dpArr = new int[12];
            dpArr[0] = 1;
            dpArr[1] = 1;
            dpArr[2] = 2;
    
            for (int i = 3; i <= n; i++) {
                dpArr[i] = dpArr[i - 3] + dpArr[i - 2] + dpArr[i - 1];
            }
    
            bw.write(String.valueOf(dpArr[n]));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
