package backjoon.알고리즘_분류.세그먼트트리.boj_2042_구간_합_구하기;

import java.io.*;
import java.util.StringTokenizer;

public class Main3 {
    static long[] nums, tree;

    static long init(int start, int end, int node) {
        if (start == end) {
        	return tree[node] = nums[start];
        }
        int mid = (start + end) / 2;
        return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
    }

    static void update(int start, int end, int node, int index, long diff) {
        if (index < start || index > end) {
        	return;
        }
        
        tree[node] += diff;
        if (start == end) {
        	return;
        }
        
        int mid = (start + end) / 2;
        update(start, mid, node * 2, index, diff);
        update(mid + 1, end, node * 2 + 1, index, diff);
    }

    static long query(int start, int end, int node, int left, int right) {
        if (left > end || right < start) {
        	return 0;
        }
        if (left <= start && end <= right) {
        	return tree[node];
        }
        
        int mid = (start + end) / 2;
        return query(start, mid, node * 2, left, right) + query(mid + 1, end, node * 2 + 1, left, right);
    }
    
    public static void main(String[] args) throws IOException {
    	try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
    		StringBuilder sb = new StringBuilder();
    		
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            nums = new long[N + 1];
            tree = new long[N * 4 + 1];

            for (int i = 1; i <= N; i++)
                nums[i] = Long.parseLong(br.readLine());

            init(1, N, 1);

            for (int i = 0; i < M + K; i++) {
                st = new StringTokenizer(br.readLine());
                int cmd = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                long c = Long.parseLong(st.nextToken());

                if (cmd == 1) {
                    long diff = c - nums[b];
                    nums[b] = c;
                    update(1, N, 1, b, diff);
                } else if (cmd == 2) {
                	sb.append(query(1, N, 1, b, (int) c)).append("\n");
                }
            }

            bw.write(sb.toString().trim());
            bw.flush();
            
    	} catch (IOException e) {
    		e.printStackTrace();
		}
    }
}

