package 단계별로_풀어보기.기하_직사각형과_삼각형.boj_27323_직사각형;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int a = Integer.parseInt(br.readLine());
		int b = Integer.parseInt(br.readLine());
		bw.write(String.valueOf(a * b));
		bw.flush();
		bw.close();
		br.close();
	}
}
