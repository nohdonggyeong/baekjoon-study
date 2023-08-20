package 단계별로_풀어보기.일반_수학_1.boj_1193_분수찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		int direction = 4; // 1 오른쪽, 2 왼쪽 아래, 3 아래 4 오른쪽 위
		int r = 2;
		int c = 0;
		int cnt = 0;
		
		int n = Integer.parseInt(br.readLine());
		while (cnt < n) {
			if (direction == 1) {
				c += 1;
				direction = 2;
			} else if (direction == 2) {
				r += 1;
				c -= 1;
				if (c == 1) {
					direction = 3;
				}
			} else if (direction == 3) {
				r += 1;
				direction = 4;
			} else if (direction == 4) {
				r -= 1;
				c += 1;
				if (r == 1) {
					direction = 1;
				}
			}
			cnt++;
		}
		bw.write(String.valueOf(r) + "/" + String.valueOf(c));
		bw.flush();
		bw.close();
		br.close();
	}
}
