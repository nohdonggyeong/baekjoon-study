package 단계별로_풀어보기.입출력과_사칙연산.boj_1008_A나누기B;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		bw.write(String.valueOf(Double.parseDouble(st.nextToken()) / Double.parseDouble(st.nextToken()) * 1000000000 / 1000000000));
		bw.flush();
		bw.close();
		br.close();
	}
}
