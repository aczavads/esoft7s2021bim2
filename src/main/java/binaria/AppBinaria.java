package binaria;

import javax.swing.JOptionPane;

public class AppBinaria {
	
	public static void main(String[] args) {
		int[] values = {1,2,3,4,5,6,7,8,9,10,11,12};
		int index = search(3, values);
		if (index != -1) {			
			System.out.println(values[index]);
		} else {
			System.out.println("Not found!");
		}
	}

	private static int search(int key, int[] values) {
		return binarySearch(key, values, 0, values.length-1);
	}

	private static int binarySearch(int key, int[] values, int left, int right) {
		System.out.println(left + "  " + right);
		JOptionPane.showConfirmDialog(null, "");
		if (left > right) {
			return -1;
		}
		int mid =  (right + left)/2;
		if (key > values[mid]) {
			return binarySearch(key, values, mid+1, right);
		} else if (key < values[mid]) {
			return binarySearch(key, values, left, mid-1);
		} else {
			return mid;
		}
	}
}
