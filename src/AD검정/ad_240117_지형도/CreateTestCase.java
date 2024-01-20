package AD검정.ad_240117_지형도;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.Random;

public class CreateTestCase {

	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			int T = 5;
			sb.append(T).append("\n");
			
			Random random = new Random();
			int[] N = new int[] {4, 5, 10, 15, 40};
			for (int t = 0; t < T; t++) {
				sb.append(N[t]).append("\n");
				
				for (int c = 0; c < N[t]; c++) {
					for (int r = 0; r < N[t]; r++) {	
						sb.append(random.nextInt(10));
						if (r < N[t] - 1) {
							sb.append(" ");
						}
					}
					sb.append("\n");
				}
				
				int W = random.nextInt(N[t] - 1) + 1;
				int H = random.nextInt(N[t] - 1) + 1;
				sb.append(W).append(" ").append(H).append("\n");
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}

}
