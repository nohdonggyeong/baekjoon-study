package 단계별로_풀어보기.반복문.boj_25304_영수증;

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
		
		int money = Integer.parseInt(br.readLine());
		int sum = 0;
		int n = Integer.parseInt(br.readLine());
		for (int i = 0; i < n; i++) {
			st = new StringTokenizer(br.readLine());
			sum += Integer.parseInt(st.nextToken()) * Integer.parseInt(st.nextToken()); 
		}
		bw.write(money == sum ? "Yes" : "No");
		bw.flush();
		bw.close();
		br.close();
	}
}
