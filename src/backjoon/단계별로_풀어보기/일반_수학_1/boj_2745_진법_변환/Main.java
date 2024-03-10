package backjoon.단계별로_풀어보기.일반_수학_1.boj_2745_진법_변환;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		String N = st.nextToken(); 
		int B = Integer.parseInt(st.nextToken());
		
		int result = 0;
		for (int i = N.length() - 1; i >= 0; i--) {
			String str = N.substring(i, i + 1);
			if (Pattern.matches("^[\\d]$", str)) {
				result += Integer.parseInt(str) * Math.pow(B, N.length() - 1 - i);
			} else {
				result += (str.charAt(0) - 'A' + 10) * Math.pow(B, N.length() - 1 - i);
			}
		}
		
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
