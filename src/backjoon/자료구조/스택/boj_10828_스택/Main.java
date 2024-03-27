package backjoon.자료구조.스택.boj_10828_스택;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Stack {

    private int top;
    private int[] stackArr;

    public Stack(int size) {
        this.top = -1;
        this.stackArr = new int[size];
    }

    public void push(int item) {
        this.stackArr[++top] = item;
    }

    public int pop() {
        if (this.top == -1) {
            return -1;
        }
        return this.stackArr[top--];
    }

    public int size() {
        return this.top + 1;
    }

    public int empty() {
        if (this.top == -1) {
            return 1;
        }
        return 0;
    }

    public int top() {
        if (this.top == -1) {
            return -1;
        }
        return this.stackArr[top];
    }
}

public class Main {
    
    private void solution() throws IOException {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine());
        Stack stack = new Stack(n);

        for (int i = 0; i < n; i++) {
            String cmd = br.readLine();

            if (cmd.contains("push")) {
                stack.push(Integer.parseInt(cmd.split(" ")[1]));
            } else if (cmd.contains("pop")) {
                bw.write(String.valueOf(stack.pop()));
                bw.newLine();
            } else if (cmd.contains("size")) {
                bw.write(String.valueOf(stack.size()));
                bw.newLine();
            } else if (cmd.contains("empty")) {
                bw.write(String.valueOf(stack.empty()));
                bw.newLine();
            } else if (cmd.contains("top")) {
                bw.write(String.valueOf(stack.top()));
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }

    public static void main(String[] args) throws IOException {
        new Main().solution();
    }
}
