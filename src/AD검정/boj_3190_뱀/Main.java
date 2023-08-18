package AD검정.boj_3190_뱀;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int N, K, L, answer = 0;
	static int[][] apples;
	static Map<Integer, String> directions;
	static List<Node> snake;
	static int[] dx = {1, 0 ,-1, 0};
	static int[] dy = {0, 1, 0, -1};
	
	static class Node {
		private int x;
		private int y;
		private int direction;
		private int time;
		
		public Node(int x, int y, int direction, int time) {
			this.x = x;
			this.y = y;
			this.direction = direction;
			this.time = time;
		}
	}
	
	static void solution() {
		while (true) {
			Node head = snake.get(snake.size() - 1);
			
			int newX = head.x + dx[head.direction];
			int newY = head.y + dy[head.direction];
			int newTime = head.time + 1;
			int newDirection = head.direction;
			if (directions.containsKey(newTime)) {
				if("L".equals(directions.get(newTime))) {
					newDirection -= 1;
					if (newDirection == -1) {
						newDirection = 3;
					}
				} else {
					newDirection += 1;
					if (newDirection == 4) {
						newDirection = 0;
					}
				}
			}
			
			snake.add(new Node(newX, newY, newDirection, newTime));
			
			if (newX < 0 || newY < 0 || newX >= N || newY >= N) {
				answer = newTime;
				return;
			}
			
			for (int i = 0; i < snake.size() - 1; i ++) {
				if (newX == snake.get(i).x && newY == snake.get(i).y) {
					answer = newTime;
					return;
				}
			}
			
			if (apples[newY][newX] != 1) {
				snake.remove(0);
			} else { // 먹은 사과는 지워줘야 한다!
				apples[newY][newX] = 0;
			}
			
//			System.out.println();
//			for (Node el :snake) {
//				System.out.println("(" + el.x + ", " + el.y + ")");
//			}
//			System.out.println();
			
		}
		
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		
		K = Integer.parseInt(br.readLine());
		apples = new int[N][N];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			apples[Integer.parseInt(st.nextToken()) - 1][Integer.parseInt(st.nextToken()) - 1] = 1;
		}
		
//		System.out.println();
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				System.out.print(String.valueOf(apples[i][j]));
//			}
//			System.out.println();
//		}
		
		L = Integer.parseInt(br.readLine());
		directions = new HashMap<>();
		for (int i = 0; i < L; i++) {
			st = new StringTokenizer(br.readLine());
			directions.put(Integer.parseInt(st.nextToken()), st.nextToken());
		}
		
		snake = new ArrayList<>();
		snake.add(new Node(0, 0, 0, 0));
		solution();
		
		bw.write(String.valueOf(answer));
		
		bw.flush();
		bw.close();
		br.close();
	}
}
