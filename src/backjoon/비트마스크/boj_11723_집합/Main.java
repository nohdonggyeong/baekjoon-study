package backjoon.비트마스크.boj_11723_집합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Main {	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int M = Integer.parseInt(br.readLine());
		int bitSet = 0;
		
		while (M-- > 0) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			String op = st.nextToken();
			int num = st.hasMoreTokens() ? Integer.parseInt(st.nextToken()) : 0;
			
			switch (op) {
			case "all":
				bitSet = (1 << 21) - 1;
				break;
			case "empty":
				bitSet = 0;
				break;
			case "add":
				bitSet |= (1 << num);
				break;
			case "remove":
				bitSet &= ~(1 << num);
				break;
			case "check":
				int checkResult = (bitSet & (1 << num)) == (1 << num) ? 1 : 0;
				bw.write(String.valueOf(checkResult));
				bw.newLine();
				break;
			case "toggle":
				bitSet ^= (1 << num);
				break;
			default:
				break;
			}
		}
		
		// Output
		bw.flush();
		bw.close();
		br.close();
	}
}
