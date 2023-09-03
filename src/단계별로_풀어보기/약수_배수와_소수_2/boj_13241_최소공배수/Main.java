package 단계별로_풀어보기.약수_배수와_소수_2.boj_13241_최소공배수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static long gcd (long a, long b) {
		if (a % b == 0) {
			return b;
		}
		return gcd(b, a % b);
	}
	
	static long lcm (long a, long b) {
		return a * b / gcd(a, b);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		long a = Long.parseLong(st.nextToken());
		long b = Long.parseLong(st.nextToken());
		
		bw.write(String.valueOf(lcm(a, b)));
		bw.flush();
		bw.close();
		br.close();
	}
}
