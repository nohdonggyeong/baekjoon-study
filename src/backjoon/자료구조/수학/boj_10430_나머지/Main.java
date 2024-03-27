package backjoon.자료구조.수학.boj_10430_나머지;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStrArr = br.readLine().split(" ");
        int A = Integer.parseInt(inputStrArr[0]);
        int B = Integer.parseInt(inputStrArr[1]);
        int C = Integer.parseInt(inputStrArr[2]);

        bw.write(String.valueOf((A + B) % C));
        bw.newLine();

        bw.write(String.valueOf(((A % C) + (B % C)) % C));
        bw.newLine();

        bw.write(String.valueOf((A * B) % C));
        bw.newLine();

        bw.write(String.valueOf(((A % C) * (B % C)) % C));
        bw.newLine();

        bw.flush();
        bw.close();
        br.close();
    }
}
