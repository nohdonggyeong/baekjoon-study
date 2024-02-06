package 알고리즘.투포인터.boj_2003_수들의_합_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[] A;
	static int start, end, sum, count;
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			A = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				A[n] = Integer.parseInt(st.nextToken());
			}
			
			start = end = sum = count = 0;
			while (true) {
				if (sum >= M) {
					sum -= A[start++];
				} else {
					if (end >= N) {
						break;
					}
					sum += A[end++];
				}
				
				if (sum == M) {
					count++;
				}
			}
			
			bw.write(String.valueOf(count));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
