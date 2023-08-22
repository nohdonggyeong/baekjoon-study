package 단계별로_풀어보기.심화_1.boj_2941;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String input = br.readLine();
		int cnt = 0;
		for (int i = 0; i < input.length(); i++) {
			if (i < input.length() - 2) {
				if ("dz=".equals(input.substring(i, i + 3))) {
					cnt++;
					i += 2;
					continue;
				}
			}
			if (i < input.length() - 1) {
				 if ("c=".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					} else if ("c-".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					} else if ("d-".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					} else if ("lj".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					} else if ("nj".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					} else if ("s=".equals(input.substring(i, i + 2))) {
						cnt++;
						i+= 1;
						continue;
					} else if ("z=".equals(input.substring(i, i + 2))) {
						cnt++;
						i += 1;
						continue;
					}
			}
			cnt++;
		}
		
		bw.write(String.valueOf(cnt));
		bw.flush();
		bw.close();
		br.close();
	}
}
