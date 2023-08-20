package 단계별로_풀어보기.문자열.boj_11654_아스키_코드;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(String.valueOf(br.readLine().charAt(0) - 'A' + 65));
		bw.flush();
		bw.close();
		br.close();
	}
}
