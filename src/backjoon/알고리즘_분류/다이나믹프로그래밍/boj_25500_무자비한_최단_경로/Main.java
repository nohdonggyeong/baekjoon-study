package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_25500_무자비한_최단_경로;

import java.io.*;
import java.util.*;

public class Main {
    static class Village {
        long x, y, z;
        int id;

        Village(long x, long y, long z, int id) {
            this.x = x;
            this.y = y;
            this.z = z;
            this.id = id;
        }
    }

    static class Road {
        int to;
        long cost;

        Road(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }
    }

    static class Node implements Comparable<Node> {
        int id;
        long cost;

        Node(int id, long cost) {
            this.id = id;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    // 두 마을 간 일반 도로 길이 계산
    static long calcRoadLength(Village a, Village b) {
        return Math.min(Math.abs(a.x - b.x), Math.abs(a.y - b.y));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 입력 처리
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long k = Long.parseLong(st.nextToken());

        List<Village> villages = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());
            villages.add(new Village(x, y, z, i));
        }

        // 그래프 초기화 (마을 n개 + residue 노드 k개)
        int totalNodes = n + (int) k + 5;
        List<Road>[] graph = new ArrayList[totalNodes];
        for (int i = 0; i < totalNodes; i++) {
            graph[i] = new ArrayList<>();
        }

        // 일반 도로: x좌표 기준 정렬 후 인접 마을 연결
        villages.sort(Comparator.comparingLong(v -> v.x));
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                Village a = villages.get(i);
                Village b = villages.get(i - 1);
                long cost = calcRoadLength(a, b);
                graph[a.id].add(new Road(b.id, cost));
                graph[b.id].add(new Road(a.id, cost));
            }
            if (i < n - 1) {
                Village a = villages.get(i);
                Village b = villages.get(i + 1);
                long cost = calcRoadLength(a, b);
                graph[a.id].add(new Road(b.id, cost));
                graph[b.id].add(new Road(a.id, cost));
            }
        }

        // 일반 도로: y좌표 기준 정렬 후 인접 마을 연결
        villages.sort(Comparator.comparingLong(v -> v.y));
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                Village a = villages.get(i);
                Village b = villages.get(i - 1);
                long cost = calcRoadLength(a, b);
                graph[a.id].add(new Road(b.id, cost));
                graph[b.id].add(new Road(a.id, cost));
            }
            if (i < n - 1) {
                Village a = villages.get(i);
                Village b = villages.get(i + 1);
                long cost = calcRoadLength(a, b);
                graph[a.id].add(new Road(b.id, cost));
                graph[b.id].add(new Road(a.id, cost));
            }
        }

        // 특수 도로: residue 노드 활용
        for (Village v : villages) {
            long residue = v.z % k;
            // 마을 -> residue 노드 (complement) 연결
            long complement = (k - residue) % k;
            graph[v.id].add(new Road(n + (int) complement, v.z));
            // residue 노드 -> 마을 연결
            graph[n + (int) residue].add(new Road(v.id, v.z));
        }

        // 다익스트라 알고리즘
        long[] dist = new long[totalNodes];
        Arrays.fill(dist, Long.MAX_VALUE);
        PriorityQueue<Node> pq = new PriorityQueue<>();
        dist[0] = 0;
        pq.offer(new Node(0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (current.cost > dist[current.id]) continue;
            for (Road road : graph[current.id]) {
                long newCost = current.cost + road.cost;
                if (newCost < dist[road.to]) {
                    dist[road.to] = newCost;
                    pq.offer(new Node(road.to, newCost));
                }
            }
        }

        // 결과 출력 (0 ~ n-1 마을까지의 거리)
        for (int i = 0; i < n; i++) {
            bw.write(dist[i] + "\n");
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
