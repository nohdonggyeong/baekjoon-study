package algorithm.simulation.boj_20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

/*
 * 1. 아이디어
 *   - 시뮬레이션: 문제 대로 순서 구현하자.
 * 2. 시간복잡도
 *   - 1차원 배열(최대 100)마다 내구도 1000 = 100000 < 1억보다 작으므로 1초 이내 가능
 * 3. 자료구조
 *   - 벨트 클래스: 내구도, 로봇 있나
 *   - 벨트 클래스를 사용하는 윗 줄 큐, 아래 줄 큐
*/

public class Main {
	static int N, K;
	static Queue<Belt> topQueue, bottomQueue;
	static int stage, brokenCnt;
	static List<Belt> tempList;
	
	static class Belt {
		private int durability;
		private boolean robot;
		
		public Belt(int durability, boolean robot) {
			this.durability = durability;
			this.robot = robot;
		}
	}
	
	static void solution() {
		// 문제 표시되는 단계
		while (true) {
			// 1. 벨트가 각 칸 위에 있는 로봇과 함께 한 칸 회전한다.
			Belt down = topQueue.poll();
			bottomQueue.offer(new Belt(down.durability, false));
			
			// 3. 올리는 위치에 있는 칸의 내구도가 0이 아니면 올리는 위치에 로봇을 올린다.
			Belt up = bottomQueue.poll();
			if (up.durability == 1) {
				brokenCnt += 1;
			}
			topQueue.offer(new Belt(up.durability > 0 ? up.durability - 1 : 0, up.durability > 0 ? true : false));

			// 2. 가장 먼저 벨트에 올라간 로봇부터, 벨트가 회전하는 방향으로 한 칸 이동할 수 있다면 이동한다. 만약 이동할 수 없다면 가만히 있는다.
			// 로봇이 이동하기 위해서는 이동하려는 칸에 로봇이 없으며, 그 칸의 내구도가 1 이상 남아 있어야 한다.
			tempList = new ArrayList<>();
			while (!topQueue.isEmpty()) {
				// tempList는 topQueue 역순
				tempList.add(topQueue.poll());
			}
			tempList.set(0, new Belt(tempList.get(0).durability, false));
			for (int i = 1; i < tempList.size(); i++) {
				if (!tempList.get(i - 1).robot && tempList.get(i - 1).durability > 0) {
					if (tempList.get(i - 1).durability == 1) {
						brokenCnt += 1;
					}
					tempList.set(i - 1, new Belt(tempList.get(i - 1).durability - 1, true));
					tempList.set(i, new Belt(tempList.get(i).durability, false));
				}
			}
			for (int i = 0; i < tempList.size(); i++) {
				topQueue.offer(new Belt(tempList.get(i).durability, tempList.get(i).robot));
			}
			
			// 4. 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료한다. 그렇지 않다면 1번으로 돌아간다.
			if (brokenCnt >= K) {
				return;
			}
			stage++;	
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken()); // 벨트 반의 길이
		K = Integer.parseInt(st.nextToken()); // 내구도가 0인 칸의 개수가 K개 이상이라면 과정을 종료
		
		topQueue = new LinkedList<>();
		bottomQueue = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		// 1 ≤ Ai ≤ 1,000이기 때문에 내구도가 0인 입력 확인 안 함
		for (int n = N - 1; n >= 0; n--) {
			topQueue.offer(new Belt(Integer.parseInt(st.nextToken()), false));
		}
		for (int n = N * 2 - 1; n >= N; n--) {
			bottomQueue.offer(new Belt(Integer.parseInt(st.nextToken()), false));
		}
		
		stage = 1;
		brokenCnt = 0;
		solution();
		
		bw.write(String.valueOf(stage));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
