package 단계별로_풀어보기.문자열.boj_27866_문자와_문자열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		String str = br.readLine();
		bw.write(str.charAt(Integer.parseInt(br.readLine()) - 1));
		bw.flush();
		bw.close();
		br.close();
	}
}
