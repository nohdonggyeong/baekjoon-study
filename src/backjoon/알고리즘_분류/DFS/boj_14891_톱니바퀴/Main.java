package backjoon.알고리즘_분류.DFS.boj_14891_톱니바퀴;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[][] gear;
	static int[][] gearBak;
	static int K;
	static int[][] input;
	static boolean[] visited;
	static int answer;
	
	static void rotate(int gearNum, int direction) {
		visited[gearNum] = true;
		
		int temp;
		if (direction == 1) {
			temp = gear[gearNum][7];
			for (int i = 7; i > 0; i--) {
				gear[gearNum][i] = gear[gearNum][i - 1];
			}
			gear[gearNum][0] = temp;
		} else {
			temp = gear[gearNum][0];
			for (int i = 0; i < 7; i++) {
				gear[gearNum][i] = gear[gearNum][i + 1];
			}
			gear[gearNum][7] = temp;
		}
		
		switch(gearNum) {
		case 0:
			if (!visited[1] && gearBak[0][2] != gearBak[1][6]) {
				rotate(1, -direction);
			}
			break;
		case 1:
			if (!visited[0] && gearBak[1][6] != gearBak[0][2]) {
				rotate(0, -direction);
			}
			if (!visited[2] && gearBak[1][2] != gearBak[2][6]) {
				rotate(2, -direction);
			}
			break;
		case 2:
			if (!visited[1] && gearBak[2][6] != gearBak[1][2]) {
				rotate(1, -direction);
			}
			if (!visited[3] && gearBak[2][2] != gearBak[3][6]) {
				rotate(3, -direction);
			}
			break;
		case 3:
			if (!visited[2] && gearBak[3][6] != gearBak[2][2]) {
				rotate(2, -direction);
			}
			break;
		default:
			break;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		String str;
		StringTokenizer st;
		
		gear = new int[4][8];
		
		for (int i = 0; i < 4; i++) {
			str = br.readLine();
			for (int j = 0; j < 8; j++) {
				gear[i][j] = Character.getNumericValue(str.charAt(j));
			}
		}
		
		K = Integer.parseInt(br.readLine());
		input = new int[K][2];
		for (int i = 0; i < K; i++) {
			st = new StringTokenizer(br.readLine());
			input[i][0] = Integer.parseInt(st.nextToken()) - 1;
			input[i][1] = Integer.parseInt(st.nextToken());
		}
		
		// 풀이
		for (int i = 0; i < K; i++) {
			visited = new boolean[4];
			gearBak = new int[4][8];
			for (int j = 0; j < 4; j++) {
				gearBak[j] = gear[j].clone();
			}
			rotate(input[i][0], input[i][1]);
		}
		
		answer = gear[0][0] + gear[1][0] * 2 + gear[2][0] * 4 + gear[3][0] * 8;
		bw.write(String.valueOf(answer));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
