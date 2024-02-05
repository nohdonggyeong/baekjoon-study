package 알고리즘.유니온파인드.boj_10775_공항;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int G, P;
	static int[] parent;
	static int answer;
	
	static int find(int a) {
		if (a == parent[a]) {
			return a;
		}
		return parent[a] = find(parent[a]);
	}
	
	static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		if (a == b) {
			return false;
		} else {
			if (a >= b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
			return true;
		}
	}
	
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			G = Integer.parseInt(br.readLine());
			P = Integer.parseInt(br.readLine());
			
			parent = new int[G + 1];
			for (int g = 1; g < G + 1; g++) {
				parent[g] = g;
			}
			
			answer = 0;
			int g;
			for (int p = 0; p < P; p++) {
				g = Integer.parseInt(br.readLine());
				g = find(g);
				if (find(g) != 0) {
					answer += 1;
					union(g, g - 1);
				} else {
					break;
				}
			}
			
			bw.write(String.valueOf(answer));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
