package sw검정.advanced_기출문제.ad_230421_와트의_카드게임;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int T;
	static int K, Q;
	
	static int[] gamePoint;
	static List<int[]> output;
	static List<int[]> compareList;
	
	static void backTracking(int depth, int watt, int hans, int snow) {
		if (depth == K) {
			output.add(new int[] {watt, hans, snow});
			return;
		}
		
		int minusWatt = 0;
		int minusSnow = 0;
		int minusHans = 0;
		for (int i = 0; i < 3; i++) {
			switch(i) {
			case 0: // 와트 승리
				minusSnow = snow >= gamePoint[depth] ? gamePoint[depth] : snow;
				minusHans = hans >= gamePoint[depth] ? gamePoint[depth] : hans;
				watt = watt + minusSnow + minusHans;
				snow -= minusSnow;
				hans -= minusHans;

//				System.out.println("depth: " + depth + ", i: " + i);
//				System.out.println("watt: " + watt + ", hans: " + hans + ", snow: " + snow);
				
				backTracking(depth + 1, watt, hans, snow);
				
				watt = watt - minusSnow - minusHans;
				snow += minusSnow;
				hans += minusHans;
				
				break;
			case 1: // 한스 승리
				minusSnow = snow >= gamePoint[depth] ? gamePoint[depth] : snow;
				minusWatt = watt >= gamePoint[depth] ? gamePoint[depth] : watt;
				hans = hans + minusSnow + minusWatt;
				snow -= minusSnow;
				watt -= minusWatt;

//				System.out.println("depth: " + depth + ", i: " + i);
//				System.out.println("watt: " + watt + ", hans: " + hans + ", snow: " + snow);
				
				backTracking(depth + 1, watt, hans, snow);
				
				hans = hans - minusSnow - minusWatt;
				snow += minusSnow;
				watt += minusWatt;
				
				break;
			case 2: // 스노우 승리
				minusWatt = watt >= gamePoint[depth] ? gamePoint[depth] : watt;
				minusHans = hans >= gamePoint[depth] ? gamePoint[depth] : hans;
				snow = snow + minusWatt + minusHans;
				watt -= minusWatt;
				hans -= minusHans;

//				System.out.println("depth: " + depth + ", i: " + i);
//				System.out.println("watt: " + watt + ", hans: " + hans + ", snow: " + snow);
				
				backTracking(depth + 1, watt, hans, snow);
				
				snow = snow - minusWatt - minusHans;
				watt += minusWatt;
				hans += minusHans;
				
				break;
			default:
				break;
			}
		}
	}
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int watt = Integer.parseInt(st.nextToken());
			int hans = Integer.parseInt(st.nextToken());
			int snow = Integer.parseInt(st.nextToken());
			
			K = Integer.parseInt(st.nextToken());
			Q = Integer.parseInt(st.nextToken());
			
			gamePoint = new int[K];
			st = new StringTokenizer(br.readLine());
			for (int k = 0; k < K; k++) {
				gamePoint[k] = Integer.parseInt(st.nextToken());
			}
			
			compareList = new ArrayList<int[]>();
			for (int q = 0; q < Q; q++) {
				st = new StringTokenizer(br.readLine());
				compareList.add(new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())});
			}
			
			// 와트 한스 스노우가 각각 이기는 백트래킹, boolean을 false로 되돌리는 작업
			// dfs로 가능한 결과 조합을 구해보고 compareList를 포함하면 1 출력
			output = new ArrayList<>();
			backTracking(0, watt, hans, snow);
			
			sb.append("#").append(t);
			for (int[] el : compareList) {
				boolean flag = false;
				for (int[] op : output) {
					if (Arrays.equals(op, el)) {
						flag = true;
						break;
					}
				}
				
				if (flag) {
					sb.append(" 1");
				} else {
					sb.append(" 0");
				}
			}
			sb.append("\n");
		}
		
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}
