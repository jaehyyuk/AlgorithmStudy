package no1_2017;

public class Solution_other {

	public static void main(String[] args) {
		int n = 5; 
		int[] arr1 = {9, 20, 28, 18, 11};
		int[] arr2 = {30, 1, 21, 17, 28};
		
		String answer[] = new String[n];
		
		
		//System.out.println(arr1[0] | arr2[0]);  --> 2���� ��ȯ �� or������ ��� ����! �ű�;
		
		for(int i=0; i<n; i++) {
			answer[i] = Integer.toBinaryString(arr1[i]|arr2[i]);
		}
		
		for(int i=0; i<n; i++) {
			
			answer[i] = String.format("%"+n+"s", answer[i]); //5������ ��Ʈ���������� �ٲ���
			answer[i] = answer[i].replaceAll("1", "#"); //��ȯ�Ȱ��� ����
			answer[i] = answer[i].replaceAll("0", " ");

		}
		
		for(int i=0; i<n; i++) {
			System.out.println(answer[i]);
			
		}

	}

}
