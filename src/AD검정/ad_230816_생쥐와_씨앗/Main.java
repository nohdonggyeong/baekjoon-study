package AD검정.ad_230816_생쥐와_씨앗;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Queue;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int T, N, result, testNum = 5;
	static Queue<int[]> seedQueue;
	static int[] mouse, cat;
	static boolean breakFlag;
	
	static void chase(int[] nextDestination, int t, int count) {
		while (true) {
			// 생쥐가 1의 범위로 먼저 이동
			// 생쥐는 좌우 우선 최단으로 이동
			if (mouse[0] != nextDestination[0]) {
				mouse[0] += nextDestination[0] - mouse[0] > 0 ? 1 : -1;
			} else {
				mouse[1] += nextDestination[1] - mouse[1] > 0 ? 1 : -1;
			}
			
			if (t == testNum) {
				System.out.println("#" + t + "-" + count + ": mouse " + mouse[0] + ", " + mouse[1]);
			}
			
			// 생쥐가 고양이에게 잡혔나 확인
			if (mouse[0] == cat[0] && mouse[1] == cat[1]) {
				if (t == testNum) {
					System.out.println("#" + t + "-" + count + ": cat ate mouse.");
					System.out.println("-----------------------");
				}
				
				breakFlag = true;
				result = 0;
				return;
			}
			
			// 생쥐가 굴에 도착했나 확인
			if (mouse[0] == 0 && mouse[1] == 1) {
				if (t == testNum) {
					System.out.println("#" + t + "-" + count + ": rat has arrived in cave.");
					System.out.println("-----------------------");
				}
				
				breakFlag = true;
				return;
			}
			
			// 고양이가 2의 범위로 이동
			// 고양이는 상하 우선 최단으로 이동
			if (Math.abs(cat[1] - mouse[1]) >= 2) {
				cat[1] += mouse[1] - cat[1] > 0 ? 2 : -2;
			} else if (Math.abs(cat[1] - mouse[1]) == 1) {
				if (Math.abs(cat[0] - mouse[0]) >= 2) {
					cat[0] += mouse[0] - cat[0] > 0 ? 2 : -2;	
				} else {
					cat[1] += mouse[1] - cat[1] > 0 ? 1 : -1;
				}
			} else if (Math.abs(cat[1] - mouse[1]) == 0) {
				if (Math.abs(cat[0] - mouse[0]) >= 2) {
					cat[0] += mouse[0] - cat[0] > 0 ? 2 : -2;	
				} else if (Math.abs(cat[0] - mouse[0]) == 1) {
					cat[0] += mouse[0] - cat[0] > 0 ? 1 : -1;
				}
			}
			
			if (t == testNum) {
				System.out.println("#" + t + "-" + count + ": cat " + cat[0] + ", " + cat[1]);
				System.out.println("-----------------------");
			}
			
			// 고양이가 생쥐를 잡았나 확인
			if (mouse[0] == cat[0] && mouse[1] == cat[1]) {
				if (t == testNum) {
					System.out.println("#" + t + "-" + count + ": cat ate mouse.");
					System.out.println("-----------------------");
				}
				
				breakFlag = true;
				result = 0;
				return;
			}
			
			// 생쥐가 다음 목적지를 향해야 하나 확인
			if (mouse[0] == nextDestination[0] && mouse[1] == nextDestination[1]) {
				return;
			}
		}
	}
	
	public static void main(String args[]) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			// 주어지는 씨앗의 개수
			N = Integer.parseInt(br.readLine());
			
			// 주어지는 씨앗의 위치 x, y
			seedQueue = new LinkedList<>();
			for (int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine());
				seedQueue.offer(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			seedQueue.offer(new int[] {0, 1}); // 생쥐 구명 0, 1을 최종 목적지로 추가
			
			// 주어지는 고양이 시작 위치
			st = new StringTokenizer(br.readLine());
			cat = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
			
			// 쥐가 입력받은 씨앗 위치 순서대로 생쥐 구멍까지 이동하면서
			// 고양이에게 잡히나 안잡히나 확인
			result = 1;
			mouse = seedQueue.poll(); // 생쥐는 씨앗의 첫 입력 위치부터 시작
			breakFlag = false;
			
			int count = 0;
			if (t == testNum) {
				System.out.println("-----------------------");
				System.out.println("#" + t + "-" + count + ": mouse " + mouse[0] + ", " + mouse[1]);
				System.out.println("#" + t + "-" + count + ": cat " + cat[0] + ", " + cat[1]);
				System.out.println("-----------------------");
			}
			while (!breakFlag && !seedQueue.isEmpty()) {
				chase(seedQueue.poll(), t, ++count);
			}
			
			sb.append("#").append(t).append(" ").append(result).append("\n");	
		}
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
}
