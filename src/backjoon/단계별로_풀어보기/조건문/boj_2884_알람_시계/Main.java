package backjoon.단계별로_풀어보기.조건문.boj_2884_알람_시계;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if ( b >= 45) {
			sb.append(String.valueOf(a)).append(" ").append(String.valueOf(b - 45));
		} else {
			if (a == 0) {
				sb.append("23 ").append(String.valueOf(15 + b));
			} else {
				sb.append(String.valueOf(a - 1)).append(" ").append(String.valueOf(15 + b));
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
