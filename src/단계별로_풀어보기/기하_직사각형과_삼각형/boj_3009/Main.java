package 단계별로_풀어보기.기하_직사각형과_삼각형.boj_3009;

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
		
		st = new StringTokenizer(br.readLine());
		int x1 = Integer.parseInt(st.nextToken());
		int y1 = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine());
		int x2 = Integer.parseInt(st.nextToken());
		int y2 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int x3 = Integer.parseInt(st.nextToken());
		int y3 = Integer.parseInt(st.nextToken());
		
		int x4 = Integer.MIN_VALUE;
		int y4 = Integer.MIN_VALUE;
		
		if (x1 == x2) {
			x4 = x3;
		} else if (x1 == x3) {
			x4 = x2;
		} else if (x2 == x3) {
			x4 = x1;
		}
		
		if (y1 == y2) {
			y4 = y3;
		} else if (y1 == y3) {
			y4 = y2;
		} else if (y2 == y3) {
			y4 = y1;
		}
		
		bw.write(String.valueOf(x4));
		bw.write(" ");
		bw.write(String.valueOf(y4));
		bw.flush();
		bw.close();
		br.close();
	}
}
