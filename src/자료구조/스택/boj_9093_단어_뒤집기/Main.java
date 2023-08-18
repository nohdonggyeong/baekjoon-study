package 자료구조.스택.boj_9093_단어_뒤집기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private void solution() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            StringTokenizer st = new StringTokenizer(br.readLine());
            while (st.hasMoreTokens()) {
                StringBuilder word = new StringBuilder(st.nextToken());
                word.reverse();
                sb.append(word);
                if (st.hasMoreTokens()) {
                    sb.append(" ");
                }
            }
            bw.write(String.valueOf(sb));
            bw.newLine();
        }
        
        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String args[]) throws IOException {
        new Main().solution();
    }
}
