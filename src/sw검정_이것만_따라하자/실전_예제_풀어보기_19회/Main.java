package sw검정_이것만_따라하자.실전_예제_풀어보기_19회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N, M;
	
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
			
			double line1 = N * 360 + M * 9;
			double line2 = line1 / 8;
			line1 = line1 % 360;
			
			double angle = Math.abs(line1 - line2);
			double answer = Math.min(angle, 360 - angle);
			// 나눗셈을 소수로 나눠야 몫 나눗셈이 아니게 된다.
			sb.append("#").append(t).append(" ").append(Math.round(answer * 10) / 10.0).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
