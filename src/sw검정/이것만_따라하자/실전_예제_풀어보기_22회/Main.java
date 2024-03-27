package sw검정.이것만_따라하자.실전_예제_풀어보기_22회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[] arr1 = new int[8];
	static int[] arr2 = new int[8];
	static int[] answer = new int[8];
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				arr1[i] = Integer.parseInt(st.nextToken());
			}
			
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 8; i++) {
				arr2[i] = Integer.parseInt(st.nextToken());
			}
			
			int total = 50000 * (arr1[0] + arr2[0])
					+ 10000 * (arr1[1] + arr2[1])
					+ 5000 * (arr1[2] + arr2[2])
					+ 1000 * (arr1[3] + arr2[3])
					+ 500 * (arr1[4] + arr2[4])
					+ 100 * (arr1[5] + arr2[5])
					+ 50 * (arr1[6] + arr2[6])
					+ 10 * (arr1[7] + arr2[7]);
			
			answer[0] = total / 50000;
			total = total % 50000;
			
			answer[1] = total / 10000;
			total = total % 10000;
			
			answer[2] = total / 5000;
			total = total % 5000;
			
			answer[3] = total / 1000;
			total = total % 1000;
			
			answer[4] = total / 500;
			total = total % 500;
			
			answer[5] = total / 100;
			total = total % 100;
			
			answer[6] = total / 50;
			total = total % 50;
			
			answer[7] = total / 10;
			total = total % 10;
			
			sb.append("#").append(t).append(" ");
			
			for (int i = 0; i < 7; i++) {
				sb.append(answer[i]).append(" ");
			}
			sb.append(answer[7]).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}

}
