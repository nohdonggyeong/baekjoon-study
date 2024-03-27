package backjoon.단계별로_풀어보기.기하_직사각형과_삼각형.boj_15894;

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
		bw.write(String.valueOf(4 * n));
		bw.flush();
		bw.close();
		br.close();
	}
}
