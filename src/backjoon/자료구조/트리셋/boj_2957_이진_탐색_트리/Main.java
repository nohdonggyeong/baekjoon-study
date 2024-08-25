package backjoon.자료구조.트리셋.boj_2957_이진_탐색_트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.TreeSet;

public class Main {
	static int N;
	static int[] nodeDepths;
	static TreeSet<Integer> nodeNumbers;
	
	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			
			N = Integer.parseInt(br.readLine()); // 1 ≤ N ≤ 300,000
			nodeDepths = new int[N + 2];         // 노드 깊이 기록:(lower guard 0) + (1 ~ N) + (higher guard N + 1)
			nodeNumbers = new TreeSet<>();       // 노드 번호 정렬: Red-Black Tree O(log n)
			
			nodeDepths[0] = -1; // root는 Math.max(-1, -1) + 1 = 0을 만들기 위해 -1로 설정
			nodeDepths[N + 1] = -1;
			nodeNumbers.add(0);
			nodeNumbers.add(N + 1);
			
			int number;
			long result = 0;
			for (int n = 1; n <= N; n++) {
				number = Integer.parseInt(br.readLine());
				nodeDepths[number] = Math.max(nodeDepths[nodeNumbers.lower(number)], nodeDepths[nodeNumbers.higher(number)]) + 1;
				nodeNumbers.add(number);
				result += nodeDepths[number];
				sb.append(result).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
