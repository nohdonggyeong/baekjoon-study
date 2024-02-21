package 알고리즘.세그먼트트리.boj_2243_사탕상자;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 상황
 * 각각의 사탕은 그 맛의 좋고 나쁨이 1부터 1,000,000까지의 정수로 구분
 * 1이 가장 맛있는 사탕을 의미하며, 1,000,000은 가장 맛없는 사탕을 의미
 * 수정이는 동생이 말을 잘 들은 정도에 따라서, 사탕상자 안에 있는 사탕들 중 몇 번째로 맛있는 사탕을 꺼내주곤 한다.
 * 
 * 입력
 * 첫째 줄에 수정이가 사탕상자에 손을 댄 횟수 n(1 ≤ n ≤ 100,000)이 주어진다.
 * 다음 n개의 줄에는 두 정수 A, B, 혹은 세 정수 A, B, C가 주어진다.
 * A가 1인 경우는 사탕상자에서 사탕을 꺼내는 경우이다. 이때에는 한 정수만 주어지며, B는 꺼낼 사탕의 순위를 의미한다. 이 경우 사탕상자에서 한 개의 사탕이 꺼내지게 된다.
 * A가 2인 경우는 사탕을 넣는 경우이다. 이때에는 두 정수가 주어지는데, B는 넣을 사탕의 맛을 나타내는 정수이고 C는 그러한 사탕의 개수이다. C가 양수일 경우에는 사탕을 넣는 경우이고, 음수일 경우에는 빼는 경우이다.
 * 맨 처음에는 빈 사탕상자에서 시작한다고 가정
 * 사탕의 총 개수는 2,000,000,000을 넘지 않는다.
 * 없는 사탕을 꺼내는 경우와 같은 잘못된 입력은 주어지지 않는다.
 * 
 * 출력
 * A가 1인 모든 입력에 대해서, 꺼낼 사탕의 맛의 번호를 출력 -> 1부터 순위대로 나열하고 입력으로 주어지는 차례의 맛의 번호를 고르기
 */

public class Main2 {
	static final int LAST_NUMBER = 1_000_000; 
	
	static int n;
	static SegmentTree sgtree = new SegmentTree(4 * LAST_NUMBER);
	
	static class SegmentTree {
		long[] tree;
		
		SegmentTree(int n) {
			tree = new long[n];
		}
		
		long init(int start, int end, int node) { // tree는 맛의 번호가 인덱스로 구성되고, 모두 0개로 초기화
			if (start == end) {
				return tree[node] = 0;
			}
			
			int mid = (start + end) / 2;
			return tree[node] = init(start, mid, node * 2) + init(mid + 1, end, node * 2 + 1);
		}
		
		void update(int start, int end, int node, int index, int diff) { // index에 해당하는 맛의 번호의 개수를 1개 빼거나, 몇 개를 빼거나 더하는 것으로 업데이트
			if (index < start || index > end) {
				return;
			}
			
			tree[node] += diff;
			
			if (start == end) {
				return;
			}
			
			int mid = (start + end) / 2;
			update(start, mid, node * 2, index, diff);
			update(mid + 1, end, node * 2 + 1, index, diff);
		}
		
		long sum(int start, int end, int node, int left, int right) { // left 번호부터 right 번호까지의 사탕의 개수 합계
			if (left > end || right < start) {
				return 0;
			}
			
			if (left <= start && right >= end) {
				return tree[node];
			}
			
			int mid = (start + end) / 2;
			return sum(start, mid, node * 2, left, right) + sum(mid + 1, end, node * 2 + 1, left, right);
		}
	}
	
	static int binarySearch(int start, int end, long order) { // B번째 사탕의 번호 출력
		do {
			int mid = (start + end) / 2;
			long sum = sgtree.sum(1, LAST_NUMBER, 1, 1, mid);
			
			if (sum < order) {
				start = mid + 1;
			} else if (sum >= order) {
				end = mid - 1;
			}
		} while (start <= end);
		
		return start;
	}
	public static void main(String[] args) {
		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
			StringTokenizer st;
			StringBuilder sb = new StringBuilder();
			
			sgtree.init(1, LAST_NUMBER, 1);

			n = Integer.parseInt(br.readLine());
			for (int i = 0; i < n; i++) {
				st = new StringTokenizer(br.readLine());
				int A = Integer.parseInt(st.nextToken());
				if (A == 1) {
					long B = Long.parseLong(st.nextToken());
					int number = binarySearch(1, LAST_NUMBER, B); // B번째에는 몇 번 사탕인가?
					sb.append(number).append("\n");
					sgtree.update(1, LAST_NUMBER, 1, number, -1); // number번째 사탕을 1개 빼기
				} else if (A == 2) {
					int B = Integer.parseInt(st.nextToken());
					int C = Integer.parseInt(st.nextToken());
					sgtree.update(1, LAST_NUMBER, 1, B, C); // B번 사탕을 C개 빼거나 더하기
				}
			}
			
			bw.write(sb.toString().trim());
			bw.flush();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
