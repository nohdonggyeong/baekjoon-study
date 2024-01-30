package 알고리즘.다익스트라.boj_1486_등산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

/**
 * 
 * 다익스트라
 * 메모리 46200KB
 * 시간 604ms
 * 코드길이 4930B
 * 
 * 플로이드워셜
 * 메모리 34428KB
 * 시간 1964ms
 * 코드길이 3919B
 * 
 */
class Next implements Comparable<Next> {
    int endN;
    int endM;
    int weight;

    Next(int endN, int endM, int weight) {
        this.endN = endN;
        this.endM = endM;
        this.weight = weight;
    }

    @Override
    public int compareTo(Next o) {
        return Integer.compare(this.weight, o.weight);
    }
}

public class Main {
    static int N, M, T, D;
    static int[][] map;

    static List<Next>[][] adjList;
    static int[] dn = {-1, 0, 1, 0};
    static int[] dm = {0, 1, 0, -1};

    static int[][] dist;
    static final int INF = 1000000;
    static boolean[][] visited;

    static int maxHeight;

    static void dijkstra(int startN, int startM) {
        for (int n = 1; n < N + 1; n++) {
            for (int m = 1; m < M + 1; m++) {
                visited[n][m] = false;
            }
        }

        for (int n = 1; n < N + 1; n++) {
            for (int m = 1; m < M + 1; m++) {
                dist[n][m] = INF;
            }
        }
        dist[startN][startM] = 0;

        PriorityQueue<Next> pq = new PriorityQueue<>();
        pq.offer(new Next(startN, startM, 0));

        while (!pq.isEmpty()) {
            Next curNode = pq.poll();
            int curN = curNode.endN;
            int curM = curNode.endM;

            if (visited[curN][curM]) {
                continue;
            }

            visited[curN][curM] = true;

            for (Next next : adjList[curN][curM]) {
                if (dist[next.endN][next.endM] > dist[curN][curM] + next.weight) {
                    dist[next.endN][next.endM] = dist[curN][curM] + next.weight;
                    pq.offer(new Next(next.endN, next.endM, dist[next.endN][next.endM]));
                }
            }
        }
    }

    public static void main(String[] args) {
        try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            T = Integer.parseInt(st.nextToken());
            D = Integer.parseInt(st.nextToken());

            map = new int[N + 1][M + 1];
            String str;
            for (int n = 1; n < N + 1; n++) {
                str = br.readLine();
                for (int m = 1; m < M + 1; m++) {
                    if ('a' <= str.charAt(m - 1)) {
                        map[n][m] = str.charAt(m - 1) - 'a' + 26;
                    } else {
                        map[n][m] = str.charAt(m - 1) - 'A';
                    }
                }
            }

            adjList = new ArrayList[N + 1][M + 1];
            for (int n = 1; n < N + 1; n++) {
                for (int m = 1; m < M + 1; m++) {
                    adjList[n][m] = new ArrayList<>();
                }
            }

            for (int n = 1; n < N + 1; n++) {
                for (int m = 1; m < M + 1; m++) {
                    for (int d = 0; d < 4; d++) {
                        int nn = n + dn[d];
                        int nm = m + dm[d];

                        if (nn < 1 || nm < 1 || nn > N || nm > M) {
                            continue;
                        }
                        if (Math.abs(map[n][m] - map[nn][nm]) > T) {
                            continue;
                        }

                        int weight = 0;
                        if (map[n][m] < map[nn][nm]) {
                            weight = (int) Math.pow(map[nn][nm] - map[n][m], 2);
                        } else {
                            weight = 1;
                        }

                        adjList[n][m].add(new Next(nn, nm, weight));
                    }
                }
            }

            dist = new int[N + 1][M + 1];
            visited = new boolean[N + 1][M + 1];
            dijkstra(1, 1);

            int[][] distBak = new int[N + 1][M + 1];
            for (int n = 1; n < N + 1; n++) {
                for (int m = 1; m < M + 1; m++) {
                    distBak[n][m] = dist[n][m];
                }
            }

            maxHeight = 0;
            for (int n = 1; n < N + 1; n++) {
                for (int m = 1; m < M + 1; m++) {
                    dijkstra(n, m);
                    if (distBak[n][m] + dist[1][1] <= D) {
                        maxHeight = Math.max(maxHeight, map[n][m]);
                    }
                }
            }

            bw.write(String.valueOf(maxHeight));
            bw.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
