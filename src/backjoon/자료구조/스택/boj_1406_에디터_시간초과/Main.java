package backjoon.자료구조.스택.boj_1406_에디터_시간초과;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

class Editor {

    private int index;
    StringBuilder sb;

    public Editor(String text) {
        this.index = text.length();
        this.sb = new StringBuilder(text);
    }

    public void goLeft() {
        if (index > 0) {
            index--;
        }
    }

    public void goRight() {
        if (sb.length() > index) {
            index++;
        }
    }

    public void backspace() {
        if (index > 0) {
            sb.deleteCharAt(index - 1);
            index--;
        }
    }

    public void paste(String str) {
        sb.insert(index, str);
        index += str.length();
    }

}

class Solution {
    public String solution(String text, String[] inputStrArr) {
        
        Editor editor = new Editor(text);

        for (int i = 0; i < inputStrArr.length; i++) {
            if ("L".equals(inputStrArr[i])) {
                editor.goLeft();
            } else if ("D".equals(inputStrArr[i])) {
                editor.goRight();
            } else if ("B".equals(inputStrArr[i])) {
                editor.backspace();
            } else if (inputStrArr[i].contains("P")) {
                editor.paste(inputStrArr[i].replace("P ", ""));
            }
        }

        String outputStr = editor.sb.toString();
        return outputStr;
    }
}

public class Main {
    public static void main(String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();

        int n = Integer.parseInt(br.readLine());
        String[] inputStrArr = new String[n];
        for (int i = 0; i < n; i++) {
            inputStrArr[i] = br.readLine();
        }

        String outputStr = new Solution().solution(text, inputStrArr);
        bw.write(outputStr);

        bw.flush();
        bw.close();
        br.close();
    }
}
