package solvedAC.grandArena2.A번_세제곱의_합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int N, result1, result2, result3;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		N = Integer.parseInt(br.readLine());
		
		result1 = 0;
		for (int i = 1; i <= N; i++) {
			result1 += i;
		}
		
		result2 = (int) Math.pow(result1, 2);
		
		result3 = 0;
		for (int i = 1; i <= N; i++) {
			result3 += (int) Math.pow(i, 3);
		}
		
		bw.write(String.valueOf(result1));
		bw.newLine();
		bw.write(String.valueOf(result2));
		bw.newLine();
		bw.write(String.valueOf(result3));
		
		bw.flush();
		
		bw.close();
		br.close();
	}
}
