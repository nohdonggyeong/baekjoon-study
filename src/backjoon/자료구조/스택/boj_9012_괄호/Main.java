package backjoon.자료구조.스택.boj_9012_괄호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Solution {
    public String[] solution(String[] inputStrArr) {
        String[] outputStrArr = new String[inputStrArr.length];

        for (int i = 0; i < inputStrArr.length; i++) {
            Stack<String> stack = new Stack<>();
            String output = "YES";
            String[] tokenArr = inputStrArr[i].split("");

            for (String token : tokenArr) {
                if ("(".equals(token)) {
                    stack.push(token);
                } else if (")".equals(token)) {
                    if (!stack.empty()) {
                        stack.pop();
                    } else {
                        output = "NO";
                        break;
                    }
                }
            }
            if (!stack.empty()) {
                output = "NO";
            }

            outputStrArr[i] = output;
        }

        return outputStrArr;
    }
}

public class Main {
    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        String[] inputStrArr = new String[n];
        for (int i = 0; i < n; i++) {
            inputStrArr[i] = br.readLine();
        }

        String[] outputStrArr =  new Solution().solution(inputStrArr);
        for (int i = 0; i < n; i++) {
            bw.write(outputStrArr[i]);
            bw.newLine();
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
