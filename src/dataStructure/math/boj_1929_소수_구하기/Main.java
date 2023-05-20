package dataStructure.math.boj_1929_소수_구하기;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

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

        String[] inputStrArr = br.readLine().split(" ");
        int num1 = Integer.parseInt(inputStrArr[0]);
        int num2 = Integer.parseInt(inputStrArr[1]);
        for (int i = num1; i <= num2; i++) {
            if (isPrimeNumber(i)) {
                bw.write(String.valueOf(i));
                bw.newLine();
            }
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
