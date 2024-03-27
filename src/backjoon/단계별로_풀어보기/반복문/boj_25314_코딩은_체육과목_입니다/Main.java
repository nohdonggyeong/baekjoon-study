package backjoon.단계별로_풀어보기.반복문.boj_25314_코딩은_체육과목_입니다;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		int n = Integer.parseInt(br.readLine());
		for (int i = 1; i <= n; i++) {
			if (i % 4 == 0) {
				sb.append("long ");
			}
		}
		sb.append("int");
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
