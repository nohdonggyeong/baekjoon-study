package backjoon.유형별로_풀어보기.그리디.boj_13305_주유소;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static long[] dists;
	static long[] costs;
	static long sum, min;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			N = Integer.parseInt(br.readLine());
			
			dists = new long[N - 1];
			st  = new StringTokenizer(br.readLine());
			for (int n = 0; n < N - 1; n++) {
				dists[n] = Integer.parseInt(st.nextToken());
			}
			
			costs = new long[N];
			st  = new StringTokenizer(br.readLine());
			for (int n = 0; n < N - 1; n++) {
				costs[n] = Integer.parseInt(st.nextToken());
			}
			
			sum = 0;
			min = costs[0];
			for (int i = 0; i < dists.length; i++) {
				min = Math.min(min, costs[i]);
				sum += (min * dists[i]);
			}
			
			bw.write(String.valueOf(sum));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
