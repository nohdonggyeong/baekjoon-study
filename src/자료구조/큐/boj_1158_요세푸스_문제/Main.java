package 자료구조.큐.boj_1158_요세푸스_문제;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public String[] solution(int N, int K) {
        int index = 0;
        String[] outputStrArr = new String[N];
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            queue.offer(i + 1);
        }

        while (queue.size() > 0) {
            for (int i = 0; i < K - 1; i++) {
                queue.offer(queue.poll());
            }
            outputStrArr[index++] = String.valueOf(queue.poll());
        }

        return outputStrArr;
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String[] inputStrArr = br.readLine().split(" ");
        String[] outputStrArr = new Solution().solution(Integer.parseInt(inputStrArr[0]), Integer.parseInt(inputStrArr[1]));
        bw.write("<");
        for (int i = 0; i < outputStrArr.length; i++) {
            bw.write(outputStrArr[i]);
            if (i < outputStrArr.length - 1) {
                bw.write(", ");
            }
        }
        bw.write(">");

        bw.flush();
        bw.close();
        br.close();
    }
}
