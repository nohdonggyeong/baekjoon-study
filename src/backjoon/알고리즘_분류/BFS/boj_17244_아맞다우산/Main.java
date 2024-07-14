package backjoon.알고리즘_분류.BFS.boj_17244_아맞다우산;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    
    static char[][] map;
    static boolean[][][] visited;
    
    static int itemNum;
    static int[][] itemNums;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};
    
    static int result = -1;

    static class Node {
    	int r;
    	int c;
    	
    	Node(int r, int c) {
    		this.r = r;
    		this.c = c;
    	}
    }
    
    static class Cursor {
    	Node node;
    	int bit;
    	int time;
    	
    	Cursor(Node node, int bit, int time) {
    		this.node = node;
    		this.bit = bit;
    		this.time = time;
    	}
    }

    public static void main(String[] args) throws IOException {
    	try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));) {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            map = new char[M][N];
            itemNum = 0;
            itemNums = new int[M][N];
            Node start = null;
            for (int m = 0; m < M; m++) {
                String row = br.readLine();
                for (int n = 0; n < N; n++) {
                    map[m][n] = row.charAt(n);
                    if (map[m][n] == 'X') {
                        itemNums[m][n] = itemNum++;
                    } else if (map[m][n] == 'S') {
                        start = new Node(m, n);
                    }
                }
            }

            int fullBit = (1 << itemNum) - 1;
            visited = new boolean[1 << itemNum][M][N];
            Queue<Cursor> queue = new ArrayDeque<>();
            queue.offer(new Cursor(new Node(start.r, start.c), 0, 0));
            visited[0][start.r][start.c] = true;

            while (!queue.isEmpty()) {
                Cursor cursor = queue.poll();
                int r = cursor.node.r;
                int c = cursor.node.c;
                int bit = cursor.bit;
                int time = cursor.time;
                
                if (bit == fullBit && map[r][c] == 'E') {
                    result= time;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    int nr = r + dr[d];
                    int nc = c + dc[d];

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N
                    		|| map[nr][nc] == '#' || visited[bit][nr][nc]) {
                        continue;
                    }

                    int nBit = bit;
                    if (map[nr][nc] == 'X') {
                        nBit |= (1 << itemNums[nr][nc]);
                    }

                    queue.offer(new Cursor(new Node(nr, nc), nBit, time + 1));
                    visited[nBit][nr][nc] = true;
                }
            }
            bw.write(String.valueOf(result));
            bw.flush();
    	}
    }
}