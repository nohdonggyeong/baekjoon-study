package sw검정.이것만_따라하자.실전_예제_풀어보기_16회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int[] lines;
	
	static int check() {
		if (lines[0] > lines[1] + lines[2]
				|| lines[1] > lines[0] + lines[2]
				|| lines[2] > lines[0] + lines[1]) {
			return 0;
		}
		
		if (Math.pow(lines[0], 2) + Math.pow(lines[1], 2) == Math.pow(lines[2], 2)
				|| Math.pow(lines[1], 2) + Math.pow(lines[2], 2) == Math.pow(lines[0], 2)
				|| Math.pow(lines[2], 2) + Math.pow(lines[0], 2) == Math.pow(lines[1], 2)) {
			return 2;
		}
		
		if (Math.pow(lines[0], 2) + Math.pow(lines[1], 2) < Math.pow(lines[2], 2)
				|| Math.pow(lines[1], 2) + Math.pow(lines[2], 2) < Math.pow(lines[0], 2)
				|| Math.pow(lines[2], 2) + Math.pow(lines[0], 2) < Math.pow(lines[1], 2)) {
			return 3;
		}

		if (Math.pow(lines[0], 2) + Math.pow(lines[1], 2) > Math.pow(lines[2], 2)
				|| Math.pow(lines[1], 2) + Math.pow(lines[2], 2) > Math.pow(lines[0], 2)
				|| Math.pow(lines[2], 2) + Math.pow(lines[0], 2) > Math.pow(lines[1], 2)) {
			return 1;
		}
		
		return 0;
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			
			lines = new int[3];
			st = new StringTokenizer(br.readLine());
			for (int i = 0; i < 3; i++) {
				lines[i] = Integer.parseInt(st.nextToken());
			}

			sb.append("#").append(t).append(" ").append(check()).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}