package sw검정.이것만_따라하자.실전_예제_풀어보기_17회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N, X, Y;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			X = Integer.parseInt(st.nextToken());
			Y = Integer.parseInt(st.nextToken());
			
			int count = 0;
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				int px = Integer.parseInt(st.nextToken());
				int py = Integer.parseInt(st.nextToken());
				if (Math.abs(X - px) == Math.abs(Y -py)) {
					count++;
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
