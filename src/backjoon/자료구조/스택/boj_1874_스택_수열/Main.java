package backjoon.자료구조.스택.boj_1874_스택_수열;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Solution {
    public String[] solution(String[] inputStrArr) {
        int n = inputStrArr.length;
        int index = 0;
        Stack<Integer> stack = new Stack<>();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < inputStrArr.length; i++) {
            int c = Integer.parseInt(inputStrArr[i]);

            while (c > index) {
                stack.push(++index);
                sb.append("+ ");
            }
            if (c == stack.peek()) {
                stack.pop();
                sb.append("- ");
            } else {
                sb = new StringBuilder();
                sb.append("NO");
                break;
            }
        }
        return sb.toString().split(" ");
    }
}

public class Main {
    public static void main(String args[]) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] inputStrArr = new String[n];
        for (int i = 0; i < n; i++) {
            inputStrArr[i] = br.readLine();
        }

        String[] outputStrArr = new Solution().solution(inputStrArr);
        for (int i = 0; i < outputStrArr.length; i++) {
            bw.write(outputStrArr[i]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
