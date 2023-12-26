package sw검정_이것만_따라하자.실전_예제_풀어보기_18회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

import sun.security.util.ArrayUtil;

public class Main {
	static int[] numbers = new int[] {7, 37, 38, 39, 40, 44};
	static int T, N;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			int total = 0;
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				
				List<Integer> inputs = new ArrayList<>();
				for (int i = 0; i < 6; i++) {
					inputs.add(Integer.parseInt(st.nextToken()));
				}
				
				int matchCount = 0;
				for (int i = 0; i < 6; i++) {
					if (inputs.contains(numbers[i])) {
						matchCount += 1;
					}
				}
				boolean hasBonus = false;
				if (inputs.contains(18)) {
					hasBonus = true;
				}
				
				switch (matchCount) {
				case 3:
					total += 5000;
					break;
				case 4:
					total += 50000;
					break;
				case 5:
					if (!hasBonus) {
						total += 1547926;
					} else {
						total += 60784377;
					}
					break;
				case 6:
					total += 1661439625;
					break;
				default:
					break;
				}
			}
			sb.append("#").append(t).append(" ").append(total).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
