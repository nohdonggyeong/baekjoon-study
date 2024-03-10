package backjoon.단계별로_풀어보기.약수_배수와_소수.boj_5806;

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
			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			
			if (a == 0 && b== 0) {
				break;
			}
			
			if (a < b && b % a == 0) {
				bw.write("factor");
			} else if (a > b && a % b == 0) {
				bw.write("multiple");
			} else {
				bw.write("neither");
			}
			bw.newLine();
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
