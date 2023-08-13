package solvedAC.grandArena2.D번_Sum_Product;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main_bak {
	static int N;
	static int[] A;
	
	static int[] input;
	static int[] temp;
	static List<int[]> output;
	
	static int resultCount;
	
	static void combinationRepetition(int start, int depth) {
		if (depth == 2) {
			output.add(temp.clone());
			return;
		}
		
		for (int i = start; i < N; i++) {
			temp[depth] = input[i];
			combinationRepetition(i, depth + 1);
		}
	}
	
	static int getLeftValue(int[] el) {
		int result = 0;
		for (int i = el[0]; i <= el[1]; i++) {
			result += A[i];
		}
		return result;
	}
	
	static int getRightValue(int[] el) {
		int result = 1;
		for (int i = el[0]; i <= el[1]; i++) {
			result *= A[i];
		}
		return result;
	}
	
	public static void main(String args[]) throws IOException {
		LocalDateTime start = LocalDateTime.now();

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		A = new int[N];

		input = new int[N];
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			A[i] = Integer.parseInt(st.nextToken());
			input[i] = i;
		}
		
		temp = new int[2];
		output = new ArrayList<>();
		combinationRepetition(0, 0);
		
		resultCount = 0;
		for (int[] el : output) {
			if (getLeftValue(el) == getRightValue(el)) {
				resultCount++;
			}
		}
		bw.write(String.valueOf(resultCount));
		bw.flush();
		
		LocalDateTime end = LocalDateTime.now();
		System.out.println("[Elapsed time: " + Duration.between(start, end).getSeconds() + " sec]");
		bw.close();
		br.close();
	}
}
