package backjoon.유형별로_풀어보기.DFS.boj_1325_효율적인_해킹;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static boolean[] visit;
    static int n, m, max, count;
    static List<List<Integer>> list;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        list = new ArrayList<>();
        for(int i = 0; i <= n; i++){
            list.add(new ArrayList<>());
        }
        
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            list.get(b).add(a);
        }
        
        int[] result = new int[n + 1];
        
        max = 0;
        for (int i = 1; i <= n; i++) {
            visit = new boolean[n + 1];
            count = 0;
            bfs(i);
            result[i] = count;
            max = Math.max(count, max);

        }
        
        for (int i = 1; i <= n; i++) {
            if (result[i] == max)
                sb.append(i + " ");
        }
        bw.write(sb.toString());
        
        bw.flush();
        bw.close();
        br.close();
    }

    public static void bfs(int node) {
        Queue<Integer> queue = new LinkedList<>();
        
        queue.add(node);
        visit[node] = true;
        
        while (!queue.isEmpty()) {
            int next = queue.poll();
            
            for(int i : list.get(next)){
                if(!visit[i]){
                    queue.add(i);
                    visit[i] = true;
                    count++;
                }
            }

        }
    }

}
