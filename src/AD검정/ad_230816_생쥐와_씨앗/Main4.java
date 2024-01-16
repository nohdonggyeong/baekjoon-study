package AD검정.ad_230816_생쥐와_씨앗;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main4 {
	// 다음 씨앗 목표 큐
	static Queue<Node> seedQueue;
	// 쥐, 고양이의 위치
	static Node mouse, cat;
	
	static class Node{
		private int x;
		private int y;
		
		public Node() {
			this.x = 0;
			this.y = 0;
		}
		
		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static boolean solution() {
		Node nextSeed = null;
		while (true) {			
			if (nextSeed == null) {
				if (mouse.x == 1 && mouse.y == 1) {
					return true;
				}
				
				if (seedQueue.isEmpty()) {
					return true;
				}
				
				nextSeed = seedQueue.poll();				
			}
			
			// 생쥐와 다음 씨앗 사이 가로 및 세로 길이 비교하고 생쥐 이동
			if (mouse.x - nextSeed.x == 0) {
				if (mouse.y > nextSeed.y) {
					mouse.y -= 1;
				} else {
					mouse.y += 1;
				}
			} else { // 쥐는 좌우우선
				if (mouse.x > nextSeed.x) {
					mouse.x -= 1;
				} else {
					mouse.x += 1;
				}
			}

			// 판단
			if (cat.x == mouse.x && cat.y == mouse.y) {
				return false;
			}
			
			// 고양이와 생쥐 사이 가로 및 세로 길이 비교하고 고양이 이동
			if (Math.abs(cat.y - mouse.y) >= 2) {
				// 상하 2 이동
				if (cat.y > mouse.y) {
					cat.y -= 2;
				} else {
					cat.y += 2;
				}
			} else if (Math.abs(cat.y - mouse.y) == 1) {
				// x가 2보다 크거나 같으면 좌우 이동
				// 그렇지 않으면 상하 1 이동
				if (Math.abs(cat.x - mouse.x) >= 2) {
					if (cat.x > mouse.x) {
						cat.x -= 2;
					} else {
						cat.x += 2;
					}
				} else {
					if (cat.y > mouse.y) {
						cat.y -= 1;
					} else {
						cat.y += 1;
					}
				}
			} else {
				// 쥐와 고양이가 y값이 같다면 좌우 이동
				if (Math.abs(cat.x - mouse.x) >= 2) {
					if (cat.x > mouse.x) {
						cat.x -= 2;
					} else {
						cat.x += 2;
					}
				} else {
					if (cat.x >= mouse.x) {
						cat.x -= 1;
					} else {
						cat.x += 1;
					}
				}
			}
			
			// 판단
			if (cat.x == mouse.x && cat.y == mouse.y) {
				return false;
			}
			
			// 다음 씨앗을 목표로 함
			if (mouse.x == nextSeed.x && mouse.y == nextSeed.y) {
				nextSeed = null;
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
				
		int T = Integer.parseInt(br.readLine());
		for (int t = 0 ; t < T; t++) {
			seedQueue = new LinkedList<>();
			int N = Integer.parseInt(br.readLine());
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				seedQueue.offer(new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
			}
			seedQueue.offer(new Node(1, 1)); // 쥐굴
			
			st = new StringTokenizer(br.readLine());
			cat = new Node(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
			mouse = seedQueue.poll();
			
			// solution
			boolean result = solution();
			
			// print result
			sb.append("#").append(String.valueOf(t + 1)).append(" ").append(result ? "1" : "0").append("\n");
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
