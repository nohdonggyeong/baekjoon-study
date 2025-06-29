package backjoon.알고리즘_분류.다이나믹프로그래밍.boj_25500_무자비한_최단_경로;

import java.io.*;
import java.util.*;

public class Main {

    static class Village {
        int id;
        long x, y, z;

        Village(int id, long x, long y, long z) {
            this.id = id;
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }

    static class Node implements Comparable<Node> {
        int to;
        long cost;

        Node(int to, long cost) {
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node other) {
            return Long.compare(this.cost, other.cost);
        }
    }

    static long calcNodeLength(Village u, Village v) {
        return Math.min(Math.abs(u.x - v.x), Math.abs(u.y - v.y));
    }

    static List<Long> dijkstra(List<List<Node>> graph, int start) {
        List<Long> dist = new ArrayList<>(Collections.nCopies(graph.size(), Long.MAX_VALUE));
        PriorityQueue<Node> pq = new PriorityQueue<>();
        
        dist.set(start, 0L);
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();

            if (current.cost > dist.get(current.to)) continue;

            for (Node next : graph.get(current.to)) {
                long newCost = dist.get(current.to) + next.cost;
                
                if (newCost < dist.get(next.to)) {
                    dist.set(next.to, newCost);
                    pq.offer(new Node(next.to, newCost));
                }
            }
        }

        return dist;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        List<Village> villages = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            long z = Long.parseLong(st.nextToken());

            villages.add(new Village(i, x, y, z));
        }

        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i < N + K; i++) {
            graph.add(new ArrayList<>());
        }

        villages.sort(Comparator.comparingLong(v -> v.x));
        
        for (int i = 0; i < N - 1; i++) {
            Village u = villages.get(i);
            Village v = villages.get(i + 1);
            long cost = calcNodeLength(u, v);
            
            graph.get(u.id).add(new Node(v.id, cost));
            graph.get(v.id).add(new Node(u.id, cost));
        }

        villages.sort(Comparator.comparingLong(v -> v.y));
        
        for (int i = 0; i < N - 1; i++) {
            Village u = villages.get(i);
            Village v = villages.get(i + 1);
            long cost = calcNodeLength(u, v);
            
            graph.get(u.id).add(new Node(v.id, cost));
            graph.get(v.id).add(new Node(u.id, cost));
        }

        for (Village v : villages) {
            int residue = (int) (v.z % K);
            int complement = (K - residue) % K;

            graph.get(v.id).add(new Node(N + complement, v.z));
            graph.get(N + residue).add(new Node(v.id, v.z));
        }

        List<Long> dist = dijkstra(graph, 0);

        for (int i = 0; i < N; i++) {
            bw.write(dist.get(i) + "\n");
        }

        bw.flush();
        bw.close();
        br.close();
    }
}