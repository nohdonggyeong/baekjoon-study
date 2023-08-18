package AD검정.ad_230712_그릇_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak5 {
	static int T, N, M;
	static int[] input;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == M) {
			temp = new int[M];
			int index = 0;
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					temp[index++] = input[i];
				}
			}
			boolean contains = false;
			for (int[] el : output) {
				if (Arrays.equals(el, temp)) {
					contains = true;
				}
			}
			if (!contains) {
				output.add(temp);
			}
			return;
		}
		
		for (int i = start; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		System.setIn(new FileInputStream("src\\삼성SW역량테스트\\ad_230712_그릇_만들기\\input.txt"));
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			input = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				input[n] = Integer.parseInt(st.nextToken());
			}
			Arrays.sort(input);
			
			visited = new boolean[N];
			output = new ArrayList<>();
			combination(0, 0);
			
			sb.append("#").append(String.valueOf(t)).append(" ").append(String.valueOf(output.size()));
			if (t < T) {
				sb.append("\n");
			}
		}
		bw.write(sb.toString());
		bw.flush();
		
		bw.close();
		br.close();
	}
}
