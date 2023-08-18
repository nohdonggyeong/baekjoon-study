package 알고리즘.브루트포스.boj_19532_수학은_비대면강의입니다;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		int e = Integer.parseInt(st.nextToken());
		int f = Integer.parseInt(st.nextToken());
		
		// Process
		int x = 0;
		int y = 0;
		
		loop:
		for (int i = -999; i <= 999; i++) {
			for (int j = -999; j <= 999; j++) {
				if (a * i + b * j == c && d * i + e * j == f) {
					x = i;
					y = j;
					break loop;
				}
			}
		}
		
		// Output
		StringBuilder sb = new StringBuilder();
		sb.append(String.valueOf(x));
		sb.append(" ");
		sb.append(String.valueOf(y));
		bw.write(sb.toString());
		
		bw.close();
		br.close();
	}
}
