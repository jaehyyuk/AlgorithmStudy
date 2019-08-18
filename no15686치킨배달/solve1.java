package no15686ġŲ���;

/*
 * dfs(��Ʈ��ŷ)�� ����Ž�� Ȱ�� ����
 * � ������ �������� ���� �����ϴ� ������ �־� dfs�� �ذ��� �����ϴ� (�� ���̰� ũ�� ������)
 * �� ��� ��Ʈ��ŷ�� ���� ���ϴ� ������ ��� Ž���� �� �ֵ��� �ؾ��Ѵ� (�Է¹��� M�� �����ϴ� ��� ���)
 * ġŲ�Ÿ��� ���ϴ� ���� �� ������ �� ġŲ������ ��� �Ÿ����� ���ϰ� ���ؼ� �ּҰ��� ã�´�(����Ž��)
 * ó������ bfs�� ����ϳ� ������ ���� �׷��ʿ� ���� for������ ���ϸ� �ȴ�
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

class Location{
	
	int row, col;
	Location(int r, int c){
		this.row = r; this.col = c;
	}
}

public class solve1 {
	
	static int m;					//�Է� ���� m
	static Location[] chickenLoc;	//ġŲ���� ��ġ���� ������ Location �迭
	static Location[] house;		//������ ��ġ���� ������ Location �迭	
	static boolean[] chickenSel;	//ġŲ���� ���� �����迭
	static int housecount, chickencount;	//���� ��, ġŲ���� ��
	static int chickenDist = Integer.MAX_VALUE;	//ġŲ�Ÿ����� ����
	
	
	static void dfs(int layer, int select) {
		//��Ʈ��尡 -1����(layer)���� ����,  0���� �϶��� ù��° ġŲ���� �����ϴ��� �������� ���
		
		if(select == m) {
			
			
			int dist=0; //�ش� dfs����� ���, ġŲ�Ÿ� ����
			
			for(int i=0; i<housecount; i++) {
				
				int tempdist = Integer.MAX_VALUE; // ������ ������ �� ġŲ�������� �Ÿ�����
				for(int j=0;  j<chickencount; j++) {
					
					if(chickenSel[j]) { //������ ġŲ���� ���
						tempdist = Math.min(tempdist, distance(house[i], chickenLoc[j]));
					}
					
				}
				dist += tempdist;
			}
			
			//������ ġŲ�Ÿ����� �����Ǹ� ����
			if(chickenDist>dist) {chickenDist = dist;}
			
			return;
		}
		
		//dfs���� ����
		layer++;
		if(layer >= chickencount) {return;} //Ʈ���� ���̰� ������ �� �ִ� ġŲ���� ������ �������� ��� �Ұ����� �����̹Ƿ� ��ŵ
		
		//Case 1 : �ش� ġŲ���� ����д�
		chickenSel[layer] = true;
		dfs(layer, select+1);
		chickenSel[layer] = false; //�����鼭 Select����
		
		//Case 2 : �ش� ġŲ���� ���ش�.
		dfs(layer, select);
		
		
	}
	
	//�Ÿ���� �޼ҵ�
	static int distance(Location a, Location b) {
		return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		String[] inputone = br.readLine().split(" ");
		
		int n = Integer.parseInt(inputone[0]);
		m = Integer.parseInt(inputone[1]);
		
		chickenLoc = new Location[13];
		chickenSel = new boolean[13];
		house = new Location[2*n];
		housecount = 0; chickencount = 0;
		
		for(int r=0; r<n; r++) {
			
			String[] inputtwo = br.readLine().split(" ");
			
			for(int c=0; c<n; c++) {
				int temp = Integer.parseInt(inputtwo[c]);
				if(temp == 1) {
					house[housecount] = new Location(r,c);
					housecount++;
				}
				else if(temp == 2) {
					chickenLoc[chickencount] = new Location(r,c);
					chickencount++;
				}
			}
		}
		// ... �Է�
		
		dfs(-1,0);
		
		System.out.println(chickenDist);
		
	}

}
