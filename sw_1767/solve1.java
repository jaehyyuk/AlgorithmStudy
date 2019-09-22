package sw_1767;

import java.io.*;
import java.util.*;

/*
 * DFS + 백트래킹 문제
 * 
 * 
 * 
 * 
 * 
 * 
 */

class Loc{
	int r,c;
	Loc(int r, int c){
		this.r = r;
		this.c = c;
	}
}

public class solve1 {
	
	static int[][] map; //맵
	static Loc[] core; //1의 위치를 저장하는 배열
	static int[] dir; //방향 0,1,2,3에 따라 동서남북
	static int cores; //cores의 수 = 배열의 가로세로
	static int[] vr = {0,-1,0,1}; //방향벡터
	static int[] vc = {1,0,-1,0};
	static int shortpath;   //최소거리
	static int[] corecounts;//연결한 코어수 별 최소거리를 넣는 배열 ** 중요!
	
	static void dfs(int layer, int corecount) {
		
		//layer가 cores와 같다면 core배열의 모든 코어를 확인하고 넘어간 상태
		//이때, 코어의 수와 전선수를 배열에 기록한다.
		if(layer==cores) {
			if(corecounts[corecount]>shortpath) {
				corecounts[corecount]=shortpath;
			}
		}
		
		else {
			
			int r = core[layer].r;
			int c = core[layer].c;
			
			//가장자리인가?
			if(isedge(r,c)) {
				//코어를 연결하지만 전선에 관해서는 아무 행동도 하지 않는다
				//하지만 코어는 연결 했기에 코어카운트++
				dfs(layer+1,corecount+1);
			}
			//가장자리가 아니라면
			else {
				//4방향을 모두 도는데
				for(int dir=0; dir<4; dir++) {
					//전선을 놓을 수 있는 위치인가?
					if(ispath(r,c,dir)) {
						putpath(r,c,dir); //전선을 놓는다
						dfs(layer+1,corecount+1); //dfs재귀
						delpath(r,c,dir); //전선을 철회한다
					}
					//전선을 놓을 수 없나?
					else {
						//코어를 연결할 수 없기에 코어카운트를 더해주지 않는다
						dfs(layer+1,corecount);
					}
				}
			}
		}
		
		
	}
	
	//전선을 놓는 함수, dir의 방향에 따라 전선을 놓는다
	static void putpath(int r, int c, int dir) {
		
		int nextr = r; int nextc = c;
		while(true) {
			nextr += vr[dir]; nextc += vc[dir];
			
			if(rgck(nextr, nextc)) {
				map[nextr][nextc]=1;
				shortpath++;
			}
			else {
				return;
			}
		}
		
	}
	//전선을 철회하는 함수, dir의 방향에 따라 철회한다.
	static void delpath(int r, int c, int dir) {
		
		int nextr = r; int nextc = c;
		while(true) {
			nextr += vr[dir]; nextc += vc[dir];
			
			if(rgck(nextr, nextc)) {
				map[nextr][nextc]=0;
				shortpath--;
			}
			else {
				return;
			}
		}
		
	}
	
	//1,2,3,4 방향으로 진행하는 와중에 걸리는 1이 있으면 f, 없으면 t
	static boolean ispath(int r, int c, int dir) {

		int nextr = r; int nextc = c;
		
		while(true) {
			nextr += vr[dir];
			nextc += vc[dir];
			
			if(rgck(nextr,nextc)) {
				if(map[nextr][nextc]==1) {
					return false;
				}
			}
			else {
				return true;
			}
			
		}
		
		
	}
	//범위체크
	static boolean rgck(int r, int c) {
		if(r>=0 && r<cores && c>=0 && c<cores) {
			return true;
		}
		return false;
	}
	//가장자리 인가?
	static boolean isedge(int r, int c) {
		if(r==0 || r==(cores-1) || c==0 || c==(cores-1)) {
			return true;
		}
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int tc = Integer.parseInt(br.readLine());
		
		for(int test = 0; test<tc; test++) {
			shortpath=0; 
			cores = Integer.parseInt(br.readLine());
			
			//코어 수 별 최소의 전선
			corecounts = new int[cores+1];
			for(int i=0; i<=cores; i++) {
				corecounts[i] = 1000;
			}
			
			map = new int[cores][cores];
			core = new Loc[cores];
			dir = new int[cores];
			
			int corcount=0;
			for(int r=0; r<cores; r++) {
				
				StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
				
				for(int c=0; c<cores; c++) {
					map[r][c] = Integer.parseInt(st1.nextToken());
					if(map[r][c] == 1) {
						core[corcount] = new Loc(r,c);
						corcount++;
					}
				}
			}
			
	
			dfs(0,0);

			
			for(int i=cores; i>0; i--) {
				if(corecounts[i]<1000) {
					System.out.println("#"+(test+1)+" "+corecounts[i]); break;
				}
			}
			
			
		}

	}

}
