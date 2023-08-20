package 단계별로_풀어보기.일반_수학_1.boj_2720_세탁소_사장_동혁;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			int money = Integer.parseInt(br.readLine());
			
			int q = money / 25;
			money %= 25;
			
			int d = money / 10;
			money %= 10;
			
			int n = money / 5;
			money %= 5;
			
			int p = money;
			
			bw.write(String.valueOf(q));
			bw.write(" ");
			bw.write(String.valueOf(d));
			bw.write(" ");
			bw.write(String.valueOf(n));
			bw.write(" ");
			bw.write(String.valueOf(p));
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
