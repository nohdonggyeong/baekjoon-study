package 알고리즘.그리디.동전_0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static Integer[] coins;
	static int count;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			coins = new Integer[N];
			for (int n = 0; n < N; n++) {
				coins[n] = Integer.parseInt(br.readLine());
			}
			Arrays.sort(coins, Collections.reverseOrder());
			
			count = 0;
			for (int n = 0; n < N; n++) {
				if (coins[n] > 1 && K >= coins[n]) {
					count += K / coins[n];
					K %= coins[n];
				}
			}
			count += K;
			
			bw.write(String.valueOf(count));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
