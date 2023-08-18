package AD검정.ad_230712_그릇_만들기;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
 
public class Main1 {
    static int T, N, M;
    static boolean[] visited;
    static Node[] arr;
    static ArrayList<Node> temp;
    static ArrayList<Integer> list;
 
    static class Node {
        int x;
 
        Node(int x) {
            this.x = x;
        }
 
        @Override
        public String toString() {
            return "" + x;
        }
    }
 
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("./input.txt"));
        StringTokenizer st;
 
        T = Integer.parseInt(br.readLine());
 
        for (int t = 1; t <= T; t++) {
            st = new StringTokenizer(br.readLine());
 
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
 
            arr = new Node[N];
            temp = new ArrayList<>();
            visited = new boolean[N];
            list = new ArrayList<>();
 
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int x = Integer.parseInt(st.nextToken());
                arr[i] = new Node(x);
            }
 
            backtrack(0, 0);
 
            ArrayList<Integer> removedNums = new ArrayList<>();
            for (int num : list) {
                if (!removedNums.contains(num))
                    removedNums.add(num);
            }
 
            System.out.println("#" + T + " " + removedNums.size());
        }
    }
 
    static void backtrack(int count, int start) {
        if (count == M) {
            int sum = 0;
            for (int i = 0; i < temp.size(); i++) {
                sum += temp.get(i).x;
            }
            list.add(sum);
            return;
        }
 
        for (int i = start; i < arr.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                temp.add(arr[i]);
                backtrack(count + 1, start + 1);
                temp.remove(arr[i]);
                visited[i] = false;
            }
        }
    }
}