package algorithm.permutation.boj_15686_치킨_배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, M;
	static List<int[]> houseList, chickenList;
	static int n, r;
	
	static int[] candidates;
	static boolean[] visited;
	static int[] temp;
	static List<int[]> selected;
	
	static int cityDistance;
	
	static void combination(int start, int depth) {
		if (depth == r) {
			temp = new int[r];
			int index = 0;
			for (int i = 0; i < n; i++) {
				if (visited[i]) {
					temp[index++] = candidates[i];
				}
			}
			selected.add(temp.clone());
			return;
		}
		
		for (int i = start; i < n; i++) {
			if (!visited[i]) {
				visited[i] = true;
				combination(i + 1, depth + 1);
				visited[i] = false;
			}
		}
	}
	
	static int getDistance(int x1, int y1, int x2, int y2) {
		return Math.abs(x1 - x2) + Math.abs(y1 - y2);
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		houseList = new ArrayList<>();
		chickenList = new ArrayList<>();
		for (int r = 0; r < N; r++) {
			st = new StringTokenizer(br.readLine());
			for (int c = 0; c < N; c++) {
				switch (Integer.parseInt(st.nextToken())) {
					case 1:
						houseList.add(new int[] {r, c});
						break;
					case 2:
						chickenList.add(new int[] {r, c});
						break;
					default:
						break;
				}
			}
		}
		
		n = chickenList.size();
		r = M;
		candidates = new int[n];
		for (int i = 0; i < n; i++) {
			candidates[i] = i;
		}
		
		visited = new boolean[n];
		selected = new ArrayList<>();
		combination(0, 0);
		
		cityDistance = Integer.MAX_VALUE;
		for (int[] el : selected) {
			int sumDistance = 0;
			for (int[] houseEl : houseList) {
				int houseDistance = Integer.MAX_VALUE;
				for (int e : el) {
					houseDistance = Math.min(houseDistance, getDistance(houseEl[0], houseEl[1], chickenList.get(e)[0], chickenList.get(e)[1]));
				}
				sumDistance += houseDistance;
			}
			cityDistance = Math.min(cityDistance, sumDistance);
		}
		
		bw.write(String.valueOf(cityDistance));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
