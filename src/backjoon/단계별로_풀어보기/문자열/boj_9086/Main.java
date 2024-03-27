package backjoon.단계별로_풀어보기.문자열.boj_9086;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			String str = br.readLine();
			bw.write(str.charAt(0));
			bw.write(str.charAt(str.length() - 1));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
