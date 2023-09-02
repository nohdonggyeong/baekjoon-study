package 단계별로_풀어보기.정렬.boj_18870_좌표_압축;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		int n = Integer.parseInt(br.readLine());
		int[] arr = new int[n];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			arr[i] = Integer.parseInt(st.nextToken());
		}
		
		int[] sorted = arr.clone();
		Arrays.sort(sorted);
		
		Map<Integer, Integer> map = new HashMap<>();
		int index = 0;
		for (int i = 0; i < n; i++) {
			if (!map.containsKey(sorted[i])) {
				map.put(sorted[i], index++);
			}
		}
		
		for (int i = 0; i < n; i++) {
			sb.append(map.get(arr[i])).append(" ");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
