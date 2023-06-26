package 삼성SW역량테스트.boj_12100_2048_Easy;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, answer = 0;
	static int[][] map;
	
	public static void findMax() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				answer = Math.max(answer, map[i][j]);
			}
		}
	}
	
	public static void game(int count) {
		// 5번 실행하고 재귀함수 종료
		if (count == 5) {
			findMax();
			return;
		}
		
		// map을 copy로 백업
		int copy[][] = new int[N][N];
		for (int i = 0; i < N; i++) {
			copy[i] = map[i].clone();
		}
		
		// move up, down, left, right 
		for (int i = 0; i < 4; i++) {
			move(i);
			game(count + 1);
			for (int k = 0; k < N; k++) {
				// map을 백업 copy로부터 복구
				map[k] = copy[k].clone();
			}
		}
			
	}
	
	public static void move(int dir) {
		switch(dir) {
		// move up
		case 0:
			for (int i = 0; i < N; i++) { // columne
				int index = 0; // 0이 아닌 현재 탐색 위치
				int block = 0; // 0이 아닌 현재 탐색 값
				for (int j = 0; j < N; j++) { // row
					if (map[j][i] != 0) { // 지금 확인하는 칸이 0이 아니면 작업
						if (block == map[j][i]) {
							map[index - 1][i] = block * 2; // 마지막으로 값이 있었던 곳으로 더함
							block = 0;
							map[j][i] = 0; // 지금 확인하는 칸을 0으로 바꿈
						} else {
							block = map[j][i]; // 지금 확인하는 칸의 값을 block으로 저장
							map[j][i] = 0; // 지금 확인하는 칸을 0으로 바꿈
							map[index][i] = block; // 마지막으로 값이 있었던 바로 아래줄로 지금 확인한 칸의 값을 기입
							index++;
						}
					}
				}
			}
			break;
			
		// move down
		case 1:
			for (int i = 0; i < N; i++) {
				int index = N - 1;
				int block = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (map[j][i] != 0) {
						if (block == map[j][i]) {
							map[index + 1][i] = block * 2;
							block = 0;
							map[j][i] = 0;
						} else {
							block = map[j][i];
							map[j][i] = 0;
							map[index][i] = block;
							index--;
						}
					}
				}
			}
			break;
			
		// move left
		case 2:
			for (int i = 0; i < N; i++) {
				int index = 0;
				int block = 0;
				for (int j = 0; j < N; j++) {
					if (map[i][j] != 0) {
						if (block == map[i][j]) {
							map[i][index - 1] = block * 2;
							block = 0;
							map[i][j] = 0;
						} else {
							block = map[i][j];
							map[i][j] = 0;
							map[i][index] = block;
							index++;
						}
					}
				}
			}
			break;
			
		// move right
		case 3:
			for (int i = 0; i < N; i++) {
				int index = N - 1;
				int block = 0;
				for (int j = N - 1; j >= 0; j--) {
					if (map[i][j] != 0) {
						if (block == map[i][j]) {
							map[i][index + 1]  = block * 2;
							block = 0;
							map[i][j] = 0;
						} else {
							block = map[i][j];
							map[i][j] = 0;
							map[i][index] = block;
							index--;
						}
					}
				}
			}
			break;
			
		default:
			break;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		game(0);
		
		bw.write(String.valueOf(answer));
		bw.flush();
		bw.close();
		br.close();
	}
}
