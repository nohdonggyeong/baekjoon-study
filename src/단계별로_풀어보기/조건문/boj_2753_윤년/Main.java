package 단계별로_풀어보기.조건문.boj_2753_윤년;

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
		if ((a % 4 == 0 && a % 100 != 0) || a % 400 == 0) {
			bw.write("1");
		} else {
			bw.write("0");
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
