package no17142;
/*
 * ���̷����� ���ؼ� dfs�ǽ� depth M �� ���ñ���
 * 
 * M���� ���õǾ��ٸ� Ȱ�����·� ����� BFS�ǽ�
 * 
 * �ּҽð� ����
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
	static boolean[] activation; //Ȱ������ �Ұ��� ����
	static boolean[][] visit; // �湮�迭
	static Loc[] virus; //���̷���(2)�� ��ġ ����迭
	static int numTwo; //���̷����� �� ����
	static int[] vecR = {0,1,0,-1};
	static int[] vecC = {1,0,-1,0};
	static Queue<Loc> q; //�̵��Ҷ� �� q
	static Queue<Integer> time; //�̵��ð��� q
	static int min = Integer.MAX_VALUE; //�ּҽð�
	static int totalzero; //������ ���� ���̷����� ���鿩�� �� zero������ �� 
	
	static void dfs(int idx, int depth) {
		
		if(depth==M) {
			visit = new boolean[N][N];
			q = new LinkedList<>();
			time = new LinkedList<>();
			for(int i=0; i<numTwo; i++) {
				
				//���̷����� �� ���� ������ q�� ����
				if(activation[i]) {
					visit[virus[i].r][virus[i].c] = true;
					q.add(virus[i]);
					time.add(0);
					
				}
				else { //�ȵа��� ��Ȱ��(3)���� ó��
					map[virus[i].r][virus[i].c] = 3; //��Ȱ�� =3
				}
			}
			
			//bfs����
			bfs(totalzero);
			
			//map���󺹱�
			for(int i=0; i<numTwo; i++) {
				if(!activation[i]) map[virus[i].r][virus[i].c] = 2;
			}
			

			return;
			
		}
		
		
		// ù��° ���, ������������ Ž���ؼ� �ߺ� ���ϱ� ���
		for(int i = idx+1; i<numTwo; i++) {
			
			activation[i] = true;
			dfs(i, depth+1);
			activation[i] = false;
			
			//dfs(i, depth);   ���� ���� ���Ѵٰ� �̷��� �� �ʿ�� x?
		}
		
		/* �ι�° ��� idx�� �ϳ��� ���� ��, ���̷����� ���� ����or�̼����� �ϳ��� �ذ��鼭 ���� ���
		idx++;
		if(idx>=numTwo) return;
		activation[idx] = true;
		dfs(idx, depth+1);
		activation[idx] = false;
		dfs(idx, depth);
		*/
		
	}
	
	static void bfs(int zero) {
		int t = 0; //0���� ���� �ð� �ϳ��� ����
		int tzero = 0; //bfs�� ���鼭 0(�����)�� ���� tzero�� �ϳ��� ����
		
		while(!q.isEmpty()) {
			Loc temp = q.poll();
			int temptime = time.poll();
			
			int nextr,nextc;
			for(int i=0; i<4; i++) {
				nextr = temp.r + vecR[i];
				nextc = temp.c + vecC[i];
				
				if(check(nextr,nextc)) {
					if(map[nextr][nextc]==1) {continue;}
					
					if(!visit[nextr][nextc] && map[nextr][nextc]==0) { //0�̶�� ����
						visit[nextr][nextc] = true;
						q.add(new Loc(nextr,nextc));
						time.add(temptime+1);
						t = temptime+1; //�ð�����
						tzero++; //������Ų zero���� �� ����
					}
					else if(!visit[nextr][nextc]&& map[nextr][nextc]==3) { //3(��Ȱ��)�̶��
						visit[nextr][nextc] = true;
						q.add(new Loc(nextr,nextc));  //��Ȱ���� ����� q�� ������, �ð��� zero�������� ����X
						time.add(temptime+1);
					}
				}
				
			}
			
			
			
		}
		// �ð��� ������ �ּҽð����� �۰� zero��� ������ ���̷����� ���鿴�ٸ�, �ð�����
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
