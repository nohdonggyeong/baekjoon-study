package AD검정.ad_230517_사격_게임_2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {

	static int T;
	static int H, W;
	static int[][] D;
	
	static int result;
	
	static List<Node> shootingList;
	
	static class Node {
		private int x;
		private int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int n, r;
	static int[] input, temp;
	static boolean[] visited;
	static List<int[]> output;
	
	static void solution() {
		// 사격장소 리스트
		shootingList = new ArrayList<>();
		for (int h = 0; h < H; h++) {
			shootingList.add(new Node(h, 0));
		}
		for (int w = 1; w < W; w++) {
			shootingList.add(new Node(0, w));
		}
		
		// 사격장소 선정
		n = H + W - 1;
		r = 3;
		input = new int[H + W - 1];
		for (int i = 0; i < H + W - 1; i++) {
			input[i] = i + 1;
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			st = new StringTokenizer(br.readLine());
			H = Integer.parseInt(st.nextToken());
			W = Integer.parseInt(st.nextToken());
			
			D = new int[H][W];
			for (int h = 0; h < H; h++) {
				st = new StringTokenizer(br.readLine());
				for (int w = 0; w < W; w++) {
					D[h][w] = Integer.parseInt(st.nextToken());
				}
			}
			
			int result = 0;
			solution();
			
			sb.append("#").append(t + 1).append(" ").append(result).append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
