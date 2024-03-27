package backjoon.알고리즘_분류.그리디.boj_1541_잃어버린_괄호;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			
			String str = br.readLine();
			String[] splitStr = str.split("\\-");
			
			String SplitString = splitStr[0];
			String[] calculationArr = SplitString.split("\\+");
			int sum = 0;
			for (int i = 0; i < calculationArr.length; i++) {
				sum += Integer.parseInt(calculationArr[i]);
			}
			
			for (int i = 1; i < splitStr.length; i++) {
				SplitString = splitStr[i];
				calculationArr = SplitString.split("\\+");
				for (int j = 0; j < calculationArr.length; j++) {
					sum -= Integer.parseInt(calculationArr[j]);
				}
			}
			
			bw.write(String.valueOf(sum));
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
