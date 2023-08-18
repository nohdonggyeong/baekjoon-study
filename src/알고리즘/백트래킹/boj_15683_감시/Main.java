package 알고리즘.백트래킹.boj_15683_감시;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

class CCTV {
	private int r;
	private int c;
	private int number;
	
	public CCTV(int r, int c, int number) {
		this.r = r;
		this.c = c;
		this.number = number;
	}
	
	public int getR() {
		return this.r;
	}
	
	public int getC() {
		return this.c;
	}
	
	public int getNumber() {
		return this.number;
	}
}

public class Main {
	static int N, M;
	static int[][] map;
	static int[][] visited;
	static List<CCTV> cctvList;
	static int minResult;
	
	static void northDFS(int r, int c, boolean reset) {
		if (!reset) {
			if (r - 1 >= 0 && map[r - 1][c] != 6) {
				visited[r - 1][c] += 1;
				northDFS(r - 1, c, false);
			}
		} else {
			if (r - 1 >= 0 && map[r - 1][c] != 6) {
				visited[r - 1][c] -= 1;
				northDFS(r - 1, c, true);
			}
		}
	}
	
	static void eastDFS(int r, int c, boolean reset) {
		if (!reset) {
			if (c + 1 < M && map[r][c + 1] != 6) {
				visited[r][c + 1] += 1;
				eastDFS(r, c + 1, false);
			}
		} else {
			if (c + 1 < M && map[r][c + 1] != 6) {
				visited[r][c + 1] -= 1;
				eastDFS(r, c + 1, true);
			}
		}
	}

	static void southDFS(int r, int c, boolean reset) {
		if (!reset) {
			if (r + 1 < N && map[r + 1][c] != 6) {
				visited[r + 1][c] += 1;
				southDFS(r + 1, c, false);
			}
		} else {
			if (r + 1 < N && map[r + 1][c] != 6) {
				visited[r + 1][c] -= 1;
				southDFS(r + 1, c, true);
			}
		}
	}
	static void westDFS(int r, int c, boolean reset) {
		if (!reset) {
			if (c - 1 >= 0 && map[r][c - 1] != 6) {
				visited[r][c - 1] += 1;
				westDFS(r, c - 1, false);
			}
		} else {
			if (c - 1 >= 0 && map[r][c - 1] != 6) {
				visited[r][c - 1] -= 1;
				westDFS(r, c - 1, true);
			}
		}
	}
	
	static int getCount() {
		int count = 0;
		for (int r = 0; r < N; r++) {
			for (int c = 0; c < M; c++) {
				if (map[r][c] == 0 && visited[r][c] == 0) {
					count++;
				}
			}
		}
		return count;
	}
	
	static void backTracking(int depth) {
		if (depth == cctvList.size()) {
			minResult = Math.min(minResult, getCount());
			return;
		}
		
		CCTV cctv = cctvList.get(depth);
		switch (cctv.getNumber()) {
		case 1:
			for (int i = 0; i < 4; i++) {
				switch(i) {
				case 0:
					northDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 1:
					eastDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					eastDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 2:
					southDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					southDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 3:
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				default:
					break;
				}
			}
			break;
		case 2:
			for (int i = 0; i < 2; i++) {
				switch(i) {
				case 0:
					eastDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					eastDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 1:
					northDFS(cctv.getR(), cctv.getC(), false);
					southDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					southDFS(cctv.getR(), cctv.getC(), true);
					break;
				default:
					break;
				}
			}
			break;
		case 3:
			for (int i = 0; i < 4; i++) {
				switch(i) {
				case 0:
					northDFS(cctv.getR(), cctv.getC(), false);
					eastDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					eastDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 1:
					eastDFS(cctv.getR(), cctv.getC(), false);
					southDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					eastDFS(cctv.getR(), cctv.getC(), true);
					southDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 2:
					southDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					southDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 3:
					northDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				default:
					break;
				}
			}
			break;
		case 4:
			for (int i = 0; i < 4; i++) {
				switch(i) {
				case 0:
					eastDFS(cctv.getR(), cctv.getC(), false);
					southDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					eastDFS(cctv.getR(), cctv.getC(), true);
					southDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 1:
					northDFS(cctv.getR(), cctv.getC(), false);
					southDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					southDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 2:
					northDFS(cctv.getR(), cctv.getC(), false);
					eastDFS(cctv.getR(), cctv.getC(), false);
					westDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					eastDFS(cctv.getR(), cctv.getC(), true);
					westDFS(cctv.getR(), cctv.getC(), true);
					break;
				case 3:
					northDFS(cctv.getR(), cctv.getC(), false);
					eastDFS(cctv.getR(), cctv.getC(), false);
					southDFS(cctv.getR(), cctv.getC(), false);
					
					backTracking(depth + 1);
					
					northDFS(cctv.getR(), cctv.getC(), true);
					eastDFS(cctv.getR(), cctv.getC(), true);
					southDFS(cctv.getR(), cctv.getC(), true);
					break;
				default:
					break;
				}
			}
			break;
		case 5:
			northDFS(cctv.getR(), cctv.getC(), false);
			eastDFS(cctv.getR(), cctv.getC(), false);
			southDFS(cctv.getR(), cctv.getC(), false);
			westDFS(cctv.getR(), cctv.getC(), false);
			
			backTracking(depth + 1);
			
			northDFS(cctv.getR(), cctv.getC(), true);
			eastDFS(cctv.getR(), cctv.getC(), true);
			southDFS(cctv.getR(), cctv.getC(), true);
			westDFS(cctv.getR(), cctv.getC(), true);
			break;
		default:
			break;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new int[N][M];
		cctvList = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if (map[r][c] != 0 && map[r][c] != 6) {
					cctvList.add(new CCTV(r, c, map[r][c]));
				}
			}
		}
		
		minResult = Integer.MAX_VALUE;
		backTracking(0);
		
		bw.write(String.valueOf(minResult));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
