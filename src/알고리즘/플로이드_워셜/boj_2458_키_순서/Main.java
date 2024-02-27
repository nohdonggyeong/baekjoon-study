package 알고리즘.플로이드_워셜.boj_2458_키_순서;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static boolean[][] check;
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			
			check = new boolean[N + 1][N + 1];
			int u, v;
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				u = Integer.parseInt(st.nextToken());
				v = Integer.parseInt(st.nextToken());
				
				check[u][v] = true;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
