package no3190;
import java.io.*;
import java.util.*;

/*
 * 1. 현재 위치에서 다음 위치를 살핀다 이때, 현재의 dirnow[][] 와 dirnext[][]를 살핀다 (방향저장배열, 현재의 진행방향 과 다음의 예정진행방향)
 * 2. dirnext에 따라 다음 방향은 달라진다
 * 3. 이를 토대로 위치를 갱신한다.
 * 	3-1. 갱신한 위치의 r,c가 범위 안에 들어오는가? -> 아니면 break를 걸고 빠져나간다. 
 * 4. 범위 안에 들어온다면 갱신한 위치의 진행방향들을 업데이트 시켜준다
 * 	4-1. 갱신한 위치의 dirnow는 갱신하기 전의 dirnext다
 *  4-2. 갱신한 위치의 dirnext는 주어진 방향 정보를 비교한 후 갱신한다
 *   4-2-1. 방향을 바꿔야하는 time일 경우 갱신한 위치의 dirnow(갱신전 dirnext)와 받아온 방향정보를 토대로 구한다 (get_dir함수)
 *   4-2-2. 방향을 바꾸지 않아도 될 경우 (갱신한)dirnext는 (갱신한)dirnow의 값과 같다.
 * 5. 방향정보를 업데이트 했으면 갱신한 위치의 있는 것이 사과 인지 배인지 뭔지 알아본다.
 * 	5-1. 갱신한 위치의 것이 사과이면 먹는다 --> tail위치 갱신 x
 *  5-2. 갱신한 위치가 뱀이다 --> break;
 *  5-3. 갱신한 위치에 아무것도 없다 --> tail위치 갱신
 */

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] dirnow;
	static int[][] dirnext;
	
	
	static final int right = 1;
	static final int up = 2;
	static final int left = 3;
	static final int down = 4;
	
	static int[] vecRow = {-1,0,-1,0,1};
	static int[] vecCol = {-1,1,0,-1,0};
	
	//현재의 방향과 바꿀 방향의 정보를 토대로 4방향 중 하나를 리턴
	static int get_dir(int nowdir, char c) {
		
		int dir = 0;
		switch(nowdir) {
		
			case 1:
				if(c=='D') {
					dir=down;
				}
				else {
					dir = up;
				}
				break;
				
			case 2:
				if(c=='D') {
					dir = right;
				}else {
					dir = left;
				}
				break;
				
			case 3:
				if(c=='D') {
					dir=up;
				}else {
					dir=down;
				}
				
				break;
				
			case 4:
				if(c=='D') {
					dir = left;
				}else {
					dir=right;
				}
				
			
		}
		
		return dir;
		
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dirnow = new int[N+1][N+1];
		dirnext = new int[N+1][N+1];
		
		int app = Integer.parseInt(br.readLine());
		
		for(int i=0; i<app; i++) {
			String st[] = br.readLine().split(" ");
			int r = Integer.parseInt(st[0]);
			int c = Integer.parseInt(st[1]);
			
			map[r][c] = 2;
		}
		
		int turn = Integer.parseInt(br.readLine());
		Queue<Integer> time = new LinkedList<>();
		Queue<Character> dir = new LinkedList<>();
		for(int i=0; i<turn; i++) {
			String st[] = br.readLine().split(" ");
			time.add(Integer.parseInt(st[0]));
			dir.add(st[1].charAt(0));   //*** charAt!
		}

		/////////////////////... Input///////////////////////
		
		//setting initial value
		dirnow[1][1] = right;
		dirnext[1][1] = right;
		map[1][1] = 1;
		int nowR = 1; int nowC = 1; //head location
		int endR = 1; int endC = 1; //tail location
		
		
		// polling the next turning point values
		int nexttime = time.poll();
		char nextdir = dir.poll();
		
		int totaltime = 0; // time of moving value
		
		while(true) {  
			//1. 현재 위치에서 다음 위치를 살핀다 이때, 현재의 dirnow[][] 와 dirnext[][]를 살핀다 (방향저장배열, 현재의 진행방향 과 다음의 예정진행방향)
			int nowd = dirnow[nowR][nowC]; //현재 위치의 현재 방향
			int nextd = dirnext[nowR][nowC]; //현재 위치의 다음 방향
			
			//2. dirnext에 따라 다음 방향은 달라진다 (r,c 갱신)
			nowR = nowR+vecRow[nextd];
			nowC = nowC+vecCol[nextd];
			
			//3-1. 갱신한 위치의 r,c가 범위 안에 들어오는가?
			if(nowR>=1 && nowR<=N && nowC>=1 && nowC<=N) {
				
				//갱신한 위치가 뱀인가?
				if(map[nowR][nowC]==1) break;
				
				//4-1. 갱신한 위치의 dirnow는 갱신하기 전의 dirnext다
				dirnow[nowR][nowC] = nextd;
				
				totaltime++; //다음곳 이므로 시간 증가
				
				if(totaltime == nexttime) {
					// 방향을 바꿔야하는 time일 경우 갱신한 위치의 dirnow(갱신전 dirnext)와 받아온 방향정보를 토대로 구한다 (get_dir함수)
					dirnext[nowR][nowC] = get_dir(nextd, nextdir);
					
					//nexttime 과 nextdir갱신
					if(!time.isEmpty()) {
						nexttime = time.poll();
						nextdir = dir.poll();
					}
					
					
				}else {
					//방향을 바꾸지 않아도 될 경우 (갱신한)dirnext는 (갱신한)dirnow의 값과 같다.
					dirnext[nowR][nowC] = nextd; //갱신한 dirnow는 갱신전의 dirnext이므로 nextd와 같음
				}
				
				

				//갱신한 위치의 것이 사과이면 먹는다 --> tail위치 갱신 x, 뱀표시
				if(map[nowR][nowC]==2){
					map[nowR][nowC]=1;
				}
				//갱신한 위치에 아무것도 없다 -->  뱀표시, 기존꼬리 철수, tail위치 갱신
				else {

					map[nowR][nowC]=1;
					map[endR][endC] = 0; //기존 tail 0초기화

					int nextEnddir = dirnext[endR][endC];
					endR = endR + vecRow[nextEnddir];
					endC = endC + vecCol[nextEnddir];
				}
				
			}
			//갱신위치가 범위를 넘어감 --> 벽 --> break
			else {
				break;
			}
			
			
			
		}
		System.out.println(totaltime+1);
		
	}

}
