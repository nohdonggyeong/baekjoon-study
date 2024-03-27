package sw검정.advanced_기출문제.ad_230712_그릇_만들기;

import java.io.IOException;
import java.util.Arrays;

public class CopyTest {
	public static void main(String args[]) throws IOException {
		int[] test = new int[10];
		for (int i = 10; i > 0; i--) {
			test[10 - i] = i;
		}
		
		System.out.print("test: ");
		for (int i = 0; i < 10; i++) {
			System.out.print(test[i] + " ");
		}
		System.out.println();
		
		int[] copy = Arrays.copyOfRange(test, 3, 8);
		
		System.out.print("copy: ");
		for (int i = 0; i < copy.length; i++) {
			System.out.print(copy[i] + " ");
		}
	}
}
