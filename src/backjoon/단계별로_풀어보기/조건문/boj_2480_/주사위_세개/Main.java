package backjoon.단계별로_풀어보기.조건문.boj_2480_.주사위_세개;

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
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		int a = Integer.parseInt(st.nextToken());
		int b = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		
		int result = 0;
		if (a == b && b == c) {
			result = 10000 + a * 1000;
		} else if ((a == b && b != c) || (a == c && b != a)) {
			result = 1000 + a * 100;
		}else if (b == c && a != b) {
			result = 1000 + b * 100; 
		} else {
			result = Math.max(Math.max(a, b), c) * 100;
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}