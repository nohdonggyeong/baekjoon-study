package sw검정.이것만_따라하자.실전_예제_풀어보기_13회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			
			String totalBinary = "";
			for (int n = 0; n < N; n++) {
				int decimal = Integer.parseInt(st.nextToken());
				String binary = Integer.toBinaryString(decimal);
				
				if (binary.length() < 7) {
					int iLimit = 7 - binary.length();
					for (int i = 0; i < iLimit; i++) {
						binary = "0" + binary;
					}
				}
				totalBinary += binary;
			}
//			System.out.println(totalBinary);
			
			int result = 0;
			for (int i = 0; i < totalBinary.length(); i++) {
				int num = Integer.parseInt(totalBinary.substring(totalBinary.length() - 1 - i, totalBinary.length() - i));
				result += num * Math.pow(2, i);
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}	
