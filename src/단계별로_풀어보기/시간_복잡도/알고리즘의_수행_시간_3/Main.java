package 단계별로_풀어보기.시간_복잡도.알고리즘의_수행_시간_3;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		long n = Long.parseLong(br.readLine());
		bw.write(String.valueOf(n * n));
		bw.newLine();
		bw.write("2");
		bw.flush();
		bw.close();
		br.close();
	}
}
