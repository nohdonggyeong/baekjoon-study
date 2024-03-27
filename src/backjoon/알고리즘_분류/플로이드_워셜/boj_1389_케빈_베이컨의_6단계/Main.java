package backjoon.알고리즘_분류.플로이드_워셜.boj_1389_케빈_베이컨의_6단계;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

class Node implements Comparable<Node>
{
	int num;
	int sum;
	
	Node(int num, int sum) {
		this.num = num;
		this.sum = sum;
	}
	
	@Override
	public int compareTo(Node o) {
		if (sum == o.sum) {
			return num - o.num;
		}
		return sum - o.sum;
	}
}
public class Main {
	static int N, M;
	static int[][] arr;
	static final int INF = 5_000;
	static Node[] result;
	
	static void floydWarshall(int N) {
		for (int k = 1; k < N + 1; k++) {
			for (int i = 1; i < N + 1; i++) {
				if (k == i) {
					continue;
				}
				for (int j = 1; j < N + 1; j++) {
					if (k == j || i == j) {
						continue;
					}
					if (arr[i][j] > arr[i][k] + arr[k][j]) {
						arr[i][j] = arr[i][k] + arr[k][j];
					}
				}
			}
		}
	}
	
	public static void main(String[] args) {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			
			arr = new int[N + 1][N + 1];
			for (int i = 1; i < N + 1; i++) {
				for (int j = 1; j < N + 1; j++) {
					if (i != j) {
						arr[i][j] = INF;
					}
				}
			}
			
			for (int m = 0; m < M; m++) {
				st = new StringTokenizer(br.readLine());
				int u = Integer.parseInt(st.nextToken());
				int v = Integer.parseInt(st.nextToken());
				arr[u][v] = 1;
				arr[v][u] = 1;
			}
			
			floydWarshall(N);
			
			result = new Node[N + 1];
			result[0] = new Node(0, 0);
			for (int i = 1; i < N + 1; i++) {
				int sum = 0;
				for (int j = 1; j < N + 1; j++) {
					sum += arr[i][j]; 
				}
				result[i] = new Node(i, sum);
			}
			
			Arrays.sort(result);
			
			bw.write(String.valueOf(result[1].num));
			bw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
