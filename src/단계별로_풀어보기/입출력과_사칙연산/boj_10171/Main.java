package 단계별로_풀어보기.입출력과_사칙연산.boj_10171;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;

public class Main {
	public static void main(String args[]) throws IOException {
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringBuilder sb = new StringBuilder();
		sb.append("\\    /\\").append("\n");
		sb.append(" )  ( ')").append("\n");
		sb.append("(  /  )").append("\n");
		sb.append(" \\(__)|");

		bw.write(sb.toString());
		bw.flush();
		bw.close();
	}
}
