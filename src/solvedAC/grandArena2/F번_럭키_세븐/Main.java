package solvedAC.grandArena2.F번_럭키_세븐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

public class Main {
	static int T, N;
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			N = Integer.parseInt(br.readLine());
			
			Queue<Integer> queue = new LinkedList<>();
			queue.offer(1);
			for (int n = 0; n < N; n++) {
				String[] strArr = br.readLine().split(" ");
				for (int i = 0; i < (int) Math.pow(2, n + 1); i++) {
					int number = queue.poll();
					
					if ("+".equals(strArr[0])) {
						queue.offer(number + Integer.parseInt(strArr[1]));
					} else {
						queue.offer(number * Integer.parseInt(strArr[1]));
					}
	
					if ("+".equals(strArr[2])) {
						queue.offer(number + Integer.parseInt(strArr[3]));
					} else {
						queue.offer(number * Integer.parseInt(strArr[3]));
					}
				}
			}
			
			boolean check = false;
			while (!queue.isEmpty()) {
				if (queue.poll() % 7 == 0) {
					check = true;
					break;
				}
			}
			
			if (check) {
				bw.write("LUCKY");
			} else {
				bw.write("UNLUCKY");
			}
			bw.newLine();
		}
		
		bw.flush();
		
		bw.close();
		br.close();
	}
}
