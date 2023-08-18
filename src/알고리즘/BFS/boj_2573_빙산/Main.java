package 알고리즘.BFS.boj_2573_빙산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static int[][] map;
	static boolean[][] visit;
	static int[] dx = {-1, 1, 0, 0};
	static int[] dy = {0, 0, -1, 1};
	
	static void melt() {
		visit = new boolean[N][M];
		Queue<Node> queue = new LinkedList<>();
		
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] >0) {
					queue.offer(new Node(i, j));
					visit[i][j] = true;
				}
			}
		}
		
		while (!queue.isEmpty()) {
			Node node = queue.poll();
			int seaCnt = 0;
			
			for (int k = 0; k < 4; k++) {
				int ni = node.x + dx[k];
				int nj = node.y + dy[k];
				
				if (ni >= 0 && nj >= 0 && ni < N && nj < M
						&& !visit[ni][nj] && map[ni][nj] == 0) {
					seaCnt++;
				}
			}
			
			if(map[node.x][node.y] - seaCnt > 0) {
				map[node.x][node.y] -= seaCnt;
			} else {
				map[node.x][node.y] = 0;
			}
		}
	}
	
	static class Node {
		private int x;
		private int y;
		
		Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
	
	static int groupCnt() {
        visit = new boolean[N][M];
 
        int cnt = 0;
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] != 0 && !visit[i][j]) {
                	dfs(i, j);
                    cnt++;
                }
            }
        }
        return cnt;
    }
	
	static void dfs(int i, int j) {
		visit[i][j] = true;
		
		for (int k = 0; k < 4; k++) {
			int ni = i + dx[k];
			int nj = j + dy[k];
			
			if (ni >= 0 && nj >= 0 && ni < N && nj < M
					&& map[ni][nj] > 0 && !visit[ni][nj]) {
				dfs(ni, nj);
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		// Input
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < M; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// Process
		int cnt, result = 0;
		while ((cnt  = groupCnt()) < 2) {
			if (cnt == 0) {
				result = 0;
				break;
			}
			melt();
			result++;
		}
		
		// Output
		bw.write(String.valueOf(result));
		
		bw.flush();
		bw.close();
		br.close();
		
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < M; j++) {
//				System.out.print(map[i][j] + " ");
//			}
//			System.out.println();
//		}
	}
}
