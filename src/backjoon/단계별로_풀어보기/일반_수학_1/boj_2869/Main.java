package backjoon.단계별로_풀어보기.일반_수학_1.boj_2869;

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
		int v = Integer.parseInt(st.nextToken());
		
		int result = (v - b) / (a - b);
		if ((v - b) % (a - b) != 0) {
			result++;
		}
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
