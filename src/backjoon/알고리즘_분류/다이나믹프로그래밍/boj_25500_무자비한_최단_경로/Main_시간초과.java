package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_25500_무자비한_최단_경로;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main_시간초과 {
    // 마을 정보 저장 클래스
    static class Town {
        int x, y, z;  // 마을의 x, y 좌표와 z 값
        
        Town(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
    
    // 그래프 간선 정보 저장 클래스 (Comparable 구현)
    static class Edge implements Comparable<Edge> {
        int to;       // 대상 마을 인덱스
        long cost;    // 이동 비용
        
        Edge(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
        
        // 비용 기준 오름차순 정렬
        @Override
        public int compareTo(Edge other) {
            return Long.compare(this.cost, other.cost);
        }
    }
    
    public static void main(String[] args) throws IOException {
        // 자원 자동 관리 (BufferedWriter)
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            // 입력 스트림 설정
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            
            // 첫째 줄 입력: 마을 수(N), 특별 도로 판단 기준(K)
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());
            
            // 마을 정보 저장 배열
            Town[] towns = new Town[N];
            
            // 마을 정보 입력 (x, y, z)
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                towns[i] = new Town(
                    Integer.parseInt(st.nextToken()),  // x 좌표
                    Integer.parseInt(st.nextToken()),  // y 좌표
                    Integer.parseInt(st.nextToken())   // z 값
                );
            }

            // 인접 리스트 초기화
            List<Edge>[] graph = new ArrayList[N];
            for (int i = 0; i < N; i++) {
                graph[i] = new ArrayList<>();
            }
            
            // 모든 마을 쌍에 대해 도로 생성
            for (int i = 0; i < N; i++) {
                for (int j = i + 1; j < N; j++) {
                    // 일반 도로: min(|xi - xj|, |yi - yj|)
                    long normalRoad = Math.min(
                        Math.abs(towns[i].x - towns[j].x),
                        Math.abs(towns[i].y - towns[j].y)
                    );
                    
                    // 양방향 도로 추가
                    graph[i].add(new Edge(j, normalRoad));
                    graph[j].add(new Edge(i, normalRoad));

                    // 특별 도로: (zi + zj)가 K로 나누어떨어지는 경우
                    if ((towns[i].z + towns[j].z) % K == 0) {
                        long specialRoad = towns[i].z + towns[j].z;
                        
                        // 양방향 특별 도로 추가
                        graph[i].add(new Edge(j, specialRoad));
                        graph[j].add(new Edge(i, specialRoad));
                    }
                }
            }
            
            // 다익스트라 알고리즘 수행
            long[] dist = new long[N];          // 최단 거리 배열
            Arrays.fill(dist, Long.MAX_VALUE);  // 무한대로 초기화
            dist[0] = 0;                       // 시작점 거리 = 0
            
            PriorityQueue<Edge> pq = new PriorityQueue<>();
            pq.offer(new Edge(0, 0));           // 시작점 추가
            
            while(!pq.isEmpty()) {
                Edge current = pq.poll();
                int currentTown = current.to;
                long currentCost = current.cost;
                
                // 이미 더 짧은 경로가 존재하는 경우 스킵
                if (currentCost > dist[currentTown]) continue;
                
                // 인접 마을 탐색
                for (Edge edge : graph[currentTown]) {
                    int nextTown = edge.to;
                    long nextCost = currentCost + edge.cost;
                    
                    // 더 짧은 경로 발견 시 업데이트
                    if (nextCost < dist[nextTown]) {
                        dist[nextTown] = nextCost;
                        pq.offer(new Edge(nextTown, nextCost));
                    }
                }
            }
            
            // 결과 출력 준비
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < N; i++) {
                sb.append(dist[i]).append('\n');
            }
            
            // 결과 출력
            bw.write(sb.toString());
            bw.flush();  // 버퍼 강제 비우기
        }
    }
}