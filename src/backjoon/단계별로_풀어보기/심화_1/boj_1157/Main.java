package backjoon.단계별로_풀어보기.심화_1.boj_1157;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int[] alphabet = new int[26];
		String str = br.readLine().toLowerCase();
		for (int i = 0; i < str.length(); i++) {
			alphabet[str.charAt(i) - 'a']++;
		}
		int maxIndex = 0;
		int maxCnt = alphabet[0];
		for (int i = 1; i < 26; i++) {
			if (alphabet[i] == maxCnt) {
				maxIndex = -1;
			} else if (alphabet[i] > maxCnt) {
				maxCnt = alphabet[i];
				maxIndex = i;
			}
		}
		if (maxIndex == -1) {
			bw.write("?");
		} else {
			char ch = (char) ('a' + maxIndex);
			bw.write(String.valueOf(ch).toUpperCase());
		}
		bw.flush();
		bw.close();
		br.close();
	}
}
