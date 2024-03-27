package backjoon.자료구조.수학.boj_6588_골드바흐의_추측;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {

    // 시간 절약: 에라토스테네스의 체
    private static boolean[] getPrimeNumArr(int n) {
        // 0부터 n까지 총 n + 1개
        boolean[] primeNumArr = new boolean[n + 1];

        // 0, 1번 째를 제외하고 모두 true로 우선 초기화
        Arrays.fill(primeNumArr, true);
        primeNumArr[0] = primeNumArr[1] = false;

        for (int i = 2; i <= Math.sqrt(n); i++) {
            if (primeNumArr[i]) {
                for (int j = i * i; j <= n; j += i) {
                    // true로 우선 초기화했던 값을 소수가 아니라는 false로 정정
                    primeNumArr[j] = false;
                }
            }
        }

        return primeNumArr;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 시간 절약
        boolean[] primeNumArr = getPrimeNumArr(1000000);
        int n;
        StringBuilder sb;
        String result;

        while (true) {
            // 숫자 읽어오기
            n = Integer.parseInt(br.readLine());
            if (n == 0)
                break;

            // result
            sb = new StringBuilder();
            result = "Goldbach's conjecture is wrong.";

            for (int i = 3; i <= n / 2; i += 2) {
                if (primeNumArr[i] && primeNumArr[n - i]) {
                    sb.append(String.valueOf(n));
                    sb.append(" = ");
                    sb.append(String.valueOf(i));
                    sb.append(" + ");
                    sb.append(String.valueOf(n - i));
                    result = sb.toString();
                    break;
                }
            }

            bw.write(result);
            bw.newLine();
        }
        bw.flush();
        ;
        bw.close();
        br.close();
    }
}
