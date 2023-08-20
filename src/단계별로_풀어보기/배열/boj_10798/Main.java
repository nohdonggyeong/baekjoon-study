package 단계별로_풀어보기.배열.boj_10798;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[][] arr = new String[5][15];
		for (int i = 0; i < 5; i++) {
			String input = br.readLine();
			for (int j = 0; j < input.length(); j++) {
				arr[i][j] = input.substring(j, j+ 1);
			}
		}
		
		String result = "";
		for (int j = 0; j < 15; j++) {
			for (int i = 0; i < 5; i++) {
				if (arr[i][j] != null) {
					result += arr[i][j];
				}
			}
		}
		
		bw.write(result);
		bw.flush();
		bw.close();
		br.close();
	}
}
