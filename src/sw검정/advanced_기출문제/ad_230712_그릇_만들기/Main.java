package sw검정.advanced_기출문제.ad_230712_그릇_만들기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int N, M;
	static int answer;
	
	static int[] materials;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void combination(int start, int depth) {
		if (depth == M) {
			int index = 0;
			temp = new int[M];
			for (int i = 0; i < N; i++) {
				if (visited[i]) {
					temp[index++] = materials[input[i]];
				}
			}
			
			int[] result = temp.clone();
			Arrays.sort(result);
			for (int[] el : output) {
				if (Arrays.equals(el, result)) {
					return;
				}
			}
			output.add(result);
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
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			materials = new int[N];
			st = new StringTokenizer(br.readLine());
			for (int n = 0; n < N; n++) {
				materials[n] = Integer.parseInt(st.nextToken());
			}
			
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = i;
			}
			
			visited = new boolean[N];
			output = new ArrayList<>();
			combination(0, 0);
			
			answer = output.size();			
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}

