package algorithm.simulation.boj_17143_낚시왕;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int R, C, M;
	static List<Shark> sharkList;
	static int resultCount;
	static int[] dr= {0, -1, 1, 0, 0};
	static int[] dc= {0, 0, 0, 1, -1};
	static class Shark implements Comparable<Shark>{
		private int r;
		private int c;
		private int s;
		private int d;
		private int z;
		
		public Shark(int r, int c, int d) {
			this.r = r;
			this.c = c;
			this.d = d;
		}
		
		public Shark(int r, int c, int s, int d, int z) {
			this.r = r;
			this.c = c;
			this.s = s;
			this.d = d;
			this.z = z;
		}

		@Override
		public int compareTo(Shark o) {
			if (c - o.c > 0) { // 왼쪽부터
				return 1;
			} else if (c == o.c) {
				if (r - o.r > 0) { // 오른쪽부터
					return 1;
				} else if (r == o.r) {
					if (o.z - z > 0) { // 사이즈 큰 것부터
						return 1;
					} else if (o.z == z) {
						return 0;
					}
				}
			}
			return -1;
		}
	}
	
	static void catchShark(int c) {
		Collections.sort(sharkList);
		
		for (Shark el : sharkList) {
			if (el.c == c) {
				resultCount += el.z;
				sharkList.remove(el);
				break;
			}	
			if (el.c > c) {
				break;
			}
		}
	}
	
	static void moveShark() {
		List<Shark> newSharkList = new ArrayList<>();
		for (Shark el : sharkList) {
			int nr = el.r;
			int nc = el.c;
			int ns = el.s;
			int nd = el.d;
			int nz = el.z;
			for (int i = 0; i < el.s; i++) {
				if (nr + dr[nd] < 1 || nc + dc[nd] < 1 || nr + dr[nd] > R || nc + dc[nd] > C) {
					if (nd == 1) {
						nd = 2;
					} else if (nd == 2) {
						nd = 1;
					} else if (nd == 3) {
						nd = 4;
					} else if (nd == 4) {
						nd = 3;
					}
				}
				nr += dr[nd];
				nc += dc[nd];
			}
			newSharkList.add(new Shark(nr, nc, ns, nd, nz));
		}
		
		Collections.sort(newSharkList);
		if (newSharkList.size() > 0) {
			Shark shark = newSharkList.get(0);
			newSharkList.remove(0);	
			
			int r = shark.r;
			int c = shark.c;
			for (Shark el : newSharkList) {
				if (el.r != r || el.c != c) {
					r = el.r;
					c = el.c;
				}
			}
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		sharkList = new ArrayList<>();
		for (int m = 1; m <= M; m++) {
			st = new StringTokenizer(br.readLine());
			sharkList.add(new Shark(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())));
		}
		
		resultCount = 0;
		for (int c = 1; c <= C; c++) {			
			catchShark(c);
			moveShark();
		}
		
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
