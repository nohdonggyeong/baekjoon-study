package sw검정.advanced_기출문제.ad_230816_생쥐와_씨앗;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;
 
public class Main1 {
    static int[] catPos;
    static int[] mousePos;
    static Queue<int[]> seedList;
    static StringTokenizer st;
 
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            int n = Integer.parseInt(br.readLine());
 
            seedList = new LinkedList<>();
 
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());
                // 씨앗 좌표 추가
                seedList.add(new int[] { x, y });
            }
 
            // 쥐랑 쥐 구멍 좌표 추가
            mousePos = new int[] { 1, 0 };
             
            // ※ 쥐는 마지막 1, 0에 위치한 구멍에 도달해야 고양이로부터 벗어 날 수 있다.
            // ※ 쥐 구멍도 씨앗과 같이 위치를 등록한다
            seedList.add(new int[] { 1, 0 });
 
            // 고양이 좌표 추가
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            catPos = new int[] { x, y };
 
            int ans = solution();
            System.out.println("#" + t + " " + ans);
        }
    }
 
    static int solution() {
        int ans = 0;
        int disX = 0;
        int disY = 0;
 
        // 쥐가 첫번째 씨앗 위치로 이동
        int[] seedPos = seedList.poll();
        mousePos[0] = seedPos[0];
        mousePos[1] = seedPos[1];
 
        while (true) {
            // 쥐가 씨앗 위치에 있을 경우, 씨앗이 남아 있는 경우
            if (mousePos[0] == seedPos[0] && mousePos[1] == seedPos[1] && !seedList.isEmpty()) {
                // 씨앗 꺼내기
                seedPos = seedList.poll();
            }
             
            // System.out.println("#씨앗 좌표: " + Arrays.toString(seedPos));
 
            // 쥐와 씨앗 거리 확인 및 쥐 이동
            disX = seedPos[0] - mousePos[0];
            disY = seedPos[1] - mousePos[1];
 
            if (Math.abs(disX) > Math.abs(disY) || disX == disY) {
                // 좌우 이동(우선)
                mousePos[0] += movePosition(disX, 1, -1);;
            } else {
                // 상하 이동
                mousePos[1] += movePosition(disY, 1, -1);
            }
 
            // System.out.println("#쥐 좌표: " + Arrays.toString(mousePos));
             
            // 쥐가 쥐 구멍에 도착한 경우, 탈출
            if (mousePos[0] == 1 && mousePos[1] == 0) {
                ans = 1;
                break;
            }
 
 
            // 쥐와 고양이 거리 확인 및 고양이 이동
            disX = mousePos[0] - catPos[0];
            disY = mousePos[1] - catPos[1];
            if (Math.abs(disX) < Math.abs(disY) || disX == disY) {
                // 상하 이동(우선)
                catPos[1] += movePosition(disY, 2, -2);
            } else {
                // 좌우 이동
                catPos[0] += movePosition(disX, 2, -2);
            }
 
            // System.out.println("#고양이 좌표: " + Arrays.toString(catPos));
             
            // 쥐가 고양이에게 잡힌 경우, 반복문 종료
            if (mousePos[0] == catPos[0] && mousePos[1] == catPos[1]) {
                break;
            }
        }
 
        return ans;
    }
 
    static int movePosition(int v, int plusValue, int minusValue) {
        int dis = v;
        int absDis = Math.abs(v);
        if (absDis > plusValue) {
            if (v > 0) {
                dis = plusValue;
            } else {
                dis = minusValue;
            }
        }
 
        return dis;
    }
}