package backjoon.단계별로_풀어보기.기하_직사각형과_삼각형.boj_1085_직사각형에서_탈출;

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
		
		int x = Integer.parseInt(st.nextToken());
		int y = Integer.parseInt(st.nextToken());
		int w = Integer.parseInt(st.nextToken());
		int h = Integer.parseInt(st.nextToken());
		
		int min = x;
		min = Math.min(min, y);
		min = Math.min(min, w - x);
		min = Math.min(min, h - y);
		
		bw.write(String.valueOf(min));
		bw.flush();
		bw.close();
		br.close();
	}
}
