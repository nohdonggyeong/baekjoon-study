package backjoon.단계별로_풀어보기.입출력과_사칙연산.boj_2588_곱셈;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int a= Integer.parseInt(br.readLine());
		int b= Integer.parseInt(br.readLine());
		int b1 = b % 10;
		int b2 = b % 100 - b1;
		int b3 = b / 100;
		int res1 = a * b1;
		int res2 = a * b2 / 10;
		int res3 = a * b3;
		int res4 = res1 + res2 * 10 + res3 * 100; 
		bw.write(String.valueOf(res1));
		bw.newLine();
		bw.write(String.valueOf(res2));
		bw.newLine();
		bw.write(String.valueOf(res3));
		bw.newLine();
		bw.write(String.valueOf(res4));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
