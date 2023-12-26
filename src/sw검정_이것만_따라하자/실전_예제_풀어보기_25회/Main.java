package sw검정_이것만_따라하자.실전_예제_풀어보기_25회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static int[] present, like;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			present = new int[3];
			present[0] = Integer.parseInt(st.nextToken());
			present[1] = Integer.parseInt(st.nextToken());
			present[2] = Integer.parseInt(st.nextToken());
			
			int count = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				like = new int[3];
				like[0] = Integer.parseInt(st.nextToken());
				like[1] = Integer.parseInt(st.nextToken());
				like[2] = Integer.parseInt(st.nextToken());
				
				if (present[0] >= like[0] && present[1] >= like[1] && present[2] >= like[2]) {
					count += 1;
				} else if (present[0] + 10 >= like[0] && present[1] >= like[1] && present[2] - 10 >= like[2]) {
					count += 1;
				} else if (present[1] + 10 >= like[1] && present[0] - 10 >= like[0] && present[2] - 10 >= like[2]) {
					count += 1;
				} else if (present[2] + 10 >= like[2] && present[1] - 10 >= like[1] && present[0] - 10 >= like[0]) {
					count += 1;
				}
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
