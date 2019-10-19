package no5427;
/*
 * 불의 BFS후 상근이의 BFS를 돌린다
 * 이때, 상근이는 불의 타임보다 작은 이동시간일때 해당 칸으로 이동할 수 있다
 * 또한, 범위를 넘어갈 수 있을 경우 탈출 가능이다
 * 
 */

import java.io.*;
import java.util.*;

class Loc{
	int r, c, time;
	Loc(int r, int c, int time){
		this.r = r;
		this.c = c;
		this.time = time;
	}
}
public class Main {

	static int w,h; 
	static char[][] map;
	static boolean[][] visit;
	static Queue<Loc> fireQ;
	static Queue<Loc> sQ;
	static int[][] times;
	static int[] vecR = {0,1,0,-1};
	static int[] vecC = {1,0,-1,0};
	
	static void fireBFS() {
		while(!fireQ.isEmpty()) {
			Loc temp = fireQ.poll();
			
			int r = temp.r;           //BFS할때 기존의 r,c / nextr,nextc 따로 두어라
			int c = temp.c;
			
			int nextr, nextc;
			for(int i=0; i<4; i++) {
				nextr = r+ vecR[i];
				nextc = c+ vecC[i];
				
				
				if(check(nextr, nextc) && !visit[nextr][nextc] && map[nextr][nextc]!='#') {
					visit[nextr][nextc] = true;
					times[nextr][nextc] = temp.time+1;
					fireQ.add(new Loc(nextr,nextc, temp.time+1));
				}
				
			}
		}/*
		for(int r=0; r<h; r++) {
			for(int c=0; c<w; c++) {
				System.out.print(times[r][c] + " ");
			}System.out.println();
		}*/
	}
	
	static void sangBFS() {
		
		while(!sQ.isEmpty()) {
			Loc temp = sQ.poll();
			
			int r = temp.r;
			int c = temp.c;
			//System.out.println(r+" "+c);
			
			int nextr, nextc;
			for(int i=0; i<4; i++) {
				nextr = r+ vecR[i];
				nextc = c+ vecC[i];
				
				if(!check(nextr,nextc)) {System.out.println(temp.time+1); return;}
				
				if(!visit[nextr][nextc] && map[nextr][nextc]!='#' && times[nextr][nextc]>temp.time+1) {
					
					visit[nextr][nextc] = true;
					sQ.add(new Loc(nextr,nextc, temp.time+1));
				}
				
			}
		}
		System.out.println("IMPOSSIBLE");
	}
	
	static boolean check(int r, int c) {
		if(r>=0 && r<h && c>=0 && c<w) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int testcase = Integer.parseInt(br.readLine());
		
		StringTokenizer st;
		for(int tc = 0; tc<testcase; tc++) {
			st = new StringTokenizer(br.readLine()," ");
			
			//w = c h = r
			
			w = Integer.parseInt(st.nextToken());
			h = Integer.parseInt(st.nextToken());
			
			map = new char[h][w];
			visit = new boolean[h][w];
			times = new int[h][w];
			fireQ = new LinkedList<>();
			sQ = new LinkedList<>();
			
			/////불의 초기값을 모두 맥스로 주어야함!!!!!! 왜냐면 불이 하나도 없는 경우나 불이 갈 수 없는 곳이 0으로 되있다면 상근이는 절대 이동 불가!
			/////반례를 찾는 연습 : 범위의 극한, or 있어야할 것이 없을 경우 등
			for(int r=0; r<h; r++) {
				for(int c=0; c<w; c++) {
					times[r][c] = Integer.MAX_VALUE;
				}
			}
			
			for(int r=0; r<h; r++) {
				String t = br.readLine();
				for(int c=0; c<w; c++) {
					char temp = t.charAt(c);
					if(temp=='*') {
						visit[r][c] = true;
						times[r][c] = 0; //불의 처음값은 무조건 0 time
						fireQ.add(new Loc(r,c,0));
					}
					else if(temp=='@') {sQ.add(new Loc(r,c,0));}
					else {
						map[r][c] = temp;
					}
				}
			}
			
			
			fireBFS();
			visit = new boolean[h][w];
			visit[sQ.peek().r][sQ.peek().c] = true;
			sangBFS();
			
			
		}
	}

}
