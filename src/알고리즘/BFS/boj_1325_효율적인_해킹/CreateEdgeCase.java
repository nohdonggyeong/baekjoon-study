package 알고리즘.BFS.boj_1325_효율적인_해킹;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CreateEdgeCase {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try (BufferedOutputStream bs = new BufferedOutputStream(new FileOutputStream("/Users/donggyeong/develop/eclipse/eclipse-workspace/baekjoon-study/src/알고리즘/BFS/boj_1325_효율적인_해킹/edgeCase.txt"))) {
			// N <= 10000, M <= 100000
			int N = 10000;
			int M = 100000;
			sb.append(N).append(" ").append(M).append("\n");
			
			Random random = new Random();
			Set<int[]> set = new HashSet<int[]>();
			for (int i = 0; i < M; i++) {
				set.add(new int[] {random.nextInt(N) + 1, random.nextInt(N) + 1});	
			}
			for (int[] el : set) {
				sb.append(el[0]).append(" ").append(el[1]).append("\n");
			}
			
			bs.write(sb.toString().trim().getBytes());
		}catch (Exception e) {
			e.getStackTrace();
		}
	}

}
