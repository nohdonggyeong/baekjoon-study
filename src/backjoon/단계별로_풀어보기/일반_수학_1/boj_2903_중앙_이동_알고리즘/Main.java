package backjoon.단계별로_풀어보기.일반_수학_1.boj_2903_중앙_이동_알고리즘;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int  n = Integer.parseInt(br.readLine());
		int cnt = 2;
		for (int i = 1; i <= n; i++) {
			cnt = cnt + (cnt - 1);
		}
		bw.write(String.valueOf((int) Math.pow(cnt, 2)));
		bw.flush();
		bw.close();
		br.close();
	}
}
