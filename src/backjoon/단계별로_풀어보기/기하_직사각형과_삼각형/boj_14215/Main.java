package backjoon.단계별로_풀어보기.기하_직사각형과_삼각형.boj_14215;

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
		int c = Integer.parseInt(st.nextToken());
		
		int max = a;
		max = Math.max(Math.max(max, b), c);
		
		if (2 * max >= a + b + c) {
			bw.write(String.valueOf((a + b + c - max) * 2 - 1));
		} else {
			bw.write(String.valueOf(a + b + c));
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
