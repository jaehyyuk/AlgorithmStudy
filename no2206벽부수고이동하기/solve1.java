package no2206���μ����̵��ϱ�;

import java.util.Queue;
import java.util.LinkedList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;


class Position{
	int row,col,count,which; // ��,��,�̵�Ƚ��, which = ���� �μ��� ������(0) ������(1) ��Ÿ���� ���� 
	
	Position(int row, int col, int count, int which) {
		this.row = row;
		this.col = col;
		this.count = count;
		this.which = which;
	}
}


public class solve1 {

	static int row, col;
	static boolean[][][] visit;
	static int[][] map;
	static Queue<Position> q;
	static int[] vectorRow = {0,-1,0,1};
	static int[] vectorCol = {1,0,-1,0};

	static void bfs() {
		
		while(!q.isEmpty()) {
			
			Position temp = q.poll();
			
			int nowrow = temp.row;
			int nowcol = temp.col;
			int nowcount = temp.count;
			int nowwhich = temp.which;
			
			//������ ������ ��� �� �޼ҵ� ����
			if(nowrow==row-1 && nowcol==col-1) {
				System.out.println(nowcount); return;
			}
			
			//4����Ž��
			int nextrow, nextcol;
			for(int i=0; i<4; i++) {
				
				nextrow = nowrow + vectorRow[i];
				nextcol = nowcol + vectorCol[i];
				
				if(rgck(nextrow, nextcol)) {
					
					//Case 1 : ����ĭ�� ���� �ƴϸ�, �ش�ĭ�� �湮���� �ʾ�����, �̶�, nowwhich������ ���� ���� �μ��� ������(0) ������(1) �� �� ����
					if(map[nextrow][nextcol]==0 && !visit[nextrow][nextcol][nowwhich]) {
						
						q.add(new Position(nextrow,nextcol,nowcount+1,nowwhich)); //����ĭ Q�� �ֱ�
						visit[nextrow][nextcol][nowwhich]=true;  //����ĭ �湮 true
						
						if(nowwhich==0) {  //�̶�, ����ĭ�� ���� ���� �μ��� ��(0)�̶�� ���� �μ����� �湮�迭�� true�� ������ ���� �μ� �� ����� �湮�迭���� �Դ����� �ٽ� ���� �ʴ´�
							visit[nextrow][nextcol][nowwhich+1]=true;
						}
						
					}
					//Case 2 : ����ĭ�� ���̶�� 
					else if(map[nextrow][nextcol]==1) {
						//���ε�, ���� �μ��� ���̶�� �μ��� ����
						if(nowwhich == 0) {
							q.add(new Position(nextrow, nextcol, nowcount+1, nowwhich+1)); //nowwhich=1(�����μ���)�� ����
							visit[nextrow][nextcol][nowwhich+1]=true;
						}
					}
				}
			}
			
			
		}
		
		//�Ұ����� ���
		System.out.println(-1);
		
		
		
	}
	
	//����üũ
	static boolean rgck(int r, int c) {
		if(r>=0 && r<row && c>=0 && c<col) {
			return true;
		}
		return false;
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] headline = br.readLine().split(" ");
		
		row = Integer.parseInt(headline[0]);
		col = Integer.parseInt(headline[1]);
		
		visit = new boolean[row][col][2];
		map = new int[row][col];
		
		for(int r=0; r<row; r++) {
			String inner = br.readLine();
			
			for(int c=0; c<col; c++) {
				map[r][c] = inner.charAt(c) - '0';
			}
		}
		
		q = new LinkedList<>();
		
		q.add(new Position(0,0,1,0));
		visit[0][0][0] = true;
		visit[0][0][1] = true;
		bfs();
		
	}

}
