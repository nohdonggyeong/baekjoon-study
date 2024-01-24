package 알고리즘.세그먼트트리.boj_2042_구간_합_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int N, M, K;
	static long[] tree;
	static int cacheEnd;
	
	static void init(int index) {
		while (index > 1) {
			tree[index / 2] += tree[index];
			index--;	
		}
	}
	
	static void update(int index, long number) {
		long diff = number - tree[index];
		while (index > 0) {
			tree[index] += diff;
			index /= 2;
		}
	}
	
	static long sum(int start, int end) {
		long sum = 0;
		while (start <= end) {
			if (start % 2 == 1) {
				sum += tree[start];
				start++;
			}
			if (end % 2 == 0) {
				sum += tree[end];
				end--;
			}
			start /= 2;
			end /= 2;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			M = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			
			int height = (int) Math.ceil(Math.log(N) / Math.log(2));
			int size = (int) Math.pow(2, height + 1) - 1;
			tree = new long[size + 1];
			cacheEnd = size / 2;
			for (int n = 1; n <= N; n++) {
				tree[cacheEnd + n] = Long.parseLong(br.readLine());
			}
			init(size);
			
			for (int i = 0; i < M + K; i++) {
				st = new StringTokenizer(br.readLine());
				int a = Integer.parseInt(st.nextToken());
				int b = Integer.parseInt(st.nextToken());
				long c = Long.parseLong(st.nextToken());
				
				if (a == 1) {
					update(cacheEnd + b, c);
				} else if (a == 2) {
					sb.append(sum(cacheEnd + b, cacheEnd + (int) c)).append("\n");
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
		} catch (Exception e) {
			e.getStackTrace();
		}
	}
}
