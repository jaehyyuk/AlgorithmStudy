package no16236;
/*
 * �ʱ� �Ʊ��� ũ�� 2
 * ���� �ڽ��� ũ�⺸�� ���� ����⸸ ����
 * ũ�Ⱑ ������ ������ �� ����
 * �ڽ��� ũ��� ���� ������� ���� ���� �� ũ�� 1 ����
 * 
 * -> ����� ũ�⺯��
 * -> �� ���� ����� �� ����
 * 
 * map����
 * 
 * 9: �Ʊ���
 * 
 * �԰��� ����� ��ĭ
 * 
 * ���� ����� ����� ���� ����������� ����, ������ ���� �켱���� (0,0)�� �����͵�
 * 
 * BFS�� ����� ������ Ž�� -> ArrayList�� �ֱ�
 * ArrayList�� ���ϸ鼭 ���� 00�� ����� �ָ� �Ա�  --> ��� ũ�� �� ���� ����� �� ����
 * �ش� �������� �̵� �� �ٽ� BFS������ 
 * 
 * 
 */
import java.io.*;
import java.util.*;

class Loc{
	int r, c,t;
	Loc(int r, int c, int t){
		this.r = r;
		this.c = c;
		this.t = t;
	}
}
public class Main {
	
	static int N;
	static int sharksize;
	static int eaten;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<Loc> fishslist;
    static Queue<Loc> q;
    static int vecR[] = {0,1,0,-1};
    static int vecC[] = {1,0,-1,0};
    static Loc shark;
    
    static boolean bfs() {
    	
    	boolean flag = false;
    	
		while (!q.isEmpty()) {
			int size = q.size();

			
			for (int s = 0; s < size; s++) {
				Loc temp = q.poll();
				int r, c,t;
				r = temp.r;
				c = temp.c;
				t = temp.t;
				int nextr, nextc;

				for (int i = 0; i < 4; i++) {
					nextr = r + vecR[i];
					nextc = c + vecC[i];

					if (nextr >= 0 && nextr < N && nextc >= 0 && nextc < N) {
						if (!visit[nextr][nextc]) {
							
							if (map[nextr][nextc] == sharksize || map[nextr][nextc] == 0) {
								q.add(new Loc(nextr, nextc, t+1));
								visit[nextr][nextc] = true;
							}else if (map[nextr][nextc] < sharksize) {
								fishslist.add(new Loc(nextr, nextc, t+1));
								flag = true;}
						}
						
					}
				}

			}
			
			if(flag) {break;}

		}
		
		return flag;
    }
    
    static void eating() {
    	Loc t = fishslist.get(0);
    	
    	for(int i=1; i<fishslist.size(); i++) {
    		Loc compare = fishslist.get(i);
    		if(t.r> compare.r) {t = compare;}
    		else if(t.r == compare.r) {
    			if(t.c > compare.c) {t=compare;}
    		}
    	}
    	
    	shark = t;
    	eaten++;
    	map[t.r][t.c] = 0;
    	if(eaten==sharksize) {sharksize++; eaten=0;}
    }
	
	public static void main(String[] args) throws IOException{
		// TODO Auto-generated method stub
		BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		visit = new boolean[N][N];
		q=new LinkedList<>();
		fishslist = new ArrayList<>();
		
		StringTokenizer st;
		for(int r=0; r<N; r++) {
			st = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<N; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {q.add(new Loc(r,c,0)); map[r][c] = 0; visit[r][c] = true; shark = new Loc(r,c,0);}
			}
		}
		
		sharksize = 2;
		eaten = 0;
		
		//boolean flag = true;
		while(bfs()) {
			eating();
			q = new LinkedList<>();
			q.add(shark);
			visit = new boolean[N][N];
			visit[shark.r][shark.c] = true;
			fishslist = new ArrayList<>();
		}
		
		System.out.println(shark.t);
		
	}

}
