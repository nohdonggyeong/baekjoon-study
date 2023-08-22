package 단계별로_풀어보기.심화_1.boj_1316;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		
		int n = Integer.parseInt(br.readLine());
		int cnt = n;
		
		for (int i = 0; i < n; i++) {
			
			String input = br.readLine();
			
			char first = input.charAt(0);
			List<Character> list = new ArrayList<>();
			list.add(input.charAt(0));
			
			for (int j = 1; j < input.length(); j++) {
				if (input.charAt(j) != first) {
					if (list.contains(input.charAt(j))) {
						cnt--;
						break;
					} else {
						first = input.charAt(j);
						list.add(input.charAt(j));
					}
				}				
			}
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}
}
