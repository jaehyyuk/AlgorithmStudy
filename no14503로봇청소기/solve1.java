package no14503로봇청소기;

import java.io.*;
import java.util.*;


class Robot{ //로봇의 위치와 바라보는 방향을 나타내는 class
	int r,c,v;
	
	Robot(int r, int c, int v){
		this.r = r;
		this.c = c;
		this.v = v;
	}
}

public class solve1 {
	
	
	static int[][] matrix;
	static boolean[][] clean;
	static int row, col;
	
	//왼쪽방향벡터, 즉 0번을 바라보면 인덱스0은 0번을 바라볼때의 왼쪽위치를 나타냄
	static int[] vectorCol = {-1,0,1,0};
	static int[] vectorRow = {0,1,0,-1};
	static Queue<Robot> q;
	
	//후진방향벡터, 0번을 바라볼때 의 후진 방향 벡터
	static int[] backvecRow = {1,0,-1,0};
	static int[] backvecCol = {0,1,0,-1};
	
	
	
	public static void startclean() {
		
		boolean end = false; //더이상 움직일 수 없음을 나타내는 변수
		
		while(!end) {
			Robot temp = q.poll();
			int tempR = temp.r;
			int tempC = temp.c;
			int tempV = temp.v;
			
		
			if(!clean[tempR][tempC]) {clean[tempR][tempC] = true;} //청소함
			
			boolean allclean = true; //4방향이 모두 청소되어 있거나 벽이다
			
			for(int i=0; i<4; i++) {
				int moveR = tempR + vectorRow[tempV];
				int moveC = tempC + vectorCol[tempV];
				
				if(rgck(moveR,moveC) && matrix[moveR][moveC]==0 && !clean[moveR][moveC]) {
					tempV = (tempV+1)%4; //다음방향의 바라보는방향갱신
					q.add(new Robot(moveR,moveC,tempV));
					allclean = false; //4방향이 모두 청소되어 있거나 벽이 아니다로 갱신
					break;
				}
				
				tempV = (tempV+1)%4; //다음방향의 바라보는방향갱신
				
			}
			

			//tempV는 마지막 방향을 보고있다.
			if(allclean) { //4방향이 모두 청소되어 있거나 벽이라면 allclean은 true ,if문 진입
				
				int backR = tempR + backvecRow[tempV];
				int backC = tempC + backvecCol[tempV];
				
				//더이상 청소할 곳이 없고 뒤가 벽이다
				if(matrix[backR][backC] == 1) { 
					end = true;
				}
				else {
					q.add(new Robot(backR,backC,tempV));
				}
				
			}
			
		}
		
	}
	
	
	//범위체크메소드
	public static boolean rgck(int r, int c) {
		
		if(r<0 || r>=row || c<0 || c>=col) {return false;}
		else {return true;}
	}
	
	

	public static void main(String[] args)  throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		matrix = new int[row][col];
		clean = new boolean[row][col];
		
		StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
		
		int startR = Integer.parseInt(st1.nextToken());
		int startC = Integer.parseInt(st1.nextToken());
		int startV = Integer.parseInt(st1.nextToken());
		
		for(int i=0; i<row; i++) {
			
			StringTokenizer tempst = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<col; j++) {
				matrix[i][j] = Integer.parseInt(tempst.nextToken());
			}
		}
		
		q = new LinkedList<Robot>();
		
		//제시문은 3번방향이 왼쪽이나 왼쪽방향으로 회전하는 배열을 쉽게 구현하기 위해 1과3의 위치를 바꿔서 생각함
		if(startV==3) {startV=1;}		
		else if(startV==1) {startV=3;}
		
		q.add(new Robot(startR, startC, startV));
		
		startclean();
		
		int count = 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(clean[i][j]) {count++;} //청소된곳 카운트
			}
		}
		
		System.out.println(count);
	}

}
