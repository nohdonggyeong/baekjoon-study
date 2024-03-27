package backjoon.자료구조.수학.boj_23971_ZOAC_4;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	public static void main(String args[]) throws IOException {
		// H, W, N, M이 공백으로 구분되어 주어진다. (0 < H, W, N, M ≤ 50,000)
		// e.g. 5 4 1 1
		// 가로 i + 1, 세로 j + 1 크기의 사각형이 주어진 H, W 큰 사각형에 몇 개 들어가는 가를 출력
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		int H = Integer.parseInt(st.nextToken());
		int W = Integer.parseInt(st.nextToken());
		int i = Integer.parseInt(st.nextToken());
		int j = Integer.parseInt(st.nextToken());
		
		int HRemain = H % (i + 1) > 0 ? 1 : 0;
		int WRemain = W % (j + 1) > 0 ? 1 : 0;
		int result = (H / (i + 1) + HRemain) * (W / (j + 1) + WRemain);
		bw.write(String.valueOf(result));
		bw.flush();
		bw.close();
		br.close();
	}
}
