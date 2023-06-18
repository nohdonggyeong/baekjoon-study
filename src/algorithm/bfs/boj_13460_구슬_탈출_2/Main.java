package algorithm.bfs.boj_13460_구슬_탈출_2;

import java.io.*;
import java.util.*;

class Ball {
    int x;
    int y;
    int cnt;

    public Ball(int x,int y, int cnt) {
        this.x = x;
        this.y = y;
        this.cnt = cnt;
    }
}

public class Main{

    static int N,M;
    static char[][] map;
    static boolean[][][][] visited;
    static Ball redBall, blueBall;
    static int result = -1;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    static void bfs(Ball redBall, Ball blueBall) {
        Queue<Ball> redQueue = new LinkedList<>();
        Queue<Ball> blueQueue = new LinkedList<>();

        redQueue.offer(redBall);
        blueQueue.offer(blueBall);

        visited[redBall.x][redBall.y][blueBall.x][blueBall.y]= true;

        while (!redQueue.isEmpty() && !blueQueue.isEmpty()) {
            Ball newRedBall = redQueue.poll();
            Ball newBlueBall = blueQueue.poll();

            if(newRedBall.cnt > 10) {
                result = -1;
                return;
            }

            if(map[newBlueBall.x][newBlueBall.y] == 'O') {
                continue;
            }

            if(map[newRedBall.x][newRedBall.y] == 'O') {
                result = newRedBall.cnt;
                return;
            }

            for(int i = 0; i < 4; i++) {

                int blueX = newBlueBall.x;
                int blueY = newBlueBall.y;
                
                while(true) {
                    blueX += dx[i];
                    blueY += dy[i];
                    
                    if(map[blueX][blueY] == 'O') {
                    	break;	
                    } else if(map[blueX][blueY] == '#'){
                        blueX -= dx[i];
                        blueY -= dy[i];
                        break;
                    }
                }

                int redX = newRedBall.x;
                int redY = newRedBall.y;
                
                while(true) {
                    redX += dx[i];
                    redY += dy[i];
                    if(map[redX][redY] == 'O') {
                    	break;
                    } else if(map[redX][redY] == '#'){
                        redX -= dx[i];
                        redY -= dy[i];
                        break;
                    }
                }

                if(blueX == redX && blueY == redY && map[redX][redY] != 'O') {
                	
                    int r_dis = Math.abs(newRedBall.x - redX) + Math.abs(newRedBall.y - redY);
                    int b_dis = Math.abs(newBlueBall.x - blueX) + Math.abs(newBlueBall.y - blueY);

                    if(r_dis > b_dis) {
                        redX -= dx[i];
                        redY -= dy[i];
                    } else {
                        blueX -= dx[i];
                        blueY -= dy[i];
                    }
                }

                if(!visited[redX][redY][blueX][blueY]) {
                    visited[redX][redY][blueX][blueY] = true;
                    
                    redQueue.offer(new Ball(redX, redY, newRedBall.cnt + 1));
                    blueQueue.offer(new Ball(blueX, blueY, newBlueBall.cnt + 1));
                }
            }

        }
    }

    public static void main(String[] args)throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map= new char[N][M];
        visited = new boolean[N][M][N][M];

        for(int i = 0; i < N; i++) {
            String row = br.readLine();
            for(int j = 0; j < M; j++) {
                map[i][j] = row.charAt(j);
                if(map[i][j] == 'R') {
                    redBall = new Ball(i, j,0);
                }
                else if(map[i][j] == 'B') {
                    blueBall = new Ball(i, j,0);
                }
            }
        }
        
        bfs(redBall, blueBall);
        
        bw.write(String.valueOf(result));
        bw.flush();
        
        bw.close();
        br.close();
    }
}
