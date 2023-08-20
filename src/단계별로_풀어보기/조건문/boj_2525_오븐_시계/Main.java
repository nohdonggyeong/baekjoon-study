package 단계별로_풀어보기.조건문.boj_2525_오븐_시계;

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
		int c = Integer.parseInt(br.readLine());
		
		b += c;
		if (b >= 60) {
			a += b / 60;
			b %= 60;
		}
		if (a >= 24) {
			a -= 24;
		}
		sb.append(String.valueOf(a)).append(" ").append(String.valueOf(b));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}