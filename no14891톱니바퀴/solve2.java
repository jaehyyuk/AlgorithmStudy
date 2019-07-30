package no14891��Ϲ���;

import java.io.*;
import java.util.*;

// �ٸ�����ڵ� ����, ��, ��͸� ����� ������ڵ� ����
public class solve2 {

	static LinkedList<Integer>[] list;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		list = new LinkedList[5];
		
		for(int i=1; i<=4; i++) {
			
			list[i] = new LinkedList<Integer>(); //�� ��� ���Ը��� ��ũ�帮��Ʈ ���� �ʼ�!!
			
			String input = br.readLine();
			for( String s : input.split("")) {
				list[i].add(Integer.parseInt(s));
			}
		}
		/// ��Ϲ��� ���� �Է� ///
		
		int roll = Integer.parseInt(br.readLine());
		
		while(roll > 0) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			
			
			left(num-1, -direction); //�ش� ��Ϲ����� �������� ����
			right(num+1, -direction); //�ش� ��Ϲ����� ���������� ����
			rotate(num, direction); //�ش� ��Ϲ��� ����
			
			roll--;
		}
		
		int score = 0;
		int[] scores = {1,2,4,8};
		for(int i=1; i<=4; i++) {
			
			if(list[i].get(0) == 1) {score += scores[i-1];}
			
		}
		System.out.println(score);
	}
	
	static void rotate(int num, int direction) {
		if(direction == -1) {
			list[num].addLast(list[num].pollFirst());
		}
		else {
			list[num].addFirst(list[num].pollLast());
		}
	}
	
	static boolean leftcheck(int a, int b) {
		
		if(list[a].get(2) == list[b].get(6)) {
			return false;
		}
		return true;
	}

	static boolean rightcheck(int a, int b) {
		if(list[a].get(6) == list[b].get(2)) {
			return false;
		}
		return true;
	}
	
	static void left(int num, int direction) {
		
		if(num < 1) return;
		
		if(leftcheck(num, num+1)) {
			left(num-1,-direction);
			rotate(num, direction);
		}
		
	}
	
	
	static void right(int num, int direction) {
		
		if(num > 4) return;
		
		if(rightcheck(num, num-1)) {
			right(num+1, -direction);
			rotate(num, direction);
		}
	}

}
