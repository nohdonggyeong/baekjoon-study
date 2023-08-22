package 단계별로_풀어보기.입출력과_사칙연산.boj_18108;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int input = Integer.parseInt(br.readLine());
		input = input - 2541 + 1998;
		bw.write(String.valueOf(input));
		bw.flush();
		bw.close();
		br.close();
	}
}
