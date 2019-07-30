package no14503�κ�û�ұ�;

import java.io.*;
import java.util.*;


class Robot{ //�κ��� ��ġ�� �ٶ󺸴� ������ ��Ÿ���� class
	int r,c,v;
	
	Robot(int r, int c, int v){
		this.r = r;
		this.c = c;
		this.v = v;
	}
}

public class solve1 {
	
	
	static int[][] matrix;
	static boolean[][] clean;
	static int row, col;
	
	//���ʹ��⺤��, �� 0���� �ٶ󺸸� �ε���0�� 0���� �ٶ󺼶��� ������ġ�� ��Ÿ��
	static int[] vectorCol = {-1,0,1,0};
	static int[] vectorRow = {0,1,0,-1};
	static Queue<Robot> q;
	
	//�������⺤��, 0���� �ٶ󺼶� �� ���� ���� ����
	static int[] backvecRow = {1,0,-1,0};
	static int[] backvecCol = {0,1,0,-1};
	
	
	
	public static void startclean() {
		
		boolean end = false; //���̻� ������ �� ������ ��Ÿ���� ����
		
		while(!end) {
			Robot temp = q.poll();
			int tempR = temp.r;
			int tempC = temp.c;
			int tempV = temp.v;
			
		
			if(!clean[tempR][tempC]) {clean[tempR][tempC] = true;} //û����
			
			boolean allclean = true; //4������ ��� û�ҵǾ� �ְų� ���̴�
			
			for(int i=0; i<4; i++) {
				int moveR = tempR + vectorRow[tempV];
				int moveC = tempC + vectorCol[tempV];
				
				if(rgck(moveR,moveC) && matrix[moveR][moveC]==0 && !clean[moveR][moveC]) {
					tempV = (tempV+1)%4; //���������� �ٶ󺸴¹��ⰻ��
					q.add(new Robot(moveR,moveC,tempV));
					allclean = false; //4������ ��� û�ҵǾ� �ְų� ���� �ƴϴٷ� ����
					break;
				}
				
				tempV = (tempV+1)%4; //���������� �ٶ󺸴¹��ⰻ��
				
			}
			

			//tempV�� ������ ������ �����ִ�.
			if(allclean) { //4������ ��� û�ҵǾ� �ְų� ���̶�� allclean�� true ,if�� ����
				
				int backR = tempR + backvecRow[tempV];
				int backC = tempC + backvecCol[tempV];
				
				//���̻� û���� ���� ���� �ڰ� ���̴�
				if(matrix[backR][backC] == 1) { 
					end = true;
				}
				else {
					q.add(new Robot(backR,backC,tempV));
				}
				
			}
			
		}
		
	}
	
	
	//����üũ�޼ҵ�
	public static boolean rgck(int r, int c) {
		
		if(r<0 || r>=row || c<0 || c>=col) {return false;}
		else {return true;}
	}
	
	

	public static void main(String[] args)  throws IOException {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		StringTokenizer st = new StringTokenizer(br.readLine()," ");
		
		row = Integer.parseInt(st.nextToken());
		col = Integer.parseInt(st.nextToken());
		
		matrix = new int[row][col];
		clean = new boolean[row][col];
		
		StringTokenizer st1 = new StringTokenizer(br.readLine()," ");
		
		int startR = Integer.parseInt(st1.nextToken());
		int startC = Integer.parseInt(st1.nextToken());
		int startV = Integer.parseInt(st1.nextToken());
		
		for(int i=0; i<row; i++) {
			
			StringTokenizer tempst = new StringTokenizer(br.readLine()," ");
			for(int j=0; j<col; j++) {
				matrix[i][j] = Integer.parseInt(tempst.nextToken());
			}
		}
		
		q = new LinkedList<Robot>();
		
		//���ù��� 3�������� �����̳� ���ʹ������� ȸ���ϴ� �迭�� ���� �����ϱ� ���� 1��3�� ��ġ�� �ٲ㼭 ������
		if(startV==3) {startV=1;}		
		else if(startV==1) {startV=3;}
		
		q.add(new Robot(startR, startC, startV));
		
		startclean();
		
		int count = 0;
		for(int i=0; i<row; i++) {
			for(int j=0; j<col; j++) {
				if(clean[i][j]) {count++;} //û�ҵȰ� ī��Ʈ
			}
		}
		
		System.out.println(count);
	}

}
