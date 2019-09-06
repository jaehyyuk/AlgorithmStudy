package no16236�Ʊ���;

/*
 * 
 * 
 * 
 * 
 * 
 * 
 */






import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.LinkedList;
import java.util.Queue;
import java.util.ArrayList;

class Loc{
	int r,c,t;
	Loc(int r, int c, int t){
		this.r = r; this.c = c; this.t = t;
	}
	
}

public class Main {

	static int mapsize;
	static int[][] map;
	static boolean[][] visit;
	static ArrayList<Loc> fish; //���� layer�ȿ� ���� �� �ִ� ����Ⱑ ���������� ���⿡ �ش� ��ǥ�� ����, �Ŀ� �켱���� ����⸦ Ž����
	static Queue<Loc> q;
	static int[] vecC = {1,0, -1, 0};
	static int[] vecR = {0,1,0,-1};
	static int sharksize; //���ũ��
	static int eating; //�� ���� ����� ��
	
	
	public static void bfs(){
		
		boolean find = false; //���� �� �ִ� ����⸦ ã�Ҵٸ� true
		
		while(!q.isEmpty()) {
			if(find) {return;} //�����ã��! bfs�� ������
			
			int qsize = q.size(); //���� layer���� (���� �ð��ȿ�) ���� �� �ִ� ����Ⱑ �������� �� �� �ֱ� ������
								  //ť�� �����ŭ ������ ��, �� ���� time�� Ž���� ��� ť ���� �ڷ�� ���� �Ѵٴ� ��.
			
			for(int rotate_samelayer=0; rotate_samelayer<qsize; rotate_samelayer++) {
				
				Loc temp = q.poll();
				int nowrow = temp.r;
				int nowcol = temp.c;
				int nowtime = temp.t;
				
				int nextrow, nextcol;
				
				for(int i=0; i<4; i++) {
					
					nextrow = nowrow + vecR[i];
					nextcol = nowcol + vecC[i];
					
					//������ ���� �ʰ� �湮�� ���� �ʾҴٸ�
					if(rgck(nextrow, nextcol) && !visit[nextrow][nextcol]) {
						//�� ����(0) �̰ų� ����� ���� ������� �׳� ������ ����
						if(map[nextrow][nextcol]==0 || map[nextrow][nextcol]==sharksize) {
							q.add(new Loc(nextrow, nextcol, nowtime+1));
							visit[nextrow][nextcol] = true;
						}
						//���� ����� �߰�! �Դ´�!
						else if(map[nextrow][nextcol]<sharksize) {
							fish.add(new Loc(nextrow,nextcol,nowtime+1));//fish��̿� �־��ش�
							find=true; //���̸� ã���� ǥ��
						}
		
					}
						
				}
	
			}

		}
		
	}
	
	public static boolean rgck(int r, int c) {
		if(r>=0 && r<mapsize && c>=0 && c<mapsize) {return true;}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		mapsize = Integer.parseInt(br.readLine());
		
		map = new int[mapsize][mapsize];
		visit = new boolean[mapsize][mapsize];
		
		q = new LinkedList<>();
		fish = new ArrayList<>();
		
		for(int r=0; r<mapsize ; r++) {
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			for(int c=0; c<mapsize; c++) {
				map[r][c] = Integer.parseInt(st.nextToken());
				if(map[r][c] == 9) {
					q.add(new Loc(r,c,0));
					map[r][c]=0; //�߿�! 9�� ��ġ�̹Ƿ� �ʱⰪ�� 0���� ����� �����;;
				}
			}
		}
		
		sharksize = 2;
		eating = 0;
		
		int time=0; //�ɸ� �ð� ����
		
		//�ݺ����� ���� ���̻� ���̸� ã�� ���Ҷ����� �ݺ����ش�
		while(true) {

			visit = new boolean[mapsize][mapsize];
			bfs();
			
			if(fish.size()==0) { //fish��̿� ���� ���� ��� ���̸� �� ã�� ����
				System.out.println(time); break;
			}	
			
			//���� ������ ��ġ�� ����ϴ� ����
			int startrow, startcol;
			startrow = fish.get(0).r;
			startcol = fish.get(0).c;
			time = fish.get(0).t;
			
			//fish�� 2�� �̻��̶�� �켱������ ���̸� ã�´�
			for(int i=1; i<fish.size(); i++) {
				Loc temp = fish.get(i);
				if(startrow>temp.r) {startrow = temp.r; startcol = temp.c;}
				else if(startrow == temp.r) {
					if(startcol>temp.c) {startrow = temp.r; startcol = temp.c;}
				}
			}
			
			//bfs�� �ٽ� ������ ���� ������ �ʱ�ȭ ���ش�.
			q = new LinkedList<>();
			fish = new ArrayList<>();
			map[startrow][startcol] = 0;
			
			//���� ���̸� �԰� ������ ��ġ�� �־��ش�
			q.add(new Loc(startrow,startcol,time));
			
			//���̸� �԰� �������� ��������׷��̵� ����
			eating++;
			if(sharksize==eating) {
				sharksize++;
				eating=0;
			}
			
		}
		
		
		
		
	}

}
