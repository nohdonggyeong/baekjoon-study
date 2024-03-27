package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_14501_퇴사;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, T, P;
    static ManDay[] manDays;

    static int[] dp;
    static int maxResult;

    static class ManDay {
        int time;
        int profit;

        ManDay(int time, int profit) {
            this.time = time;
            this.profit = profit;
        }
    }

    static void getMaxProfit() {
    	for (int n = 0; n < N; n++) {
    		if (n + manDays[n].time <= N) {
    			dp[n + manDays[n].time] = Math.max(dp[n + manDays[n].time], dp[n] + manDays[n].profit);
    		}
    		dp[n + 1] = Math.max(dp[n + 1], dp[n]);
    	}
    }

    public static void main(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st;

            N = Integer.parseInt(br.readLine());

            manDays = new ManDay[N];
            for (int n = 0; n < N; n++) {
                st = new StringTokenizer(br.readLine());
                T = Integer.parseInt(st.nextToken());
                P = Integer.parseInt(st.nextToken());
                manDays[n] = new ManDay(T, P);
            }

            dp = new int[N + 1];
            getMaxProfit();
            maxResult = dp[N];
            
            bw.write(maxResult);
            bw.flush();
        }
    }
}
