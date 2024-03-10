package backjoon.단계별로_풀어보기.심화_1.boj_2444;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int n = Integer.parseInt(br.readLine());
		for (int i = n - 1; i >= 0; i--) {
			for (int j = i; j >= 1; j--) {
				bw.write(" ");
			}
			for (int j  = 1; j <= 2 * (n - i) - 1; j++) {
				bw.write("*");
			}
			bw.newLine();
		}
		for (int i = 1; i <= n - 1; i++) {
			for (int j = 1; j <= i; j++) {
				bw.write(" ");
			}
			for (int j = 1; j <= 2 * (n - i) - 1; j++) {
				bw.write("*");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
