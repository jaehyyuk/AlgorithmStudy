package no15684;
/*
 * row * col-1 �� ���
 * �� ��Ŀ� ������ �����ϰԴ� x  -> ������ ���� �� �ִ� �� �ֺ� üũ �ʿ�, �̹� ���������̶�� �н� �Ϸķ� �̷���� ��ķ� �ص�?
 * ���� ���ɼ� üũ
 * ���� ���ɼ� üũ ...
 * 
 * 1�� ���� ��� 2�� ���� ��� 3�� ���� ���  -> ��� �ȵǸ� -1
 * dfs0 dfs1 dfs2 dfs3 -> ã���� ��� �� �� 
 * 
 * ���ɼ�üũ?
 * 
 * �迭�̿�
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
				if(N-1==1) { //1��¥���� ���, putline�迭�� 1��¥�� �϶�
					way[c][r] = c+1; way[c+1][r] = c; putline[r][c]=true;
					dfs2(i, depth+1);
					way[c][r] = 0; way[c+1][r] = 0; putline[r][c] = false;
				}
				else { //2�� �̻��� ���
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
	static void check(int line,int r, int c) { //��������� way�迭�� �̿��� ������ ���� ã�ư���. 
		
		if(c==H) {lines[line] = r; return;} //������ ���ڶ����� ���� ��� lines�迭�� � �������� ����
		int tc = c+1; //c�� ���� ���κ��� Ž��
		while(tc<=H && lines[line]==0) { //lines[line]�� 0�̶�� �̹� ���Ѱ��ν� �� �ʿ� x, �̷��� dfs���� Ư������ ���ϰ� ���̻� �� �ʿ� ������ while��� or ������ ����,break���
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
