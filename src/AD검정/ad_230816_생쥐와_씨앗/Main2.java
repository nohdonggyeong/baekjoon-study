package AD검정.ad_230816_생쥐와_씨앗;

import java.util.*;
import java.io.*;
 
public class Main2 {
     
    static List<int[]> seedList;
    static int[][] dis;
    static int[] cat;
    static int result;
     
    static int[] xArr = {0, 0, 1, -1};
    static int[] yArr = {-1, 1, 0, 0};
     
     
    public static void main(String[] args) throws IOException {
         
         
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;
         
         
        int forCnt = Integer.parseInt(br.readLine());
         
        for(int t=1; t<=forCnt; t++) {
            int N = Integer.parseInt(br.readLine());
             
            seedList = new ArrayList<>();
            dis = new int[15][15];
            result = 1;
             
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine(), " ");
                int y = Integer.parseInt(st.nextToken());
                int x = Integer.parseInt(st.nextToken());
                seedList.add(new int[] {x, y});
            }
             
            // 도착지점 추가
            seedList.add(new int[] {1, 0});
                     
            st = new StringTokenizer(br.readLine(), " ");
            int y = Integer.parseInt(st.nextToken());
            int x = Integer.parseInt(st.nextToken());
             
            // 고양이 위치
            cat = new int[] {x, y};
             
            for(int i=0; i<seedList.size()-1; i++) {
                // System.out.println(Arrays.toString(seedList.get(i)) + " , " + Arrays.toString(seedList.get(i+1)));
                bfs(seedList.get(i), seedList.get(i+1));
                // System.out.println("확인 cat: " + Arrays.toString(cat));
                 
                if(result == 0) {
                    break;
                }
            }
             
            // System.out.println(Arrays.deepToString(dis));
             
            bw.write("#" + t + " " + result + "\n");
        }
         
        bw.flush();
        bw.close();
         
         
    }
     
    public static void bfs(int[] point, int[] endPoint) {
        boolean[][] check = new boolean[15][15];
        Queue<int[][]> queue = new LinkedList<>();
         
        int[][] pointArr = new int[2][2];
        pointArr[0] = point;
        pointArr[1] = cat;
         
        queue.offer(pointArr);
        check[point[0]][point[1]] = true;
         
        while(!queue.isEmpty()) {
             
            int[][] tmp = queue.poll();
             
            int x = tmp[0][0];
            int y = tmp[0][1];
             
            int catx = tmp[1][0];
            int caty = tmp[1][1];
             
            boolean move = false; // 쥐 이동 여부
             
            for(int i=0; i<4; i++) {
                int nx = x + xArr[i];
                int ny = y + yArr[i];
                 
                int ncatx = catx;
                int ncaty = caty;
                 
                if(move) {
                    break;
                }
                 
                // 범위 확인
                if(nx <= 0 || nx >= 14 || ny <= 0 || ny >= 14) {
                    continue;
                }
                 
                // 방문 여부 확인
                if(check[nx][ny]) {
                    continue;
                }
                 
                int absx = Math.abs(endPoint[0] - x);
                int absy = Math.abs(endPoint[1] - y);
                int absnx = Math.abs(endPoint[0] - nx);
                int absny = Math.abs(endPoint[1] - ny);
                 
                // 도착 지점으로부터 멀어지는 경우
                if((absx + absy) < (absnx + absny)) {
                    continue;
                }
                 
                // 거리 추가(확인용)
                dis[nx][ny] = dis[x][y] + 1 ;
                 
                // 고양이 이동
                absx = Math.abs(nx - catx);
                absy = Math.abs(ny - caty);
                 
                if(absx >= 2) {
                    if(catx - nx > 0) { // 위 이동
                        ncatx -= 2;
                    }else { // 아래 이동
                        ncatx += 2;
                    }
                }else if(absy >= 2){
                    if(caty - ny > 0) { // 좌측 이동
                        ncaty -= 2;
                    }else { // 우측 이동
                        ncaty += 2;
                    }
                }else if(absx >= 1) {
                    if(catx - nx > 0) { // 위 이동
                        ncatx -= 1;
                    }else { // 아래 이동
                        ncatx += 1;
                    }
                }else if(absx >= 1) {
                    if(caty - ny > 0) { // 좌측 이동
                        ncaty -= 1;
                    }else { // 우측 이동
                        ncaty += 1;
                    }
                }
                 
                int[] nmouse = {nx, ny};
                int[] ncat = {ncatx, ncaty};
                 
                // 고양이 이동 후 위치 확인
                if(Arrays.equals(nmouse, ncat)) {
                    // System.out.println("쥐 : " + Arrays.toString(nmouse));
                    // System.out.println("고양이 : " + Arrays.toString(ncat));
                    result = 0;
                    return;
                }
                 
                move = true;
                 
                // 씨앗 도착
                if(endPoint[0] == nx && endPoint[1] == ny) {
                    cat = ncat;
                    return;
                }
                 
                check[nx][ny] = true;
                queue.offer(new int[][] {nmouse, ncat});
            }
             
        }
         
    }  
 
}