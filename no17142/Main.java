package no17142;
/*
 * 바이러스에 대해서 dfs실시 depth M 개 선택까지
 * 
 * M개가 선택되었다면 활성상태로 만들고 BFS실시
 * 
 * 최소시간 갱신
 * 
 */
import java.io.*;
import java.util.*;

class Loc{
	int r,c;
	Loc(int r, int c){
		this.r = r;
		this.c = c;
		
	}
}

public class Main {

	static int N,M;
	static int map[][];
	static boolean[] activation; //활성가능 불가능 여부
	static boolean[][] visit; // 방문배열
	static Loc[] virus; //바이러스(2)의 위치 저장배열
	static int numTwo; //바이러스의 총 개수
	static int[] vecR = {0,1,0,-1};
	static int[] vecC = {1,0,-1,0};
	static Queue<Loc> q; //이동할때 쓸 q
	static Queue<Integer> time; //이동시간의 q
	static int min = Integer.MAX_VALUE; //최소시간
	static int totalzero; //연구소 내의 바이러스로 물들여야 할 zero공간의 수 
	
	static void dfs(int idx, int depth) {
		
		if(depth==M) {
			visit = new boolean[N][N];
			q = new LinkedList<>();
			time = new LinkedList<>();
			for(int i=0; i<numTwo; i++) {
				
				//바이러스를 둔 곳의 정보를 q에 저장
				if(activation[i]) {
					visit[virus[i].r][virus[i].c] = true;
					q.add(virus[i]);
					time.add(0);
					
				}
				else { //안둔곳은 비활성(3)으로 처리
					map[virus[i].r][virus[i].c] = 3; //비활성 =3
				}
			}
			
			//bfs시행
			bfs(totalzero);
			
			//map원상복귀
			for(int i=0; i<numTwo; i++) {
				if(!activation[i]) map[virus[i].r][virus[i].c] = 2;
			}
			

			return;
			
		}
		
		
		// 첫번째 방법, 오름차순으로 탐색해서 중복 피하기 방법
		for(int i = idx+1; i<numTwo; i++) {
			
			activation[i] = true;
			dfs(i, depth+1);
			activation[i] = false;
			
			//dfs(i, depth);   굳이 선택 안한다고 이렇게 할 필요는 x?
		}
		
		/* 두번째 방법 idx를 하나씩 증가 즉, 바이러스에 대해 선택or미선택을 하나씩 해가면서 가는 방법
		idx++;
		if(idx>=numTwo) return;
		activation[idx] = true;
		dfs(idx, depth+1);
		activation[idx] = false;
		dfs(idx, depth);
		*/
		
	}
	
	static void bfs(int zero) {
		int t = 0; //0으로 들어갈때 시간 하나씩 증가
		int tzero = 0; //bfs를 돌면서 0(빈공간)에 가면 tzero를 하나씩 증가
		
		while(!q.isEmpty()) {
			Loc temp = q.poll();
			int temptime = time.poll();
			
			int nextr,nextc;
			for(int i=0; i<4; i++) {
				nextr = temp.r + vecR[i];
				nextc = temp.c + vecC[i];
				
				if(check(nextr,nextc)) {
					if(map[nextr][nextc]==1) {continue;}
					
					if(!visit[nextr][nextc] && map[nextr][nextc]==0) { //0이라면 진입
						visit[nextr][nextc] = true;
						q.add(new Loc(nextr,nextc));
						time.add(temptime+1);
						t = temptime+1; //시간증가
						tzero++; //전염시킨 zero영역 수 증가
					}
					else if(!visit[nextr][nextc]&& map[nextr][nextc]==3) { //3(비활성)이라면
						visit[nextr][nextc] = true;
						q.add(new Loc(nextr,nextc));  //비활성을 깨우고 q에 넣지만, 시간과 zero영역수는 증가X
						time.add(temptime+1);
					}
				}
				
			}
			
			
			
		}
		// 시간이 기존의 최소시간보다 작고 zero모든 영역을 바이러스로 물들였다면, 시간갱신
		if(min>t && tzero == zero) {min = t;}
	}
	
	static boolean check(int r, int c) {
		if(r>=0 && r<N && c>=0 && c<N) return true;
		return false;
	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st;
		
		st = new StringTokenizer(br.readLine()," ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		map = new int[N][N];
		activation = new boolean[N*N];
		virus = new Loc[N*N];
		
		int temp = 0; int zero = 0;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<N;c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c]==2) {virus[temp] = new Loc(r,c); temp++;}
				if(map[r][c]==0) {zero++;}
			}
		}
		
		totalzero = zero;
		numTwo = temp;
		dfs(-1,0);
		
		if(min == Integer.MAX_VALUE) {System.out.println(-1);}
		else {System.out.println(min);}
		
	}

}
