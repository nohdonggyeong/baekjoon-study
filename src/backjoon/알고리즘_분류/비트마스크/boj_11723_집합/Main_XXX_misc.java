package backjoon.알고리즘_분류.비트마스크.boj_11723_집합;

import java.io.IOException;

public class Main_XXX_misc {
	public static void main(String args[]) throws IOException {
		int bitSet = 0;
		int n = 2;
		
		bitSet = (1 << 0); // 1(2) = 1(10)
		System.out.println(bitSet);
		
		bitSet = (1 << 1); // 10(2) = 2(10)
		System.out.println(bitSet);
		
		bitSet = (1 << 2); // 100(2) = 4(10)
		System.out.println(bitSet);
		
		// {2, 1, 0}
		bitSet = (1 << (n + 1)) - 1; // 1000(2) - 1(2) = 111(2) = 7(10)
		System.out.println(bitSet);
	}
}
