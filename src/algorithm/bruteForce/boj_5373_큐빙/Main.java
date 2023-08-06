package algorithm.bruteForce.boj_5373_큐빙;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
	static int T, N;
	static Map<Character, Integer> faceMap;
	static char[][][] cube;
	
	static void rotateFace(char face, char direction) {
		char[][] newFace = new char[3][3];
		if (direction == '+') {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					newFace[r][c] = cube[faceMap.get(face)][2 - c][r];
				}
			}
		} else {
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					newFace[r][c] = cube[faceMap.get(face)][c][2 - r];
				}
			}
		}
		
		for (int r = 0; r < 3; r++) {
			for (int c = 0; c < 3; c++) {
				cube[faceMap.get(face)][r][c] = newFace[r][c];
			}
		}
	}
	
	static void rotateEdges(char face, char direction) {
		char[] temp = new char[3];
		switch(face) {
		case 'U':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('B')][0][i];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][0][i] = cube[faceMap.get('L')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][0][i] = cube[faceMap.get('F')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][0][i] = cube[faceMap.get('R')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][0][i] = temp[i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][0][i] = cube[faceMap.get('R')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][0][i] = cube[faceMap.get('F')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][0][i] = cube[faceMap.get('L')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][0][i] = temp[i];
				}
			}
			break;
		case 'D':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('F')][2][i];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][2][i] = cube[faceMap.get('L')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][2][i] = cube[faceMap.get('B')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][2][i] = cube[faceMap.get('R')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][2][i] = temp[i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][2][i] = cube[faceMap.get('R')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][2][i] = cube[faceMap.get('B')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][2][i] = cube[faceMap.get('L')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][2][i] = temp[i];
				}
			}
			break;
		case 'F':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('U')][2][i];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][2][i] = cube[faceMap.get('L')][2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][i][2] = cube[faceMap.get('D')][0][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][0][i] = cube[faceMap.get('R')][2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][i][0] = temp[i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][2][i] = cube[faceMap.get('R')][i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][i][0] = cube[faceMap.get('D')][0][2 - i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][0][i] = cube[faceMap.get('L')][i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][i][2] = temp[2 - i];
				}
			}
			break;
		case 'B':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('U')][0][i];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][0][i] = cube[faceMap.get('R')][i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][i][2] = cube[faceMap.get('D')][2][2 - i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][2][i] = cube[faceMap.get('L')][i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][i][0] = temp[2 - i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][0][i] = cube[faceMap.get('L')][2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('L')][i][0] = cube[faceMap.get('D')][2][i];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][2][i] = cube[faceMap.get('R')][2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('R')][i][2] = temp[i];
				}
			}
			break;
		case 'L':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('U')][i][0];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][i][0] = cube[faceMap.get('B')][2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][i][2] = cube[faceMap.get('D')][2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][i][0] = cube[faceMap.get('F')][i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][i][0] = temp[i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][i][0] = cube[faceMap.get('F')][i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][i][0] = cube[faceMap.get('D')][i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][i][0] = cube[faceMap.get('B')][2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][i][2] = temp[2 - i];
				}
			}
			break;
		case 'R':
			for (int i = 0; i < 3; i++) {
				temp[i] = cube[faceMap.get('U')][i][2];
			}
			if (direction == '+') {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][i][2] = cube[faceMap.get('F')][i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][i][2] = cube[faceMap.get('D')][i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][i][2] = cube[faceMap.get('B')][2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][i][0] = temp[2 - i];
				}
			} else {
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('U')][i][2] = cube[faceMap.get('B')][2 - i][0];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('B')][i][0] = cube[faceMap.get('D')][2 - i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('D')][i][2] = cube[faceMap.get('F')][i][2];
				}
				for (int i = 0; i < 3; i++) {
					cube[faceMap.get('F')][i][2] = temp[i];
				}
			}
			break;
		}
	}
	
	public static void main(String args[]) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;

		faceMap = new HashMap<>(); 
		faceMap.put('U', 0);
		faceMap.put('D', 1);
		faceMap.put('F', 2);
		faceMap.put('B', 3);
		faceMap.put('L', 4);
		faceMap.put('R', 5);
		
		T = Integer.parseInt(br.readLine());
		for (int t = 0; t < T; t++) {
			cube = new char[][][] {{{'w', 'w', 'w'}, {'w', 'w', 'w'}, {'w', 'w', 'w'}}
									, {{'y', 'y', 'y'}, {'y', 'y', 'y'}, {'y', 'y', 'y'}}
									, {{'r', 'r', 'r'}, {'r', 'r', 'r'}, {'r', 'r', 'r'}}
									, {{'o', 'o', 'o'}, {'o', 'o', 'o'}, {'o', 'o', 'o'}}
									, {{'g', 'g', 'g'}, {'g', 'g', 'g'}, {'g', 'g', 'g'}}
									, {{'b', 'b', 'b'}, {'b', 'b', 'b'}, {'b', 'b', 'b'}}};
			
			N = Integer.parseInt(br.readLine());
			st = new StringTokenizer(br.readLine());
		 	for (int n = 0; n < N; n++) {
				String input = st.nextToken();
				rotateFace(input.charAt(0), input.charAt(1));
				rotateEdges(input.charAt(0), input.charAt(1));
		 	}
			
			for (int r = 0; r < 3; r++) {
				for (int c = 0; c < 3; c++) {
					bw.write(String.valueOf(cube[0][r][c]));
				}
				bw.newLine();
			}
		}
		
		bw.flush();
		
		bw.close();
		br.close();
	}
}
