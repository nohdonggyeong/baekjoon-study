package sw검정_Advanced_취득_전략.기초수학;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int T;
	
	static double getArea(int x1, int y1, int x2, int y2, int x3, int y3) {
		// 겉면으로 사각형을 그려서 삼각형을 잘라낸다.
		double rectangle = (Math.max(y1, Math.max(y2, y3)) - Math.min(y1, Math.min(y2, y3)))
				* (Math.max(x1, Math.max(x2, x3)) - Math.min(x1, Math.min(x2, x3)));
		
		double triangle1 = (Math.abs(x1 - x2) * Math.abs(y1 - y2) / 2.0);
		double triangle2 = (Math.abs(x2 - x3) * Math.abs(y2 - y3) / 2.0);
		double triangle3 = (Math.abs(x3 - x1) * Math.abs(y3 - y1) / 2.0);
		
		return rectangle - triangle1 - triangle2 - triangle3;	
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		StringBuilder sb = new StringBuilder();
		
		T = Integer.parseInt(br.readLine());
		for (int t = 1; t <= T; t++) {
			st = new StringTokenizer(br.readLine());
			int x1 = Integer.parseInt(st.nextToken());
			int y1 = Integer.parseInt(st.nextToken());
			int x2 = Integer.parseInt(st.nextToken());
			int y2 = Integer.parseInt(st.nextToken());
			int x3 = Integer.parseInt(st.nextToken());
			int y3 = Integer.parseInt(st.nextToken());

			int minX = Math.min(x1, Math.min(x2, x3));
			int maxX = Math.max(x1, Math.max(x2, x3));
			int minY = Math.min(y1, Math.min(y2, y3));
			int maxY = Math.max(y1, Math.max(y2, y3));
			
			double area = getArea(x1, y1, x2, y2, x3, y3);
			int count = 0;
			for (int x = minX + 1; x < maxX; x++) {
				for (int y = minY + 1; y < maxY; y++) {
					// 점이 삼각형 안에 있다면 점을 기준으로 세 영역의 넓이 합이 삼각형의 넓이와 동일해야 한다.
					if (area == getArea(x, y, x2, y2, x3, y3)
							+ getArea(x1, y1, x, y, x3, y3)
							+ getArea(x1, y1, x2, y2, x, y)) {
						count += 1;
						
						System.out.println(String.valueOf(x) + ", " + String.valueOf(y));
					}
				}
			}
			System.out.println();
			sb.append("#").append(t).append(" ").append(count).append("\n");
		}
		bw.write(sb.toString().trim());
		bw.flush();
		bw.close();
		br.close();
	}
}

/**
 * refs.
 * 점이 삼각형 내부에 있는가? - https://80000coding.oopy.io/6241cb5b-b0fd-4c26-be30-cbd7c3870b67
 * 세 점의 좌표로 넓이 구하기 - https://m.blog.naver.com/eandimath/221760895905
 * 
 */