package backjoon.단계별로_풀어보기.배열.boj_2566;

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
		
		int maxValue = 0;
		int maxI = 1;
		int maxJ = 1;
		for (int i = 0; i < 9; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < 9; j++) {
				int value = Integer.parseInt(st.nextToken());
				if (maxValue < value) {
					maxValue = value;
					maxI = i + 1;
					maxJ = j + 1;
				}
			}
		}
		bw.write(String.valueOf(maxValue));
		bw.newLine();
		bw.write(String.valueOf(maxI));
		bw.write(" ");
		bw.write(String.valueOf(maxJ));
		bw.flush();
		bw.close();
		br.close();
	}
}
