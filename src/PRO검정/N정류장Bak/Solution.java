package PRO검정.N정류장Bak;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
 
/**
 *
 */
public class Solution {
     
    static int INF = (int)1e9;
    static int N, M;
         
    public static void main(String[] args) throws Exception {
        System.setIn(new FileInputStream("src\\\\PRO검정\\\\N정류장\\\\sample_input.txt"));
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
         
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
         
        int[][] graph = new int[N+1][N+1];
         
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                graph[i][j] = INF;
            }
        }
         
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
             
            graph[start][end] = cost;
            graph[end][start] = cost;
        }
     
        find(graph, N, 1,7,1, new int[] {2,0,0,0,0});
         
        graph[7][4] = 4;
        graph[4][7] = 4;
         
        find(graph, N, 1,7,1, new int[] {2,0,0,0,0});
         
        find(graph, N, 2,1,2, new int[] {5,7,0,0,0});
         
        find(graph, N, 1,5,3, new int[] {6,4,2,0,0});
         
        find(graph, N, 1,4,3, new int[] {2,5,6,0,0});
         
        find(graph, N, 4,1,3, new int[] {2,5,6,0,0});
         
        find(graph, N, 1,4,3, new int[] {2,3,7,0,0});
         
        find(graph, N, 5,2,1, new int[] {4,0,0,0,0});
         
        graph[3][7] = 8;
        graph[7][3] = 8;
         
        find(graph, N, 1,4,3, new int[] {2,3,7,0,0});
         
        br.close();
    }
     
    private static int[][] floydWarshall(int[][] graph, int vertices, int start, int end) {
        //결과를 저장할 2차원 배열 초기화
        int[][] dist = new int[vertices+1][vertices+1];
        for (int i = 1; i <= vertices; i++) {
            for (int j = 1; j <= vertices; j++) {
                dist[i][j] = graph[i][j];
            }
        }
         
        //k를 경유하여 i에서 j로 가는 경로를 확인
        for (int k = 1; k <= vertices; k++) {
            if(k == start || k == end) {
                continue;
            }
            for (int i = 1; i <= vertices; i++) {
                for (int j = 1; j <= vertices; j++) {
                    //무한대가 아니라면, i에서 k를 거쳐 j로 가는 경로와 직접 가는 경로 중 최단 경로 선택
                    if(i != j && dist[i][k] != INF && dist[k][j] != INF) {
                        dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                    }
                }
            }
        }
         
        //최단 경로 출력
      for (int i = 1; i <= vertices; i++) {
          for (int j = 1; j <= vertices; j++) {
              System.out.print((dist[i][j] == INF ? "INF" : dist[i][j]) + "\t");
          }
          System.out.println();
      }
         
        return dist;
    }
     
    private static void find(int[][] graph, int vertices, int start, int end, int m, int[] minCosts) {
        //결과를 저장할 2차원 배열 초기화
        int[][] dist = floydWarshall(graph, N, start, end);
        int mincost = 0;
        int result = INF;      
        List<List<Integer>> allPermutation = new ArrayList<>();
        findCombinations(m, minCosts, 0, new ArrayList<>(), allPermutation); //모든 가능한 순열 생성
        for(List<Integer> combination : allPermutation) {
//          System.out.println(combination);
            int lastNum = 0;
            mincost = dist[start][combination.get(0)];
            lastNum = combination.get(0);
            for (int i = 1; i < m; i++) {
                mincost += dist[combination.get(i-1)][combination.get(i)];
                lastNum = combination.get(i);
            }
            mincost += dist[lastNum][end];
            result = Math.min(result, mincost);
        }
 
        System.out.println(result == INF? - 1 : result);
    }
     
    private static void findCombinations(int m, int[] minCosts, int start, List<Integer> currentCombination, List<List<Integer>> result) {
        if(start == m) {
            result.add(new ArrayList<>(currentCombination));
            return;
        }
         
        for (int i = 0; i < currentCombination.size()+1; i++) {
            if(minCosts[start] == 0) {
                continue;
            }
            currentCombination.add(i, minCosts[start]);
            findCombinations(m,minCosts, start+1, currentCombination, result);
            currentCombination.remove(i);
        }
    }
}