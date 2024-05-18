import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		Integer[] arr = {1, 3, 5, 6, 7, 8, 10, 11, 12, 13, 14};
		int findNum = 9;
		
		int arrIndex = Arrays.binarySearch(arr, findNum);
		
		List<Integer> list = Arrays.asList(arr);
		int listIndex = Collections.binarySearch(list, findNum);
		
		System.out.println("arrIndex: " + arrIndex);
		System.out.println("listIndex: " + listIndex);
	}
}
