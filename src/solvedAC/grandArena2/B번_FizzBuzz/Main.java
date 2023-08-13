package solvedAC.grandArena2.B번_FizzBuzz;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	static int number, index;
	static String[] input;
	
	static boolean isInteger(String str) {
		try {
			Integer.parseInt(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		input = new String[3];
		number = 0;
		for (int i = 0; i < 3; i++) {
			String str = br.readLine();
			input[i] = str;
			if (isInteger(str)) {
				number = Integer.parseInt(str);
				index = i;
			}
		}
		
		if (number == 0) { // 숫자 없었다.
		} else { // 숫자 있었다.
			int next = number + (3 - index);
			if (next % 3 == 0 && next % 5 != 0) {
				bw.write("Fizz");
			} else if (next % 5 == 0 && next % 3 != 0) {
				bw.write("Buzz");
			} else if (next % 3 == 0 && next % 5 == 0) {
				bw.write("FizzBuzz");
			} else {
				bw.write(String.valueOf(next));
			}
		}

		bw.flush();
		
		bw.close();
		br.close();
	}
}
