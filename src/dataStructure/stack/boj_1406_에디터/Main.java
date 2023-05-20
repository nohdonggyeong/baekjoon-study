package dataStructure.stack.boj_1406_에디터;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Stack;

class Editor {
    private Stack<Character> leftStack;
    private Stack<Character> rightStack;

    public Editor(String text) {
        this.leftStack = new Stack<>();
        this.rightStack = new Stack<>();

        for (int i = 0; i < text.length(); i++) {
            leftStack.push(text.charAt(i));
        }
    }

    public void moveLeft() {
        if (!leftStack.isEmpty()) {
            rightStack.push(leftStack.pop());
        }
    }
    
    public void moveRight() {
        if (!rightStack.isEmpty()) {
            leftStack.push(rightStack.pop());
        }
    }

    public void backspace() {
        if (!leftStack.isEmpty()) {
            leftStack.pop();
        }
    }

    public void paste(String inputStr) {
        leftStack.push(inputStr.charAt(2));
    }

    public String printString() {
        while (!leftStack.empty()) {
            rightStack.push(leftStack.pop());
        }
        StringBuilder sb = new StringBuilder();
        while (!rightStack.empty()) {
            sb.append(rightStack.pop());
        }
        return sb.toString();
    }
}

public class Main {
    // 스택 자료구조를 사용하면 실행 속도가 빨라진다.
    // String을 사용하는 것보다 Character를 사용하는 것이 더 빠르다.

    public static void main (String args[]) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String text = br.readLine();
        Editor editor = new Editor(text);

        int n = Integer.parseInt(br.readLine());
        for (int i = 0; i < n; i++) {
            String inputStr = br.readLine();
            if ("L".equals(inputStr)) {
                editor.moveLeft();
            } else if ("D".equals(inputStr)) {
                editor.moveRight();
            } else if ("B".equals(inputStr)) {
                editor.backspace();
            } else if (inputStr.contains("P")) {
                editor.paste(inputStr);
            }
        }
        
        bw.write(editor.printString());
        bw.flush();
        bw.close();
        br.close();
    }
}