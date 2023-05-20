package dataStructure.math.boj_1934_최소공배수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    private static int getGCD(int x, int y) {
        // 시간 절약: 유클리드 호제법
        if (y == 0) return x;
        return getGCD(y, x % y);
    }

    private static int getLCM(int x, int y) {
        return x * y / getGCD(x, y);
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String[] inputStrArr = br.readLine().split(" ");
            bw.write(String.valueOf(getLCM(Integer.parseInt(inputStrArr[0]), Integer.parseInt(inputStrArr[1]))));
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
