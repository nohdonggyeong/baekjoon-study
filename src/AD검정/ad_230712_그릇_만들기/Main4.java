package AD검정.ad_230712_그릇_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main4 {
	static int T;
	static int N, M;
	static int[] materials;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> output;
	
	static boolean checkInclude() {
		for (int[] el : output) {
			if (Arrays.equals(el, temp)) {
				return true;
			}
		}
		return false;
	}
	
	static void combination(int start, int depth) {
		if (depth == M) {
			int index = 0;
			temp = new int[M];
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					temp[index++] = materials[i];
				}
			}
			
			Arrays.sort(temp);
			if (!checkInclude()) {
				output.add(temp.clone());
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
	
	static int solution() {
		visited = new boolean[N];
		output = new ArrayList<int[]>();
		combination(0, 0);
		
		for (int[] el : output) {
			for (int e : el) {
				System.out.print(String.valueOf(e) + " ");
			}
			System.out.println();
		}
		return output.size();
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());

			materials = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				materials[n] = Integer.parseInt(st.nextToken());
			}
			
			int result = solution();
			
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
