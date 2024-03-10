package sw검정.이것만_따라하자.실전_예제_풀어보기_21회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			String[] components = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				components[n] = st.nextToken();
			}
			
			String[] parts = new String[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				parts[n] = st.nextToken();
			}
			
			int count = 0;
			for (int n = 0; n < N; n++) {
				String component = components[n];
				String part = parts[n];
				if (component.charAt(0) == part.charAt(0) && component.charAt(component.length() - 1) == part.charAt(part.length() - 1) && component.length() == part.length()) {
					component = component.substring(1, component.length() - 1);
					part = part.substring(1, part.length() - 1);
					
					String[] componentArr = component.split("");
					String[] partArr = part.split("");
					
					Arrays.sort(componentArr);
					Arrays.sort(partArr);
					
					boolean result = true;
					for (int i = 0; i < componentArr.length; i++) {
						if (!partArr[i].equals(componentArr[i])) {
							result = false;
							break;
						}
					}
					
					if (result) {
						count += 1;
					}
				}
			}
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
