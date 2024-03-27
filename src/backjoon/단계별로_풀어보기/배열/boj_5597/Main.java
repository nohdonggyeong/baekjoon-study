package backjoon.단계별로_풀어보기.배열.boj_5597;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		boolean[] good = new boolean[30];
		for (int i = 0; i < 28; i++) {
			good[Integer.parseInt(br.readLine()) - 1] = true;
		}
		for (int i = 0; i < 30; i++) {
			if (!good[i]) {
				bw.write(String.valueOf(i + 1));
				bw.newLine();
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
