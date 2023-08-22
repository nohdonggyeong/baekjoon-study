package 단계별로_풀어보기.문자열.boj_2908;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String a = st.nextToken();
		String b = st.nextToken();
		
		String newA = "";
		for (int i = a.length() - 1; i >= 0; i--) {
			newA += a.substring(i, i + 1);
		}
		String newB = "";
		for (int i = b.length() - 1; i >= 0; i--) {
			newB += b.substring(i, i + 1);
		}
		bw.write(Integer.parseInt(newA) >= Integer.parseInt(newB) ? newA : newB);
		bw.flush();
		bw.close();
		br.close();
	}
}
