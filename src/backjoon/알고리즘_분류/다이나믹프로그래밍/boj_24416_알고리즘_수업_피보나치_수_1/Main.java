package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_24416_알고리즘_수업_피보나치_수_1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int n;
	static int[] f;
	static int countFib, countFibonacci;
	
	static int fib(int n) {
		if (n == 1 || n ==2) {
			return 1;
		} else {
			countFib++;
			return fib(n - 1) + fib(n - 2); 
		}
	}
	
	static int fibonacci(int n) {
		f[1] = f[2] = 1;
		for(int i = 3; i <= n; i++) {
			countFibonacci++;
			f[i] = f[i - 1] + f[i - 2];
		}
		return f[n];
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringBuilder sb = new StringBuilder();
			
			n = Integer.parseInt(br.readLine());
			f = new int[n + 1];
			
			countFib = countFibonacci = 0;
			fib(n);
			fibonacci(n);
			
			sb.append(countFib + 1).append("\n").append(countFibonacci);
			bw.write(sb.toString());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
