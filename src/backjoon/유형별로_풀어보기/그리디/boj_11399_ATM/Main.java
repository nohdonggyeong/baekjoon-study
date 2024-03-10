package backjoon.유형별로_풀어보기.그리디.boj_11399_ATM;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static int[] P;
	static int sum;
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			N = Integer.parseInt(br.readLine());
			
			P = new int[N];
			StringTokenizer st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				P[n] = Integer.parseInt(st.nextToken());
			}
			
			Arrays.sort(P);
			
			sum = 0;
			for (int n = 0; n < N; n++) {
				sum += P[n] * (N - n);
			}
			
			bw.write(String.valueOf(sum));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
