package PRO검정.배달;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class MainBak {
 
    private static int n, m, d;
    private static long result1, result2;
    private static int [] arr;
    private static Store [] tree;
    private static ArrayList<House> houses;
     
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        n = Integer.parseInt(st.nextToken()); // 음식점의 수 1 ~ 100_000
        m = Integer.parseInt(st.nextToken()); // 집의 수 1 ~ 100_000
        d = Integer.parseInt(st.nextToken()); // 배달 가능한 거리
        
        arr = new int[n]; // x 좌표 위의 n개의 음식점 위치
        st = new StringTokenizer(br.readLine().trim());
        for(int i=0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        
        Arrays.sort(arr); // 정렬 후 Tree 생성
        tree = new Store[n*4];
        init(1, n, 1);
         
        houses = new ArrayList<House>(); // x, y 좌표의 m개의 집 위치
        for(int i=0; i < m; i++) {
            st = new StringTokenizer(br.readLine().trim());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            
            int scope = Math.abs(y) - d; // x, y에서 x축으로 수직거리 계산. scope > 0이면 x축에 닿지 않는다.
            if(scope <= 0) { // Y 에서 배달거리가 X축에 닿는 경우만 배달 가능
                houses.add(new House(x - Math.abs(scope), x + Math.abs(scope)));
            }
        }
         
        for(House house : houses) {
            Store store = query(1, n, 1, house.left, house.right);
            if(store.cnt > 0) result1++;
            result2 += store.cnt;
        }
         
        sb.append(result1 + " " + result2 + "\n");
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
 
    /**
     * Min-Max Tree
     */
    public static Store init(int start, int end, int node) {
        if(start == end) {
            return tree[node] = new Store(arr[start-1], arr[start-1], arr[start-1], 1);
        }
         
        int mid = (start + end) / 2;
         
        Store leftNode = init(start, mid, node*2);
        Store rightNode = init(mid+1, end, node*2+1);
         
        return tree[node] = new Store(Math.min(leftNode.min, rightNode.min),
                Math.max(leftNode.max, rightNode.max), 0, leftNode.cnt + rightNode.cnt);   
    }
     
    /**
     * 구간 최대 최소 조회
     */
    public static Store query(int start, int end, int node, int left, int right) {
        if(right < tree[node].min || tree[node].max < left) {
            return new Store(Long.MAX_VALUE, Long.MIN_VALUE, 0, 0);
        }
         
        if(left <= tree[node].min && tree[node].max <= right) {
            return tree[node];
        }
         
        int mid = (start + end) / 2;
         
        Store leftNode = query(start, mid, node*2, left, right);
        Store rightNode = query(mid+1, end, node*2+1, left, right);
         
        return new Store(Math.min(leftNode.min, rightNode.min),
                Math.max(leftNode.max, rightNode.max), 0, leftNode.cnt + rightNode.cnt);   
    }
    
    static class Store {
        public long min, max, dist, cnt;
     
        public Store(long min, long max, long dist, long cnt) {
            this.min = min;
            this.max = max;
            this.dist = dist;
            this.cnt = cnt;
        }
    }

    static class House {
        public int left, right;
     
        public House(int left, int right) {
            this.left = left;
            this.right = right;
        }
    }
}