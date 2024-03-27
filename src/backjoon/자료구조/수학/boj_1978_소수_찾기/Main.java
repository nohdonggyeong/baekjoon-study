package backjoon.자료구조.수학.boj_1978_소수_찾기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    private static boolean isPrimeNumber(int x) {
        boolean result = x < 2 ? false : true;
        for (int i = 2; i <= Math.sqrt(x); i++) {
            if (x % i == 0) {
                result = false;
                break;
            }
        }

        return result;
    }

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        br.readLine(); // 읽어오는 개수는 사용할 필요 없으므로 버림.

        int count = 0;
        String inputStr = br.readLine();
        StringTokenizer st = new StringTokenizer(inputStr);

        while (st.hasMoreTokens()) {
            if (isPrimeNumber(Integer.parseInt(st.nextToken()))) {
                count++;
            }
        }

        bw.write(String.valueOf(count));
        bw.flush();
        bw.close();
        br.close();
    }
}
