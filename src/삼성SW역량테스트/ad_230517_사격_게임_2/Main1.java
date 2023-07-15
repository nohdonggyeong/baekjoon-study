package 삼성SW역량테스트.ad_230517_사격_게임_2;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
public class Main1 {
         
        static int H; //세로
        static int W; //가로
        static int[][] matrix;
        static int[][] check;
        static int total; // 최대 점수
         
        public static void main(String[] args) throws FileNotFoundException {

    			System.setIn(new FileInputStream("src/삼성SW역량테스트/ad_230517_사격_게임_2/input.txt"));
                Scanner sc = new Scanner(System.in);
                 
                int T = sc.nextInt(); //testcase
                 
                for (int t = 1; t <= T; t ++ ) {
                         
                        H = sc.nextInt();
                        W = sc.nextInt();
                         
                        matrix = new int[H][W];
                        check = new int[H][W];
                        total = 0;
                        for (int h=0; h<H; h++) {
                                for (int w=0; w < W; w++) {
                                        matrix[h][w] = sc.nextInt();
                                }
                        }
                         
                        LinkedList<Game> q = new LinkedList<>();
                        Game game = new Game(new ArrayList<>(), -1);
                        q.add(game);
                         
                        //bfs
                        while(!q.isEmpty()) {       
                                Game tmpGame = q.removeFirst();
                                 
                                //종료, 계산
                                if (tmpGame.round == 3) {
                                         
                                        // 초기화
                                        for (int h=0; h<H; h++) {
                                                for (int w=0; w < W; w++) {
                                                        check[h][w] = matrix[h][w];
                                                }
                                        }
                                         
                                        //계산
                                        int tmpTotal = 0;
                                        for (int num=0; num<3; num++) {
                                                 
                                                Shoot tmp = tmpGame.getShootList().get(num);
                                                //가로일 경우
                                                if(tmp.getWay() == 0) {
                                                        for (int w = 0; w < W; w++) {
                                                                tmpTotal = tmpTotal + check[tmp.getH()][w];
                                                                check[tmp.getH()][w] = 0;
                                                        }
                                                }
                                                //세로일 경우
                                                else if (tmp.getWay() == 1) {
                                                        for (int h = 0; h < H; h++) {
                                                                tmpTotal = tmpTotal + check[h][tmp.getW()];
                                                                check[h][tmp.getW()] = 0;
                                                        }
                                                }
                                                //대각선 위 way = 2
                                                else if (tmp.getWay() == 2) {
                                                        if (tmp.getH() >=0 ) {
                                                                for (int h = 0; h < H; h++) {
                                                                        if(tmp.getH()-h >= 0 && h<W) {
                                                                                tmpTotal = tmpTotal + check[tmp.getH()-h][h];
                                                                                check[tmp.getH()-h][h] = 0;
                                                                        }       
                                                                }
                                                                 
                                                        } else {
                                                                for (int w = 0; w < W; w++) {
                                                                        if(tmp.getW()-w >= 0 && w<H) {
                                                                                tmpTotal = tmpTotal + check[w][tmp.getW()-w];
                                                                                check[w][tmp.getW()-w] = 0;
                                                                        }       
                                                                }
                                                        }
                                                }
                                                //대각선 아래 way = 3
                                                else if (tmp.getWay() == 3) {
                                                        if (tmp.getH() >=0 ) {
                                                                for (int h = 0; h < H; h++) {
                                                                        if(tmp.getH()+h < H && h<W) {
                                                                                tmpTotal = tmpTotal + check[tmp.getH()+h][h];
                                                                                check[tmp.getH()+h][h] = 0;
                                                                        }       
                                                                }
                                                                 
                                                        } else {
                                                                for (int w = 0; w < W; w++) {
                                                                        if(tmp.getW()+w < W && w<H) {
                                                                                tmpTotal = tmpTotal + check[w][tmp.getW()];
                                                                                check[w][tmp.getW()] = 0;
                                                                        }       
                                                                }
                                                        }
                                                }
                                        }
                                        if (tmpTotal >= total) {
                                        	
                            				System.out.println();
                            				for (int r = 0; r < H; r++) {
                            					for (int c = 0; c < W; c++) {
                            						System.out.print(check[r][c] == 0 ?  "O " : "X ");
                            					}
                            					System.out.println();
                            				}	
                            				
                                            total = tmpTotal;
                                        }
                                } else {
                                 
                                        //가로 way = 0
                                        for (int h=0; h<H; h++) {
                                                 
                                                ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                boolean isOkay = checkOkay(tmpList, h, -1, 0);
                                                 
                                                if (isOkay) {
                                                        tmpList.add(new Shoot(h, -1, 0));
                                                        q.add(new Game(tmpList, tmpGame.round+1));
                                                }
                                                 
                                        }
                                         
                                        //세로 way = 1
                                        for (int w=0; w<W; w++) {
                                                ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                boolean isOkay = checkOkay(tmpList, -1, w, 1);
                                                 
                                                if (isOkay) {
                                                        tmpList.add(new Shoot(-1, w, 1));
                                                        q.add(new Game(tmpList, tmpGame.round+1));
                                                }
                                                 
                                        }
                                         
                                        //대각선 위 way = 2
                                        for (int h=0; h<H; h++) {
                                                ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                 
                                                boolean isOkay = checkOkay(tmpList, h, -1, 2);
                                                 
                                                if (isOkay) {
                                                        tmpList.add(new Shoot(h, -1, 2));
                                                        q.add(new Game(tmpList, tmpGame.round+1));
                                                }
                                        }
                                         
                                        if (W > H) {
                                                for (int w=H; w<W; w++) {
                                                        ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                        boolean isOkay = checkOkay(tmpList, -1, w, 2);
                                                         
                                                        if (isOkay) {
                                                                tmpList.add(new Shoot(-1, w, 2));
                                                                q.add(new Game(tmpList, tmpGame.round+1));
                                                        }
                                                         
                                                }
                                        }
                                         
                                        //대각선 아래 way = 3
                                        for (int h=0; h<H; h++) {
                                                ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                 
                                                boolean isOkay = checkOkay(tmpList, -h, -1, 3);
                                                 
                                                if (isOkay) {
                                                        tmpList.add(new Shoot(h, -1, 3));
                                                        q.add(new Game(tmpList, tmpGame.round+1));
                                                }
                                                 
                                        }
                                         
                                        for (int w=1; w<W; w++) {
                                                ArrayList<Shoot> tmpList = (ArrayList<Shoot>) tmpGame.getShootList().clone();
                                                 
                                                boolean isOkay = checkOkay(tmpList, -1, w, 3);
                                                 
                                                if (isOkay) {
                                                        tmpList.add(new Shoot(-1, w, 3));
                                                        q.add(new Game(tmpList, tmpGame.round+1));
                                                }
                                                 
                                        }
                                }
                        }
                                 
                         
                        System.out.println("#"+t+" "+total);
                }
        }
        // 기억 나지 않는 부분
        private static boolean checkOkay(ArrayList<Shoot> tmpList, int h, int w, int way) {
                for (int i =0; i < tmpList.size(); i++) {
                        if (tmpList.get(i).h == h && tmpList.get(i).w == w && tmpList.get(i).way == way) {
                                return false;
                        }
                }
                return true;
        }
}
class Game {
        ArrayList<Shoot> shootList;
        int round;
        public ArrayList<Shoot> getShootList() {
                return shootList;
        }
        public void setShootList(ArrayList<Shoot> shootList) {
                this.shootList = shootList;
        }
        public Game(ArrayList<Shoot> shootList, int round) {
                super();
                this.shootList = shootList;
                this.round = round;
        }
}
class Shoot {
        int h;
        int w;
        int way;
        public int getH() {
                return h;
        }
        public void setH(int h) {
                this.h = h;
        }
        public int getW() {
                return w;
        }
        public void setW(int w) {
                this.w = w;
        }
        public int getWay() {
                return way;
        }
        public void setWay(int way) {
                this.way = way;
        }
        public Shoot(int h, int w, int way) {
                super();
                this.h = h;
                this.w = w;
                this.way = way;
        }
}