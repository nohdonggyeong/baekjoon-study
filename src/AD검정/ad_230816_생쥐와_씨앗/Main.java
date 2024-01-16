package AD검정.ad_230816_생쥐와_씨앗;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int T, answer;
	static int N;
	static Queue<int[]> seedQueue;
	static int[] mouse, cat;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			N = Integer.parseInt(br.readLine());
			
			// 씨앗 입력
			seedQueue = new LinkedList<int[]>();
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				int x = Integer.parseInt(st.nextToken());
				int y = Integer.parseInt(st.nextToken());
				seedQueue.offer(new int[] {y, x});
			}	

			// 고양이 시작 위치
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			cat = new int[] {y, x};
			
			// 쥐 시작 위치
			mouse = seedQueue.poll();
			
			// 쥐굴 입력
			seedQueue.offer(new int[] {1, 0});
			
			// 쥐가 움직이기 시작
			answer = 0;
			loop1:
			while (!seedQueue.isEmpty()) {
				
				int[] target = seedQueue.poll();
				
				loop2:
				while (target[0] != mouse[0] || target[1] != mouse[1]) {
					// 쥐 이동
					if ((target[1] == mouse[1])
							|| (mouse[0] != 1 && mouse[1] - 1 == 0)) {
						mouse = new int[] {target[0] > mouse[0] ? mouse[0] + 1 : mouse[0] - 1, mouse[1]};
					} else { // 최대 1만큼 기본 좌우 이동한다.
						mouse = new int[] {mouse[0], target[1] > mouse[1] ? mouse[1] + 1 : mouse[1] - 1};
					}
					
					// 쥐굴 도착했는지 판단
					if (mouse[0] == 1 && mouse[1] == 0) {
						answer = 1;
						break loop1;
					}
					
					// 쥐와 고양이의 위치가 같은지 판단
					if (mouse[0] == cat[0] && mouse[1] == cat[1]) {
						break loop1;
					}
					
					// 고양이 이동
					if ((Math.abs(mouse[0] - cat[0]) == 1 && Math.abs(mouse[1] - cat[1]) > 1)
							|| (Math.abs(mouse[0] - cat[0]) == 0)) { // 고양이와 쥐가 상하가 2보다 작게 차이나고 좌우 차이가 상하 차이보다 커야 한다.
						// 좌우 이동
						if (Math.abs(mouse[1] - cat[1]) > 1) {
							cat = new int[] {cat[0], mouse[1] > cat[1] ? cat[1] + 2 : cat[1] - 2};
						} else {
							cat = new int[] {cat[0], mouse[1] > cat[1] ? cat[1] + 1 : cat[1] - 1};
						}
					} else { // TODO: 최대 2만큼 기본 상하 이동한다.
						if (Math.abs(mouse[0] - cat[0]) > 1) {
							cat = new int[] {mouse[0] > cat[0] ? cat[0] + 2 : cat[0] - 2, cat[1]};
						} else {
							cat = new int[] {mouse[0] > cat[0] ? cat[0] + 1 : cat[0] - 1, cat[1]};
						}
					}
					
					// 쥐와 고양이의 위치가 같은지 판단
					if (mouse[0] == cat[0] && mouse[1] == cat[1]) {
						break loop1;
					}
				}
			}
			
			
			sb.append("#").append(t).append(" ").append(answer).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
