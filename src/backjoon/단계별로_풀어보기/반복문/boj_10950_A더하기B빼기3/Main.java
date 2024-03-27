package backjoon.단계별로_풀어보기.반복문.boj_10950_A더하기B빼기3;

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
		int a = Integer.parseInt(br.readLine());
		for (int i = 0; i < a; i++) {
			st = new StringTokenizer(br.readLine());
			bw.write(String.valueOf(Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken())));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
