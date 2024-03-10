package backjoon.단계별로_풀어보기.기하_직사각형과_삼각형.boj_5073;

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
		
		while (true) {
			st = new StringTokenizer(br.readLine());
			int a= Integer.parseInt(st.nextToken());
			int b= Integer.parseInt(st.nextToken());
			int c= Integer.parseInt(st.nextToken());
			
			int max = a;
			max = Math.max(Math.max(max, b), c);
			
			if (a == 0 && b == 0 && c == 0) {
				break;
			}
			
			if (2 * max >= a + b + c) {
				bw.write("Invalid");
				bw.newLine();
			} else {
				if (a == b && b == c) {
					bw.write("Equilateral");
					bw.newLine();
				} else if (a == b || b == c || c == a) {
					bw.write("Isosceles");
					bw.newLine();
				} else {
					bw.write("Scalene");
					bw.newLine();
				}
			}
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
