package dataStructure.math.boj_1676_팩토리얼_0의_개수;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int num = Integer.parseInt(br.readLine());

        int cnt = 0; // 시간 절약: 5의 배수만큼 0이 생기는 것으로 팩토리얼 대체
        while(num >= 5) {
            cnt += num / 5;
            num /= 5;
        }

        bw.write(String.valueOf(cnt));
        bw.flush();
        bw.close();
        br.close();
    }
}
