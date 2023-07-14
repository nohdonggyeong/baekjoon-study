package 삼성SW역량테스트.ad_230712_그릇_만들기;


import java.util.*;
 
 
public class Main3 {
    int N;
    int M;
    int[] ingrediant;
     
    HashSet<String> answer;
     
    public int readInt() throws Exception {
        int c, n = System.in.read() & 15;
        while ((c = System.in.read()) > 32) n = (n << 3) + (n << 1) + (c & 15);
        if (c == 13) System.in.read();
        return n;
    }
     
    public void permutation(int[] selected, int start, int cnt) {
        if (cnt == M) {
            StringBuilder sb = new StringBuilder();
             
            for (int s : selected) {
                sb.append(s);
            }
             
            answer.add(sb.toString());
            return;
        }
         
        for (int i = start; i < N; i++) {
            selected[cnt] = ingrediant[i];
            permutation(selected, i + 1, cnt + 1);
        }
    }
     
    public void solution() throws Exception {
        int T = readInt();
        StringBuilder sb = new StringBuilder();
         
        for (int t = 1; t <= T; t++) {
            N = readInt();
            M = readInt();
             
            ingrediant = new int[N];
            for (int i = 0; i < N; i++) {
                ingrediant[i] = readInt();
            }
            Arrays.sort(ingrediant);
             
            answer = new HashSet<>();
            int[] selected = new int[M];
            permutation(selected, 0, 0);
             
            sb.append("#").append(t).append(" ").append(answer.size()).append("\n");
        }
         
        System.out.println(sb);
    }
     
    public static void main(String[] args) throws Exception {
        new Main3().solution();
    }
}