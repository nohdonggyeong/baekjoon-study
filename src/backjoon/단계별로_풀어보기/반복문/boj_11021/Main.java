package backjoon.단계별로_풀어보기.반복문.boj_11021;

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
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			st = new StringTokenizer(br.readLine());
			bw.write("Case #" + String.valueOf(i) + ": " + String.valueOf(Long.parseLong(st.nextToken()) + Long.parseLong(st.nextToken())));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
