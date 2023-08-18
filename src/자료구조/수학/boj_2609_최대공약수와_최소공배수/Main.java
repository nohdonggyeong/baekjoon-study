package 자료구조.수학.boj_2609_최대공약수와_최소공배수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Solution {
    private int getGCD(int x, int y) {
        // 시간 절약: 유클리드 호제법
        if (y == 0) return x;
        return getGCD(y, x % y);
    }

    private int getLCM(int x, int y) {
        return x * y / getGCD(x, y);
    }

    public String[] solution(String[] inputStrArr) {
        int x = Integer.parseInt(inputStrArr[0]);
        int y = Integer.parseInt(inputStrArr[1]);

        String[] outputStrArr = new String[2];
        outputStrArr[0] = String.valueOf(getGCD(x, y));
        outputStrArr[1] = String.valueOf(getLCM(x, y));

        return outputStrArr;
    } 
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStrArr = br.readLine().split(" ");
        String[] outputStrArr = new Solution().solution(inputStrArr);

        bw.write(outputStrArr[0]);
        bw.newLine();
        bw.write(outputStrArr[1]);

        bw.flush();
        bw.close();
        br.close();
    }
}
