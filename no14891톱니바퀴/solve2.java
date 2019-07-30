package no14891톱니바퀴;

import java.io.*;
import java.util.*;

// 다른사람코드 차용, 덱, 재귀를 사용해 깔끔한코드 생성
public class solve2 {

	static LinkedList<Integer>[] list;
	public static void main(String[] args)throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		list = new LinkedList[5];
		
		for(int i=1; i<=4; i++) {
			
			list[i] = new LinkedList<Integer>(); //각 행렬 슬롯마다 링크드리스트 생성 필수!!
			
			String input = br.readLine();
			for( String s : input.split("")) {
				list[i].add(Integer.parseInt(s));
			}
		}
		/// 톱니바퀴 정보 입력 ///
		
		int roll = Integer.parseInt(br.readLine());
		
		while(roll > 0) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			int num = Integer.parseInt(st.nextToken());
			int direction = Integer.parseInt(st.nextToken());
			
			
			left(num-1, -direction); //해당 톱니바퀴의 왼족으로 진입
			right(num+1, -direction); //해당 톱니바퀴의 오른쪽으로 진입
			rotate(num, direction); //해당 톱니바퀴 갱신
			
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
