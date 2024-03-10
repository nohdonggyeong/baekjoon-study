package backjoon.단계별로_풀어보기.심화_1.boj_3003;

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
		bw.write(String.valueOf(1 - Integer.parseInt(st.nextToken())) + " ");
		bw.write(String.valueOf(1 - Integer.parseInt(st.nextToken())) + " ");
		bw.write(String.valueOf(2 - Integer.parseInt(st.nextToken())) + " ");
		bw.write(String.valueOf(2 - Integer.parseInt(st.nextToken())) + " ");
		bw.write(String.valueOf(2 - Integer.parseInt(st.nextToken())) + " ");
		bw.write(String.valueOf(8 - Integer.parseInt(st.nextToken())));
		bw.flush();
		bw.close();
		br.close();
	}
}
