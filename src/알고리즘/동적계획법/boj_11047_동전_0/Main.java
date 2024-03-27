package 알고리즘.동적계획법.boj_11047_동전_0;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, K, count;
	static int[] A;
	
	public static void main(String[] args) throws IOException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			for (int n = 0; n < N; n++) {
				A[n] = Integer.parseInt(br.readLine());
			}
			
			count = 0;
			for (int i = N - 1; i >= 0; i--) {
				if (K >= A[i]) {
					count += K / A[i];
					K %= A[i];
				}
			}
			
			bw.write(String.valueOf(count));
			bw.flush();
		}
	}
}
