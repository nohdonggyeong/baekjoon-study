package sw검정_이것만_따라하자.실전_예제_풀어보기_5회;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int cx, cy, r;
	static int sx, sy;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		for (int t = 0; t < 3; t++) {
			st = new StringTokenizer(br.readLine());
			cx = Integer.parseInt(st.nextToken());
			cy = Integer.parseInt(st.nextToken());
			r = Integer.parseInt(st.nextToken());
			
			st = new StringTokenizer(br.readLine());
			sx = Integer.parseInt(st.nextToken());
			sy = Integer.parseInt(st.nextToken());
			
			double range = Math.sqrt(Math.pow(cx - sx, 2) + Math.pow(cy - sy, 2));
			bw.write(range <= r ? "1" : "0");
			bw.newLine();
		}
		bw.flush();
		bw.close();
		br.close();
	}

}
