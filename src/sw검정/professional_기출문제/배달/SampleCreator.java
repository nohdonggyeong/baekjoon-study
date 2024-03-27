package sw검정.professional_기출문제.배달;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Arrays;

public class SampleCreator {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();
		
		int N = 99_999;
		int M = 99_999;
		int D = 1_400_000_000;
		
		sb.append(N).append(" ").append(M).append(" ").append(D).append("\n");
		
		int[] randoms = new int[N];
		for (int n = 0; n < N; n++) {
			int randomNumber = (int) (Math.random() * 1_000_000_000);
			int randomSign = (int) (Math.random() * 10) % 2 == 1 ? 1 : -1;
			randoms[n] = randomNumber * randomSign;
		}
		Arrays.sort(randoms);
		
		for (int n = 0; n < N; n++) {
			sb.append(randoms[n]);
			if (n < N - 1) {
				sb.append(" ");
			} else {
				sb.append("\n");
			}
		}
		
		for (int m = 0; m < M; m++) {
			int randomNumberX = (int) (Math.random() * 2_000_000_000) - 1_000_000_000;
			int randomNumberY = (int) (Math.random() * 2_000_000_000) - 1_000_000_000;
			int randomSignX = (int) (Math.random() * 10) % 2 == 1 ? 1 : -1;
			int randomSignY = (int) (Math.random() * 10) % 2 == 1 ? 1 : -1;
			sb.append(randomNumberX * randomSignX).append(" ").append(randomNumberY * randomSignY).append("\n");
		}
		
		try {
		    OutputStream output = new FileOutputStream("C:\\Users\\user\\Desktop\\sampleInput.txt");
		    byte[] by= sb.toString().getBytes();
		    output.write(by);
		} catch (Exception e) {
	            e.getStackTrace();
		}
	}
}
