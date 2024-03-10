package backjoon.자료구조.수학.boj_9655_돌_게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		// N = 1 -> SK
		// N = 2 -> CY
		// N = 3 -> SK
		// N = 4 -> CY
		// N = 5 -> SK
		// N = 6 -> CY
		bw.write(N % 2 == 0 ? "CY" : "SK");
		bw.flush();
		bw.close();
		br.close();
	}
}
