package 알고리즘.시뮬레이션.boj_20055_컨베이어_벨트_위의_로봇;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	static int N, K;
	static Deque<Node> upBelt, downBelt;
	
	static class Node {
		private boolean robot;
		private int durability;
		
		Node(int durability) {
			this.robot = false;
			this.durability = durability;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		upBelt = new LinkedList<>();
		downBelt = new LinkedList<>();
		
		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < N; i++) {
			upBelt.offerLast(new Node(Integer.parseInt(st.nextToken())));
		}
		for (int i = 0; i < N; i++) {
			downBelt.offerFirst(new Node(Integer.parseInt(st.nextToken())));
		}
	}
}
