package 단계별로_풀어보기.약수_배수와_소수_2.boj_1735_분수_합;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int gcd(int a, int b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
	
	static int lcm(int a, int b) {
		return a * b / gcd(a, b);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int upper1 = Integer.parseInt(st.nextToken());
		int below1 = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine());
		int upper2 = Integer.parseInt(st.nextToken());
		int below2 = Integer.parseInt(st.nextToken());
		
		int below = lcm(below1, below2);
		int upper = below / below1 * upper1 + (below / below2 * upper2);
		
		int newGcd = gcd(below, upper);
		upper = upper / newGcd;
		below = below / newGcd;
	
		sb.append(upper).append(" ").append(below);
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
