package dataStructure.math.boj_10872_팩토리얼;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static int factorial(int n) {
        return n > 1 ? n * factorial(n - 1) : 1;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        bw.write(String.valueOf(factorial(n)));
        bw.flush();
        bw.close();
        br.close();
    }
}
