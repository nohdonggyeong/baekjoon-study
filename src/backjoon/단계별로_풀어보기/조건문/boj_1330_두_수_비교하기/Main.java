package backjoon.단계별로_풀어보기.조건문.boj_1330_두_수_비교하기;

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
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		if (a > b) {
			bw.write(">");
		} else if (a == b) {
			bw.write("==");
		} else {
			bw.write("<");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
