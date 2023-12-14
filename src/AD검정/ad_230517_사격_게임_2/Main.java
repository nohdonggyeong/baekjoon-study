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
		private int h;
		private int w;
		
		Node(int h, int w) {
			this.h = h;
			this.w = w;
		}
	}
	
	static int n, r;
	static int[] input, temp;
	static List<int[]> output;
	
	static void combinationWithRepetition(int start, int depth) {
		if (depth == r) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			temp[depth] = input[i];
			combinationWithRepetition(i, depth + 1);
		}
	}
	
	static int[][] shotCount;
	
	static void shootRightUp(Node node, Boolean rollBack) {
		if (node.h < 0 || node.w >= W) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootRightUp(new Node(node.h - 1, node.w + 1), rollBack);
	}
	
	static void shootRightStraight(Node node, Boolean rollBack) {
		if (node.w >= W) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootRightUp(new Node(node.h, node.w + 1), rollBack);
	}
	
	static void shootRightDown(Node node, Boolean rollBack) {
		if (node.h >= H || node.w >= W) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootRightUp(new Node(node.h + 1, node.w + 1), rollBack);
	}
	
	static void shootDownLeft(Node node, Boolean rollBack) {
		if (node.h >= H || node.w < 0) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootDownLeft(new Node(node.h + 1, node.w - 1), rollBack);
	}
	
	static void shootDownStraight(Node node, Boolean rollBack) {
		if (node.h >= H) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootDownStraight(new Node(node.h + 1, node.w), rollBack);
	}
	
	static void shootDownRight(Node node, Boolean rollBack) {
		if (node.h >= H || node.w >= W) {
			return;
		}
		if (!rollBack) {
			shotCount[node.h][node.w] += 1;
		} else {
			shotCount[node.h][node.w] -= 1;
		}
		shootDownRight(new Node(node.h + 1, node.w + 1), rollBack);
	}
	
	static void dfs(int[] el) {
		
	}
	
	static int gamePoint;
	
	static int getGamePoint() {
		for (int h = 0; h < H; h++) {
			for (int w = 0; w < W; w++) {
				if (shotCount[h][w] > 0) {
					gamePoint += D[h][w];
				}
			}
		}
		return gamePoint;
	}
	
	static void solution() {
		// 사격장소 리스트 구성
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
		
		temp = new int[r];
		output = new ArrayList<>();
		combinationWithRepetition(0, 0);
		
		// 경우마다 계산
		for (int[] el : output) {
			shotCount = new int[H][W];
			dfs(el);
			
			gamePoint = 0;
			getGamePoint();
			result = Math.max(result, gamePoint);
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
