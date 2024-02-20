package PRO검정.도서관;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main2 {
    public static int N, Q; // 보관중인 책 수 N과 (반출/반납)쿼리 갯수 Q
    public static ArrayList<Integer> arr;
    public static Book [] tree;
    public static int [][] query;
    
    public static void main(String [] args) throws Exception {
    	long start = System.currentTimeMillis();
    	
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
        
        int test_case = Integer.parseInt(br.readLine());
        for(int t = 1; t <= test_case; t++) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            Q = Integer.parseInt(st.nextToken());
 
            arr = new ArrayList<Integer>();
            for(int i = 1; i <= N; i++) {
            	arr.add(i);
            }
 
            // 쿼리를 모두 입력 받고
            query = new int[Q][2];
            for(int i = 0; i < Q; i++) {
                st = new StringTokenizer(br.readLine());
                query[i][0] = Integer.parseInt(st.nextToken());
                query[i][1] = Integer.parseInt(st.nextToken());
                if (query[i][0] == 2) {
                	// 반납 쿼리를 모두 배열에 담아
                	arr.add(query[i][1]);
                }
            }
 
            tree = new Book[arr.size() * 4];
             
            init(1, 0, arr.size() - 1);
 
            long answer = 0;
            for(int i = 0; i < Q; i++) {
                if (query[i][0] == 1) {
                    answer += (long) getBook(1, 0, arr.size() - 1, query[i][1]);
                }
            }
            bw.write("#" + t + " " + answer);
            bw.newLine();
        }
        
        bw.flush();
        
        long end = System.currentTimeMillis();
        System.out.println((end - start) / 1000.0 + "초");
        
        bw.close();
        br.close();
    }
 
    // 기존 N개 + 반납 쿼리 갯수 만큼 배열을 세그먼트 트리로 구현하였습니다.
	// 리프노드는 실제 책의 번호와 갯수를 설정하고, 리프노드 외 노드에는 책 갯수를 설정하였습니다.
    public static Book init(int node, int start, int end) {
        if (start == end) {
            return tree[node] = new Book(arr.get(start));
        }
        int mid = (start + end) / 2;
        Book leftNode = init(node * 2, start, mid);
        Book rightNode = init(node * 2 + 1, mid + 1, end);
        tree[node] = new Book(0);
        tree[node].count = leftNode.count + rightNode.count;
        return tree[node];
    }
 
    public static int getBook(int node, int start, int end, int pos) {
        tree[node].count--;
        if (start != end) {
            int mid = (start + end) / 2;
            if (tree[node * 2].count >= pos) {
                return getBook(node * 2, start, mid, pos);
            } else {
                return getBook(node * 2 + 1, mid + 1, end, pos - tree[node * 2].count);
            }
        } else {
            return tree[node].val;
        }
    }
}
 
class Book {
    int count, val;
    public Book(int val) {
        this.val = val;
        count = 1;
    }
}