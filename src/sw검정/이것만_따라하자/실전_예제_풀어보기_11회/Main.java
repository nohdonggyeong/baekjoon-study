package sw검정.이것만_따라하자.실전_예제_풀어보기_11회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int L, Answer;
	static String S;
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		int T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			L = Integer.parseInt(st.nextToken());
			S = st.nextToken();
			
			Answer = 0;
			int start, end, len;
			
			for (int i = 0; i < L; i++) {
				for (int j = L - 1; j > i; j--) {
					start = i;
					end = j;
					len = 0;
					if (S.charAt(start) != S.charAt(end)) {
						continue;
					}
					
					len = end - start + 1;
					
					do {
						start++;
						end--;
					} while (start <= end && S.charAt(start) == S.charAt(end));
					
					if (start > end) {
						if (len >= 3 && len > Answer) {
							Answer = len;
						}
					}
				}
			}
			sb.append("#").append(t).append(" ").append(Answer).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}	
