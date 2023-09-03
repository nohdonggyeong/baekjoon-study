package 단계별로_풀어보기.집합과_맵.boj_11478_서로_다른_부분_문자열의_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.Set;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		
		Set<String> set = new HashSet<>();
		for (int i = 0; i < str.length(); i++) {
			for (int j = i + 1; j <= str.length(); j++) {
				String subStr = str.substring(i, j);
				set.add(subStr);
			}
		}
		
		bw.write(String.valueOf(set.size()));
		bw.flush();
		bw.close();
		br.close();
	}
}
