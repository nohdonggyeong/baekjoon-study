package backjoon.단계별로_풀어보기.심화_1.boj_10988;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		String front = str.substring(0, str.length() / 2);
		String back = "";
		if (str.length() % 2 == 1) {
			back = str.substring(str.length() / 2 + 1, str.length());
		} else {
			back = str.substring(str.length() / 2, str.length());
		}
		
		String newFront = "";
		for (int i = front.length() - 1; i >= 0; i--) {
			newFront += front.charAt(i);
		}
		if (back.equals(newFront)) {
			bw.write("1");
		} else {
			bw.write("0");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
