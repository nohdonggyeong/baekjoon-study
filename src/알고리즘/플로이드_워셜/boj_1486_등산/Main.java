package 알고리즘.플로이드_워셜.boj_1486_등산;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
	static int N; // 세로 크기
	static int M; // 가로 크기
	static int T; // 최대 높의 차이
	static int D; // 어두워지는 시간
	
	static int[][] map; // 입력되는 좌표의 산 높이 
	static int[] flattened; // 산의 높이를 1차원 배열로 flattening
	static List<Integer> indexes; // flattened 배열의 번호 리스트
	
	static int[][] dist; // 출발지-목적지 최단 거리 배열
    static final int INF = 1_000_000;
    
    static int[] di = {0, 1, 0, -1};
    static int[] dj = {1, 0, -1, 0};

    public static void main(String[] args) {
    	try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
    		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    		
    		StringTokenizer st = new StringTokenizer(br.readLine());
    		N = Integer.parseInt(st.nextToken());
    		M = Integer.parseInt(st.nextToken());
    		T = Integer.parseInt(st.nextToken());
    		D = Integer.parseInt(st.nextToken());
    		
            map = new int[N + 1][M + 1];
            flattened = new int[N * M + 1];
    		indexes = new ArrayList<>();
            
            String str;
            for (int n = 1; n <= N; n++) {
            	str = br.readLine();
                for (int m = 1; m <= M; m++) {
                    if ('a' <= str.charAt(m - 1)) {
                    	map[n][m] = str.charAt(m - 1) - 'a' + 26;
                    } else {
                    	map[n][m] = str.charAt(m - 1) - 'A';
                    }
                    flattened[(n - 1) * M + m] = map[n][m];
                    indexes.add((n - 1) * M + m);
                }
            }

            dist = new int[N * M + 1][N * M + 1];
            for (int n = 1; n <= N * M; n++) {
        		for (int m = 1; m <= N * M; m++) {
        			if (n != m) {
        				dist[n][m] = INF;
        			}
                }
            }

            for (int n = 1; n <= N; n++) {
            	for (int m = 1; m <= M; m++) {
                	for (int d = 0; d < 4; d++) {
                        int nn = n + di[d];
                        int nm = m + dj[d];
                        
                        if (nn < 1 || nm < 1 || nn > N || nm > M) {
                        	continue;
                        }
                        if (Math.abs(map[n][m] - map[nn][nm]) > T) {
                        	continue;
                        }	
                        
                        int start = (n - 1) * M + m;
                        int end = (nn - 1) * M + nm;
                        
                        if (map[n][m] >= map[nn][nm]) {
                        	dist[start][end] = 1;
                        } else {
                        	dist[start][end] = (int) Math.pow(map[n][m] - map[nn][nm], 2);
                        }
                    }
                }
            }
            
            for (int k : indexes) {
            	for (int i : indexes) {
            		if (k == i) {
            			continue;
            		}
                	for (int j : indexes) {
                		if (k == j || i == j) {
                			continue;
                		}
                		
                    	if (dist[i][k] + dist[k][j] < dist[i][j]) {
                        	dist[i][j] = dist[i][k] + dist[k][j];
                        }
                    }	
                }	
            }
            
            int answer = 0;
            for (int k : indexes) {
            	if (dist[1][k] + dist[k][1] <= D) {
                	answer = Math.max(answer, flattened[k]);
                }
            }
                
            bw.write(String.valueOf(answer));
            bw.flush();
    	} catch (Exception e) {
    		e.printStackTrace();
		}
    }
}