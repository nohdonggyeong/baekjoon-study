package backjoon.단계별로_풀어보기.문자열.boj_2675;

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
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken());
			String str = st.nextToken();
			for (int i = 0; i < str.length(); i++) {
				for (int j = 0; j < n; j++) {
					bw.write(str.charAt(i));
				}
			}
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
