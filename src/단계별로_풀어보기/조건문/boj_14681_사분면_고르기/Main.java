package 단계별로_풀어보기.조건문.boj_14681_사분면_고르기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		if (a > 0 && b > 0) {
			bw.write("1");
		} else if (a > 0 && b < 0) {
			bw.write("4");
		} else if (a < 0 && b < 0) {
			bw.write("3");
		} else {
			bw.write("2");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
