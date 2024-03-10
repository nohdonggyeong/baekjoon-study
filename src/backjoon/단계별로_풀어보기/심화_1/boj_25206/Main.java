package backjoon.단계별로_풀어보기.심화_1.boj_25206;

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
		
		double upValue = 0;
		double downValue = 0;
		for (int i = 0; i < 20; i++) {
			st = new StringTokenizer(br.readLine());
			st.nextToken();
			
			double num = Double.parseDouble(st.nextToken());
			
			double scoreValue = 0;
			String score = st.nextToken();
			if ("A+".equals(score)) {
				scoreValue = 4.5;
			} else if ("A0".equals(score)) {
				scoreValue = 4.0;
			} else if ("B+".equals(score)) {
				scoreValue = 3.5;
			} else if ("B0".equals(score)) {
				scoreValue = 3.0;
			} else if ("C+".equals(score)) {
				scoreValue = 2.5;
			} else if ("C0".equals(score)) {
				scoreValue = 2.0;
			} else if ("D+".equals(score)) {
				scoreValue = 1.5;
			} else if ("D0".equals(score)) {
				scoreValue = 1.0;
			} else if ("F".equals(score)) {
				scoreValue = 0.0;
			} else {
				num = 0;
			}
			
			upValue += num * scoreValue;
			downValue += num;
		}
		double result = upValue / downValue;
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
