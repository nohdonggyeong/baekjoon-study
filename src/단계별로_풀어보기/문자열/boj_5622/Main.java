package 단계별로_풀어보기.문자열.boj_5622;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String[] strArr = br.readLine().split("");
		int result = 0;
		for (int i = 0; i < strArr.length; i++) {
			int index = strArr[i].charAt(0) - 'A';
			if (index >= 0 && index < 3) {
				result += 3;
			} else if (index >= 3 && index < 6) {
				result += 4;
			} else if (index >= 6 && index < 9) {
				result += 5;
			} else if (index >= 9 && index < 12) {
				result += 6;
			} else if (index >= 12 && index < 15) {
				result += 7;
			} else if (index >= 15 && index < 19) {
				result += 8;
			} else if (index >= 19 && index < 22) {
				result += 9;
			} else {
				result += 10;
			}
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
