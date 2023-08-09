package algorithm.simulation.boj_17144_미세먼지_안녕;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.StringTokenizer;
import java.io.IOException;

public class Main {
	static int R, C, T;
	static int[][] A;
	static int purifierTop, purifierBottom;
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	
	static int getSum() {
		int sum = 0;
		for (int r = 0; r < R; r++) {
			for (int  c = 0; c < C; c++) {
				if (A[r][c] > 0) {
					sum += A[r][c];
				}
			}
		}
		return sum;
	}
	
	static void diffuse() {
		int[][] diffusion = new int[R][C];
		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				if (A[r][c] > 0) {
					int amountDiffusion = A[r][c] / 5; 
					for (int i = 0; i < 4; i++) {
						int nr = r + dr[i];
						int nc = c + dc[i];
						
						if (nr < 0 || nc < 0 || nr >= R || nc >= C || A[nr][nc] == -1) {
							continue;
						}
						diffusion[nr][nc] += amountDiffusion;
						A[r][c] -= amountDiffusion;
					}
				}
			}
		}

		for (int r = 0; r < R; r++) {
			for (int c = 0; c < C; c++) {
				A[r][c] += diffusion[r][c];
			}
		}
	}
	
	static void purify() {
		for (int r = purifierTop - 1; r > 0; r--) {
			A[r][0] = A[r - 1][0];
		}
		for (int c = 0; c < C - 1; c++) {
			A[0][c] = A[0][c + 1];
		}
		for (int r = 0; r < purifierTop; r++) {
			A[r][C - 1] = A[r + 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			A[purifierTop][c] = A[purifierTop][c - 1];
		}
		A[purifierTop][1] = 0;
		
		for (int r = purifierBottom + 1; r < R - 1; r++) {
			A[r][0] = A[r + 1][0];
		}
		for (int c = 0; c < C - 1; c++) {
			A[R - 1][c] = A[R - 1][c + 1];
		}
		for (int r = R - 1; r > purifierBottom; r--) {
			A[r][C - 1] = A[r - 1][C - 1];
		}
		for (int c = C - 1; c > 1; c--) {
			A[purifierBottom][c] = A[purifierBottom][c - 1];
		}
		A[purifierBottom][1] = 0;
	}
	
	public static void main(String args[]) throws IOException {
		LocalDateTime start = LocalDateTime.now();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		T = Integer.parseInt(st.nextToken());
		
		A = new int[R][C];
		
		purifierTop = -1;
		for (int r = 0; r < R; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < C; c++) {
				A[r][c] = Integer.parseInt(st.nextToken());
				if (A[r][c] == -1) {
					if (purifierTop == -1) {
						purifierTop = r;						
					} else {
						purifierBottom = r;
					}
				}
			}
		}
		
		for (int t = 0; t < T; t++) {
			diffuse();
			
//			System.out.println();
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(A[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
			
			purify();
			
//			System.out.println();
//			for (int r = 0; r < R; r++) {
//				for (int c = 0; c < C; c++) {
//					System.out.print(A[r][c] + " ");
//				}
//				System.out.println();
//			}
//			System.out.println();
		}
		
		bw.write(String.valueOf(getSum()));
		bw.flush();
		
		LocalDateTime end = LocalDateTime.now();
		System.out.println();
		System.out.println("[Elpased time: " + Duration.between(start, end).getSeconds() + " sec]");
		
		bw.close();
		br.close();
	}
}
