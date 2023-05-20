package algorithm.bruteForce.boj_1436_영화감독_숌;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int N = Integer.parseInt(br.readLine());
		
		// Process
		int cnt = 0;
		int index = 665;
		int result = 0;
		while (true) {
			if(String.valueOf(index).contains("666")) {
				cnt++;
				if (N == cnt) {
					result = index;
					break;
				}
			}
			index++;
		}
		
		// Output
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
