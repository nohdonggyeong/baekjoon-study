package backjoon.브루트포스.boj_15685_드래곤_커브;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static int N, x, y, d, g;
	static boolean[][] map;
	
	static List<Integer> getDirections(int d, int g) {
		List<Integer> directions = new ArrayList<>();
		directions.add(d);
		
		while (g-- > 0) {
			for (int i = directions.size() - 1; i >= 0; i--) {
				int direction = (directions.get(i) + 1) % 4;
				directions.add(direction);
			}
		}
		return directions;
	}
	
	static void draw(int x, int y, List<Integer> directions) {
		map[x][y] = true;
		
		for (int direction : directions) {
			switch (direction) {
			case 0:
				map[++x][y] = true;
				break;
			case 1:
				map[x][--y] = true;
				break;
			case 2:
				map[--x][y] = true;
				break;
			case 3:
				map[x][++y] = true;
				break;
			default:
				break;
			}
		}
	}
	
	static int getSquareCount() {
		int count = 0;
		for (int x = 0; x < 100; x++) {
			for (int y = 0; y < 100; y++) {
				if (map[x][y] && map[x + 1][y] && map[x][y + 1] && map[x + 1][y + 1]) {
					count++;
				}
			}
		}
		return count;
	}
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		map = new boolean[101][101];
		
		N = Integer.parseInt(br.readLine());
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			x = Integer.parseInt(st.nextToken());
			y = Integer.parseInt(st.nextToken());
			d = Integer.parseInt(st.nextToken());
			g = Integer.parseInt(st.nextToken());
			
			draw(x, y, getDirections(d, g));
		}
		
		bw.write(String.valueOf(getSquareCount()));
		bw.flush();
		
		bw.close();
		br.close();
	}
}
