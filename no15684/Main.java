package no15684;
/*
 * row * col-1 의 행렬
 * 각 행렬에 놓을때 인접하게는 x  -> 놓을때 놓을 수 있는 지 주변 체크 필요, 이미 놓아진것이라면 패스 일렬로 이루어진 행렬로 해도?
 * 놓고 가능성 체크
 * 놓고 가능성 체크 ...
 * 
 * 1개 놓는 경우 2개 놓는 경우 3개 놓는 경우  -> 모두 안되면 -1
 * dfs0 dfs1 dfs2 dfs3 -> 찾으면 출력 후 끝 
 * 
 * 가능성체크?
 * 
 * 배열이용
 * 
 * 
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

	static int N,M,H;
	
	static int[][] way;
	static boolean[][] putline; 
	static int putlineIdx;
	static int[] capa_line;
	static boolean possible;
	static boolean totalpossible;
	static int[] lines;
	static int wannadepth;
	
	
	static void dfs2(int idx, int depth) {
		if(depth==wannadepth) { checkfirst();  return;}
		
		for(int i=idx+1; i<=putlineIdx; i++) {
			int r;
			int c = i%(N-1);
			if(c==0) {r = i/(N-1); c=N-1;}
			else {r=i/(N-1)+1;}
			
			if(putline[r][c]) {continue;}
			
			if(c==1) {
				if(N-1==1) { //1줄짜리의 경우, putline배열이 1줄짜리 일때
					way[c][r] = c+1; way[c+1][r] = c; putline[r][c]=true;
					dfs2(i, depth+1);
					way[c][r] = 0; way[c+1][r] = 0; putline[r][c] = false;
				}
				else { //2줄 이상의 경우
					if(!putline[r][c+1]) {
						way[c][r] = c+1; way[c+1][r] = c; putline[r][c] = true;
						dfs2(i, depth+1);
						way[c][r] = 0; way[c+1][r] = 0; putline[r][c] = false;
					}
				}
			}
			else if(c == N-1) { 
				if(!putline[r][c-1]) {
					way[c][r] = c+1; way[c+1][r] = c; putline[r][c] = true;
					dfs2(i, depth+1);
					way[c][r] = 0; way[c+1][r] = 0; putline[r][c] = false;
				}
			}
			else {
				if(!putline[r][c-1] && !putline[r][c+1] && capa_line[c]+1<=H) {
					way[c][r] = c+1; way[c+1][r] = c; putline[r][c] = true;
					dfs2(i, depth+1);
					way[c][r] = 0; way[c+1][r] = 0; putline[r][c] = false;
					
				}
			}
			
			
		}
		
	}
	
	
	static void checkfirst() {
		lines = new int[N+1];
		for(int j=1; j<=N; j++) {
			check(j,j,0);
			if(lines[j] != j) { return;}
		}
		totalpossible = true;
	}
	
	//start =line = r, c=0
	static void check(int line,int r, int c) { //재귀적으로 way배열을 이용해 라인의 끝을 찾아간다. 
		
		if(c==H) {lines[line] = r; return;} //라인의 끝자락까지 왔을 경우 lines배열에 어떤 라인인지 저장
		int tc = c+1; //c의 다음 라인부터 탐색
		while(tc<=H && lines[line]==0) { //lines[line]가 0이라면 이미 구한경우로써 할 필요 x, 이렇게 dfs에서 특정값을 구하고 더이상 할 필요 없을때 while사용 or 포문에 이프,break사용
			if(way[r][tc]>0) {check(line, way[r][tc],tc);}
			else if(tc==H) {check(line,r,tc);}
			tc++;
		}

	}
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());
		
		way = new int[N+1][H+1];
		putline = new boolean[H+1][N];
		capa_line = new int[N];
		
		putlineIdx = H*(N-1);
		int a,b;
		for(int i=0; i<M; i++) {
			st = new StringTokenizer(br.readLine()," ");
			a=Integer.parseInt(st.nextToken());
			b=Integer.parseInt(st.nextToken());
			
			way[b][a] = b+1;
			way[b+1][a] = b;
			putline[a][b] = true;
			capa_line[b]++;
		}
		
		totalpossible = false;
		
		checkfirst();
		if(totalpossible) {System.out.println(0); return;}
		
		wannadepth = 1;
		dfs2(0,0);
		if(totalpossible) {System.out.println(1); return;}
		
		wannadepth = 2;
		dfs2(0,0);
		if(totalpossible) {System.out.println(2); return;}
		
		wannadepth = 3;
		dfs2(0,0);
		if(totalpossible) {System.out.println(3); return;}

		System.out.println(-1);
		
		
		
		
	}

}
