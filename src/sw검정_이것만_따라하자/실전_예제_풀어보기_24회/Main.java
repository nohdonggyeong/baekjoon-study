package sw검정_이것만_따라하자.실전_예제_풀어보기_24회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	static int Answer;
	static int[] B = new int[50];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < N; i++) {
				B[i] = Integer.parseInt(st.nextToken());
			}
			
			Answer = 0;
			for (int i = 1; i < N; i++) {
				int w = 0;
				for (int j = 0; j < N; j++) {
					w += B[j];
					if (j == 0 || j == N - 1 || j % i == 0) {
						if (M / 50 >= w) {
							w = 0;
						} else {
							w -= M / 50;
						}
					}
					
					if (w * 50 > 800) {
						break;
					}
					
					if (j == N - 1 && w == 0) {
						Answer = i;
					}
				}
			}
			
			sb.append("#").append(t).append(" ").append(Answer).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
