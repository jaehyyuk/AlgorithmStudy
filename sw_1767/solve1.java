package sw_1767;

import java.io.*;
import java.util.*;

/*
 * DFS + ��Ʈ��ŷ ����
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
	
	static int[][] map; //��
	static Loc[] core; //1�� ��ġ�� �����ϴ� �迭
	static int[] dir; //���� 0,1,2,3�� ���� ��������
	static int cores; //cores�� �� = �迭�� ���μ���
	static int[] vr = {0,-1,0,1}; //���⺤��
	static int[] vc = {1,0,-1,0};
	static int shortpath;   //�ּҰŸ�
	static int[] corecounts;//������ �ھ�� �� �ּҰŸ��� �ִ� �迭 ** �߿�!
	
	static void dfs(int layer, int corecount) {
		
		//layer�� cores�� ���ٸ� core�迭�� ��� �ھ Ȯ���ϰ� �Ѿ ����
		//�̶�, �ھ��� ���� �������� �迭�� ����Ѵ�.
		if(layer==cores) {
			if(corecounts[corecount]>shortpath) {
				corecounts[corecount]=shortpath;
			}
		}
		
		else {
			
			int r = core[layer].r;
			int c = core[layer].c;
			
			//�����ڸ��ΰ�?
			if(isedge(r,c)) {
				//�ھ ���������� ������ ���ؼ��� �ƹ� �ൿ�� ���� �ʴ´�
				//������ �ھ�� ���� �߱⿡ �ھ�ī��Ʈ++
				dfs(layer+1,corecount+1);
			}
			//�����ڸ��� �ƴ϶��
			else {
				//4������ ��� ���µ�
				for(int dir=0; dir<4; dir++) {
					//������ ���� �� �ִ� ��ġ�ΰ�?
					if(ispath(r,c,dir)) {
						putpath(r,c,dir); //������ ���´�
						dfs(layer+1,corecount+1); //dfs���
						delpath(r,c,dir); //������ öȸ�Ѵ�
					}
					//������ ���� �� ����?
					else {
						//�ھ ������ �� ���⿡ �ھ�ī��Ʈ�� �������� �ʴ´�
						dfs(layer+1,corecount);
					}
				}
			}
		}
		
		
	}
	
	//������ ���� �Լ�, dir�� ���⿡ ���� ������ ���´�
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
	//������ öȸ�ϴ� �Լ�, dir�� ���⿡ ���� öȸ�Ѵ�.
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
	
	//1,2,3,4 �������� �����ϴ� ���߿� �ɸ��� 1�� ������ f, ������ t
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
	//����üũ
	static boolean rgck(int r, int c) {
		if(r>=0 && r<cores && c>=0 && c<cores) {
			return true;
		}
		return false;
	}
	//�����ڸ� �ΰ�?
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
			
			//�ھ� �� �� �ּ��� ����
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
