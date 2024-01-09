package 알고리즘.시뮬레이션.boj_20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static int[] conveyor;
	static boolean[] robot;
	
	static int round;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		conveyor = new int[2 * N];
		robot = new boolean[N];
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < 2 * N; i++) {
			conveyor[i] = Integer.parseInt(st.nextToken());
		}
		
		round = 0;
		while (true) {
			round += 1;
			
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			int tempInt = conveyor[2 * N - 1];
			for (int i = 2 * N - 1; i >= 1; i--) {
				conveyor[i] = conveyor[i - 1];
			}
			conveyor[0] = tempInt;
			
			for (int i = N - 2; i >= 1; i--) {
				robot[i] = robot[i - 1];
			}
			robot[N - 1] = false;
			robot[0] = false;
			
			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			for (int i = N - 2; i >= 0; i--) {
				if (robot[i] && !robot[i + 1] && conveyor[i + 1] >= 1) {
					robot[i] = false;
					robot[i + 1] = true;
					conveyor[i + 1] -= 1;
				}
			}
			robot[N - 1] = false;
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			if (conveyor[0] != 0) {
				robot[0] = true;
				conveyor[0] -= 1;
			}
	
//			print();
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			int count = 0;
			for (int i = 0; i < 2 * N; i++) {
				if (conveyor[i] == 0) {
					count += 1;
				}
			}
			if (count >= K) {
				break;
			}
		}
		
		bw.write(String.valueOf(round));
		bw.flush();
		bw.close();
		br.close();
	}
	
	static void print() {
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.print(robot[i] ? "O " : "X ");
		}
		
		System.out.println();
		for (int i = 0; i < N; i++) {
			System.out.print(conveyor[i] + " ");
		}
		
		System.out.println();
		for (int i = 2 * N - 1; i >= N; i--) {
			System.out.print(conveyor[i] + " ");
		}
		System.out.println();
	}
}
