package backjoon.자료구조.트리셋.boj_2957_이진_탐색_트리;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main_시간초과 {
	static class Node {
		int number;
		Node left;
		Node right;
		
		Node(int number) {
			this.number = number;
		}
	}
	
	static int C = 0;
	static Node root;
	
	static void insert(int number, Node node) {
		++C;
		if (number < node.number) {
			if (node.left == null) {
				Node left = new Node(number);
				node.left = left;
			} else {
				insert(number, node.left);
			}
		} else if (number > node.number) {
			if (node.right == null) {
				Node right = new Node(number);
				node.right = right;
			} else {
				insert(number, node.right);
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		try (BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringBuilder sb = new StringBuilder();
			
			int N = Integer.parseInt(br.readLine());
			for (int n = 0; n < N; n++) {
				int number = Integer.parseInt(br.readLine());
				if (n == 0) {
					root = new Node(number);
				} else {
					insert(number, root);
				}
				sb.append(C).append("\n");
			}
			
			bw.write(sb.deleteCharAt(sb.length() - 1).toString());
			bw.flush();
		}
	}
}
