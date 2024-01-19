package PRO검정.도서관;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Simulation {
	static int T, N, Q;
	static int[] bookOrder;
	
	public static void main(String[] args) throws IOException {
		// T * (N + 1 + Q * (count - num - 1)) -> O(T * Q * N)
		// 6 * 100,000 * 100,000 = 60,000,000,000 > 1억 (1초)
		long start = System.currentTimeMillis();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			// 주어지는 책 개수만큼 배열 크기 생성하고 0으로 초기화
			bookOrder = new int[N + 1];
			for (int i = 0; i <= N; i++) {
				bookOrder[i] = i;
			}
			
			int sum = 0;
			int count = N;
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				int behavior = Integer.parseInt(st.nextToken());
				int num = Integer.parseInt(st.nextToken());
				
				// 주어지는 순서의 책 빼고 현재 책 개수 업데이트
				if (behavior == 1) {
					sum += bookOrder[num];
					for (int i = num; i < count; i++) {
						bookOrder[i] = bookOrder[i + 1];
					}
					bookOrder[count] = 0;
					count -= 1;
					// 주어지는 책 번호를 우측으로 넣고 책 개수 업데이트
				} else if (behavior == 2) {
					bookOrder[count + 1] = num;
					count += 1;
				}
			}
			sb.append("#").append(t).append(" ").append(sum).append("\n");
		}
		bw.write(sb.toString().trim());
		
		long end = System.currentTimeMillis();
		bw.newLine();
		bw.write(String.valueOf((end - start) / 1000.0) + "초"); // 2~3초
		
		bw.flush();
		bw.close();
		br.close();
	}

}
