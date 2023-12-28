package sw검정_Advanced_취득_전략.논리구현;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static String str1, str2;
	static int[] input1, input2;
	
	static int[] input, temp;
	static boolean[] visited;
	
	static int count, index1, index2;
	
	static void permutation(int depth) {
		if (depth == N) {
			count += 1;
			if (Arrays.equals(input1, temp)) {
				index1 = count;
			}
			if (Arrays.equals(input2, temp)) {
				index2 = count;
			}
			return;
		}
		
		for (int i = 0; i < N; i++) {
			if (!visited[i]) {
				visited[i] = true;
				temp[depth] = input[i];
				permutation(depth + 1);
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
			str1 = st.nextToken();
			str2 = st.nextToken();
			
			input1 = new int[N];
			input2 = new int[N];
			for (int i = 0; i < N; i++) {
				input1[i] = str1.charAt(i) - 'a';
				input2[i] = str2.charAt(i) - 'a';
			}
			
			input = new int[N];
			for (int i = 0; i < N; i++) {
				input[i] = i;
			}
			
			visited = new boolean[N];
			temp = new int[N];
			count = 0;
			index1 = 0;
			index2 = 0;
			permutation(0);
			
			sb.append("#").append(t).append(" ").append(Math.abs(index2 - index1) - 1).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
