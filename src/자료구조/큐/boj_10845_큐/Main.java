package 자료구조.큐.boj_10845_큐;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

class Queue {
    private ArrayList<String> queue;

    public Queue() {
        this.queue = new ArrayList<>();
    }

    public void push(String x) {
        queue.add(x);
    }

    public String pop() {
        String result = "-1";
        if (queue.size() > 0) {
            result = queue.get(0);
            queue.remove(0);
        }
        return result;
    }

    public String size() {
        return String.valueOf(queue.size());
    }

    public String empty() {
        return queue.size() > 0 ? "0" : "1";
    }

    public String front() {
        return queue.size() > 0 ? queue.get(0) : "-1";
    }

    public String back() {
        return queue.size() > 0 ? queue.get(queue.size() - 1) : "-1";
    }
}

public class Main {
    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        Queue queue = new Queue();

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String inputStr = br.readLine();
            if (inputStr.contains("push")) {
                queue.push(inputStr.replace("push ", ""));
            } else if ("pop".equals(inputStr)) {
                bw.write(queue.pop());
                bw.newLine();
            } else if ("size".equals(inputStr)) {
                bw.write(queue.size());
                bw.newLine();
            } else if ("empty".equals(inputStr)) {
                bw.write(queue.empty());
                bw.newLine();
            } else if ("front".equals(inputStr)) {
                bw.write(queue.front());
                bw.newLine();
            } else if ("back".equals(inputStr)) {
                bw.write(queue.back());
                bw.newLine();
            }
        }

        bw.flush();
        bw.close();
        br.close();
    }
}
