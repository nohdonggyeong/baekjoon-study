package jungol.jo_1225_사람감시;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N;
	static double area, radiusSqueareSum;
	static Point center1, center2;
	static Point[] people;
	static double radius1, radius2;
	static int maxCount, count;
	
	static class Point {
		double x;
		double y;
		double dist1;
		double dist2;
		
		Point(double x, double y) {
			this.x = x;
			this.y = y;
		}
		
		void setDist(double dist1, double dist2) {
			this.dist1 = dist1;
			this.dist2 = dist2;
		}
	}
	
	public static void main(String[] args) throws IOException, NumberFormatException {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			people = new Point[N];
			
			st = new StringTokenizer(br.readLine());
			center1 = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			center2 = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
			area = Double.parseDouble(st.nextToken());
			
			radiusSqueareSum = area / 3.141;
			
			for (int n = 0; n < N; n++) {
				st = new StringTokenizer(br.readLine());
				people[n] = new Point(Double.parseDouble(st.nextToken()), Double.parseDouble(st.nextToken()));
				people[n].setDist(Math.sqrt(Math.pow(center1.x - people[n].x, 2) + Math.pow(center1.y - people[n].y, 2)),
						Math.sqrt(Math.pow(center2.x - people[n].x, 2) + Math.pow(center2.y - people[n].y, 2)));
			}
			
			maxCount = 0;
			for (int n = 0; n < N; n++) {
				radius1 = people[n].dist1;
				if (Math.pow(radius1, 2) > radiusSqueareSum) {
					continue;
				}
				radius2 = Math.sqrt(radiusSqueareSum - Math.pow(radius1, 2));
				
				count = 0;
				for (int m = 0; m < N; m++) {
					if (people[m].dist1 <= radius1 || people[m].dist2 <= radius2) {
						count++;
					}
				}
				
				maxCount = Math.max(maxCount, count);
			}
			
			for (int n = 0; n < N; n++) {
				radius2 = people[n].dist2;
				if (Math.pow(radius2, 2) > radiusSqueareSum) {
					continue;
				}
				radius1 = Math.sqrt(radiusSqueareSum - Math.pow(radius2, 2));
				
				count = 0;
				for (int m = 0; m < N; m++) {
					if (people[m].dist1 <= radius1 || people[m].dist2 <= radius2) {
						count++;
					}
				}
				
				maxCount = Math.max(maxCount, count);
			}
			
			bw.write(String.valueOf(N - maxCount));
			bw.flush();
			
		}
	}
}
