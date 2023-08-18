package 알고리즘.다이나믹프로그래밍.boj_11727_2xn_타일링_2;

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
        // 동적 계획법(dp) 문제는 점화식을 배열로 저장하면서 bottom-up 방식으로 for문을 둘려 n번 째 값을 찾는 것
        int[] dp = new int[1001]; // 런타임 에러 (ArrayIndexOutOfBounds) 해결을 위해 n + 1을 1001로 변경
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 3;

        for (int i = 3; i <= n; i++) {
            dp[i] = (dp[i - 1] + 2* dp[i - 2]) % 10007 ; // 10007를 나눈 나머지를 저장하지 않으면 오버플로우가 발생하여 잘못된 값이 들어감
        }
        bw.write(String.valueOf(dp[n]));
        bw.flush();
        bw.close();
        br.close();
    }
}
