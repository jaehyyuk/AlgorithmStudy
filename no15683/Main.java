package no15683;
/*
 * 1. ī�޶��� ��ġ��, ������ �迭�� ��´�
 * 2. �迭�� �� �ε����� depth�� �����ϰ� DFS�� �����Ѵ�
 * 3. �� �������� ������ ������ ��� �ϵ��� ���ش�
 * 4. �̶�, ��� ���ٰ� ������ ���� ���󺹱� ������� �Ѵ�.
 *  4-1. ���� ���ñ����� �δ� �Լ���
 *  4-2. ���ö� ���ñ����� ���½�Ű�� �Լ��� �ʿ�
 *  4-3. �� �Լ��� r,c,������������Ʈ�� �޴´� -> r,c���� ������������ ���� 0�ۿ� ���ٸ� 0���� �� 0,1 �̶�� 0,1�������� ��
 * 5. ��depth�� �������� �����ִ� 0�� ���ش�.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

class Info{
		
		int r,c,cctv;
		Info(int r, int c, int cctv){
			this.r = r;
			this.c = c;
			this.cctv = cctv;
		}
	}


public class Main {
	
	
	
	static int N,M; //N=r, M=c
	static Info[] cctv;
	static int[][] map;
	static int[] vecR = {0,-1,0,1};  // 0 : ������, 1 : ���� , 2 : ���� , 3 : �Ʒ���
	static int[] vecC = {1,0,-1,0};
	static int min = Integer.MAX_VALUE;
	static int totaldepth;
	
	static boolean rgck(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<M) return true;
		return false;
	}
	
	static void putSight(int r, int c, int[] dir, int idx) {
		
		for(int i=0; i<dir.length; i++) {
			int nextr = r;
			int nextc = c;
			while(true) {
				nextr = nextr+vecR[dir[i]];
				nextc = nextc+vecC[dir[i]];
				
				if(rgck(nextr,nextc)) {
					if(map[nextr][nextc]==6) {break;}
					if(map[nextr][nextc]==0) {map[nextr][nextc]=idx+7;}
				}
				else {break;}
				
				
			}
		}
		
	}
	static void delSight(int r, int c, int[] dir, int idx) {
		
		for(int i=0; i<dir.length; i++) {
			int nextr = r;
			int nextc = c;
			while(true) {
				nextr = nextr+vecR[dir[i]];
				nextc = nextc+vecC[dir[i]];
				
				if(rgck(nextr,nextc)) {
					if(map[nextr][nextc]==6) {break;}
					if(map[nextr][nextc]==(idx+7)) {map[nextr][nextc]=0;}
				}
				else {break;}
				
				
			}
		}
		
	}
	
	static void dfs(int idx) {
		
		if(idx==totaldepth) { 
			/*System.out.println("---------------");
			for(int r=0; r<N; r++) {
				
				for(int c=0; c<M; c++) {
					System.out.print(map[r][c] +" ");
				}System.out.println();
			}
			System.out.println("---------------");*/
			getInvisible(); return;}
		
		Info temp = cctv[idx];
		int r,c,cctv;
		
		r = temp.r;
		c = temp.c;
		cctv = temp.cctv;
		
		if(cctv==1) {
			int[] temp_dir = new int[1];
			
			temp_dir[0] = 0;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 1;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 2;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
		}
		else if(cctv==2) {
			int[] temp_dir = new int[2];
			
			temp_dir[0] = 0; temp_dir[1] = 2;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 1; temp_dir[1] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
		}
		else if(cctv==3) {
			int[] temp_dir = new int[2];
			
			temp_dir[0] = 0; temp_dir[1] = 1;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 1; temp_dir[1] = 2;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 2; temp_dir[1] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 3; temp_dir[1] = 0;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			
		}
		else if(cctv==4) {
			int[] temp_dir = new int[3];
			
			temp_dir[0] = 0; temp_dir[1] = 1; temp_dir[2] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 0; temp_dir[1] = 1; temp_dir[2] = 2;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 1; temp_dir[1] = 2; temp_dir[2] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
			
			temp_dir[0] = 0; temp_dir[1] = 2; temp_dir[2] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
		}
		else {
			int temp_dir[] = new int[4];
			
			temp_dir[0] = 0; temp_dir[1] = 1; temp_dir[2] = 2; temp_dir[3] = 3;
			putSight(r,c,temp_dir,idx);
			dfs(idx+1);
			delSight(r,c,temp_dir,idx);
		}
	}
	
	static void getInvisible() {
		int tempcount = 0;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				if(map[r][c] == 0) {
					tempcount++;
				}
			}
		}
		
		if(min>tempcount) {min=tempcount;}
	}

	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		cctv = new Info[8];
		
		int cnt = 0;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine());
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] >=1 && map[r][c] <=5) {
					cctv[cnt] = new Info(r,c,map[r][c]); cnt++;
				}
			}
		}
		
		totaldepth = cnt;
		
		dfs(0);
		System.out.println(min);
	}

}
