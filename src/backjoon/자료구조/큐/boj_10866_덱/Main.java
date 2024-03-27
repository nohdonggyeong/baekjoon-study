package backjoon.자료구조.큐.boj_10866_덱;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Deque {
    private ArrayList<String> deque;

    public Deque() {
        this.deque = new ArrayList<>();
    }

    public void push_front(String X) {
        deque.add(0, X);
    }

    public void push_back(String X) {
        deque.add(X);
    }

    public String pop_front() {
        String result = "-1";
        if (deque.size() > 0) {
            result = deque.get(0);
            deque.remove(0);
        }
        return result;
    }

    public String pop_back() {
        String result = "-1";
        if (deque.size() > 0) {
            result = deque.get(deque.size() - 1);
            deque.remove(deque.size() - 1);
        }
        return result;
    }

    public String size() {
        return String.valueOf(deque.size());
    }

    public String empty() {
        String result = "0";
        if (deque.isEmpty()) {
            result = "1";
        }
        return result;
    }

    public String front() {
        String result = "-1";
        if (deque.size() > 0) {
            result = deque.get(0);
        }
        return result;
    }

    public String back() {
        String result = "-1";
        if (deque.size() > 0) {
            result = deque.get(deque.size() - 1);
        }
        return result;
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Deque deque = new Deque();
        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String inputStr = br.readLine();
            if (inputStr.contains("push_front")) {
                deque.push_front(inputStr.replace("push_front ", ""));
            } else if (inputStr.contains("push_back")) {
                deque.push_back(inputStr.replace("push_back ", ""));
            } else if ("pop_front".equals(inputStr)) {
                bw.write(deque.pop_front());
                bw.newLine();;
            } else if ("pop_back".equals(inputStr)) {
                bw.write(deque.pop_back());
                bw.newLine();
            } else if ("size".equals(inputStr)) {
                bw.write(deque.size());
                bw.newLine();
            } else if ("empty".equals(inputStr)) {
                bw.write(deque.empty());
                bw.newLine();
            } else if ("front".equals(inputStr)) {
                bw.write(deque.front());
                bw.newLine();
            } else if ("back".equals(inputStr)) {
                bw.write(deque.back());
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}