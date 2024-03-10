package backjoon.단계별로_풀어보기.문자열.boj_10809;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str = br.readLine();
		
		int[] alphabet = new int[26];
		Arrays.fill(alphabet, -1);
		for (int i = 0; i < str.length(); i++) {
			int index = str.charAt(i) - 'a';
			if (alphabet[index] == -1) {
				alphabet[index] = i;
			}
		}
		
		for (int i = 0; i < 26; i++) {
			bw.write(String.valueOf(alphabet[i]));
			bw.write(" ");
		}
		
		bw.flush();
		bw.close();
		br.close();
	}
}
