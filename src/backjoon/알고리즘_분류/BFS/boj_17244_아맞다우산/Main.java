package backjoon.알고리즘_분류.BFS.boj_17244_아맞다우산;

import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    
    static char[][] map;
    
    static int itemNum;
    static int[][] itemNums;

    static int fullBit;
    static boolean[][][] visit;
    
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

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
    
    static int result = -1;

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

            int bitSize = 1 << itemNum;
            visit = new boolean[bitSize][M][N];
            fullBit = bitSize - 1;
            
            Queue<Cursor> queue = new ArrayDeque<>();
            queue.offer(new Cursor(new Node(start.r, start.c), 0, 0));
            visit[0][start.r][start.c] = true;

            int cr, cc, cBit, cTime, nr, nc, nBit, nTime;
            while (!queue.isEmpty()) {
                Cursor cursor = queue.poll();
                cr = cursor.node.r;
                cc = cursor.node.c;
                cBit = cursor.bit;
                cTime = cursor.time;
                
                if (cBit == fullBit && map[cr][cc] == 'E') {
                    result = cTime;
                    break;
                }

                for (int d = 0; d < 4; d++) {
                    nr = cr + dr[d];
                    nc = cc + dc[d];

                    if (nr < 0 || nr >= M || nc < 0 || nc >= N
                    		|| map[nr][nc] == '#' || visit[cBit][nr][nc]) {
                        continue;
                    }

                    nBit = cBit;
                    if (map[nr][nc] == 'X') {
                        nBit |= (1 << itemNums[nr][nc]);
                    }
                    
                    nTime = cTime + 1;

                    queue.offer(new Cursor(new Node(nr, nc), nBit, nTime));
                    visit[nBit][nr][nc] = true;
                }
            }
            
            bw.write(String.valueOf(result));
            bw.flush();
    	}
    }
}