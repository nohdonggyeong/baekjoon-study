package backjoon.단계별로_풀어보기.집합과_맵.boj_1620_나는야_포켓몬_마스터_이다솜;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
		
	}
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		String[] arr = new String[n + 1];
		Map<String, Integer> map = new HashMap<>();
		for (int i = 1; i <= n; i++) {
			String str = br.readLine();
			arr[i] = str;
			map.put(str, i);
		}
		
		for (int i = 1; i <= m; i++) {
			String str = br.readLine();
			if (isInteger(str)) {
				sb.append(arr[Integer.parseInt(str)]).append("\n");
			} else {
				sb.append(map.get(str)).append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
