package PRO검정.N정류장;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
 
/**
 *
 */
public class Solution {
     
    static int INF = (int)1e9;
    static int N, M;
         
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src\\PRO검정\\N정류장\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
         
        int[][] graph = new int[N + 1][N + 1];
         
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
            	if (i != j) {
            		graph[i][j] = INF;	
            	}
            }
        }
         
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
             
            graph[start][end] = Math.min(graph[start][end], cost);
            graph[end][start] = Math.min(graph[end][start], cost);
        }
     
        UserSolution.find(graph, N, 1, 7, 1, new int[] {2,0,0,0,0});
         
        graph[7][4] = 4;
        graph[4][7] = 4;
         
        UserSolution.find(graph, N, 1,7,1, new int[] {2,0,0,0,0});
         
        UserSolution.find(graph, N, 2,1,2, new int[] {5,7,0,0,0});
         
        UserSolution.find(graph, N, 1,5,3, new int[] {6,4,2,0,0});
         
        UserSolution.find(graph, N, 1,4,3, new int[] {2,5,6,0,0});
         
        UserSolution.find(graph, N, 4,1,3, new int[] {2,5,6,0,0});
         
        UserSolution.find(graph, N, 1,4,3, new int[] {2,3,7,0,0});
         
        UserSolution.find(graph, N, 5,2,1, new int[] {4,0,0,0,0});
         
        graph[3][7] = 8;
        graph[7][3] = 8;
         
        UserSolution.find(graph, N, 1,4,3, new int[] {2,3,7,0,0});
         
        br.close();
    }
}