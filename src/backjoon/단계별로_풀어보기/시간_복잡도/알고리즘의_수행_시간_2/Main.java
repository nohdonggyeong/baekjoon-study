package backjoon.단계별로_풀어보기.시간_복잡도.알고리즘의_수행_시간_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int n = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(n));
		bw.newLine();
		bw.write("1");
		bw.flush();
		bw.close();
		br.close();
	}
}
