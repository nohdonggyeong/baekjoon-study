package 자료구조.해시.boj_11478_서로_다른_부분_문자열_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Main {
	static String input;
	static Set<String> caseSet;

	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			input = br.readLine();

			caseSet = new HashSet<String>();
			String cut;
			for (int i = 1; i <= input.length(); i++) {
				for (int j = 0; j < input.length(); j++) {
					cut = input.substring(j, j + i > input.length() ? input.length() : j + i);
					caseSet.add(cut);
				}
			}
			
			bw.write(String.valueOf(caseSet.size()));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
