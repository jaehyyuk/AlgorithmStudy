package no1799���;

/*dfs+��Ʈ��ŷ�� Ȱ���� ����
 * 
 * ü������ 2���� �������� �и��ؼ� ����
 * 1 2 1 2
 * 2 1 2 1
 * 1 2 1 2
 * 2 1 2 1
 * �̷������� ������ 1���� ������ dfs�� 1������ Ž���� ����ϰ� 2�� 2������
 * �̷��� ���� ������ �ð��ʰ���
 * 
 * 
 */




import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class solve1 {
	
	static int size;//��ũ��
	static int map[][]; //0,1,2�� �� ��
	static boolean visit[][]; //�湮�迭
	static int vecR[] = {1,1,-1,-1}; //�밢�� ����(��)
	static int vecC[] = {1,-1,-1,1}; //�밢�� ����(��)
	static int counts[]; //�ִ����� ���� ����迭 counts[1] -> index 1�� �ִ� ��󰹼�
	
	static void dfs(int start, int index, int count) {
		
		if(counts[index]<count) {
			counts[index] = count;
		}
		
		// dfsž���� �����Ҷ� dfs�� �� ������ �ε������� �����ϵ��� start+1
		for(int i = start+1; i<size*size; i++) {
			int r = i/size;
			int c = i%size;
			
			//���� �ε����� ���� dfs�� �ε����� ���� �湮���� �ʾ�����,
			if(map[r][c] == index && !visit[r][c]) {
				
				//����� ���� �� �ִٸ� dfs����
				if(isPossible(r,c)) {
					visit[r][c] = true;
					dfs(i,index, count+1);
					visit[r][c] = false;
				}
			}
			
		}
		
	}
	
	//�밢���� ����� ���� �� �ִ��� Ȯ���ϴ� �޼ҵ�
	static boolean isPossible(int r, int c) {
		
		boolean temp = true;
		
		for(int i=0; i<4 ; i++) {
			
			int tempr = r; int tempc = c;
			
			while(true) {
				tempr = tempr + vecR[i];
				tempc = tempc + vecC[i];
				
				if(rangeCheck(tempr,tempc)) {
					if(visit[tempr][tempc]) {
						temp=false;
					}
				}
				else {
					break; //������ ����� �극��ũ
				}
			}
			
		}
		
		return temp;
		
	}
	
	//����üũ
	static boolean rangeCheck(int r, int c) {
		if(r>=0 && r<size && c>=0 && c<size) {return true;}
		return false;
	}
	

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		size = Integer.parseInt(br.readLine());
		
		map = new int[size][size];
		visit = new boolean[size][size];
		
		for(int r=0; r<size; r++) {
			
			String templine[] = br.readLine().split(" ");
			for(int c=0; c<size; c++) {
				map[r][c] = Integer.parseInt(templine[c]);
			}
		}
		
		
		// �ε��� 1�� 2�� ������ �۾�
		for(int r=0;  r<size; r++) {
			
			if(r%2==0) {
				for(int c=1; c<size; c=c+2) {
					if(map[r][c]==0) {continue;}
					map[r][c] = 2;
				}
			}
			else {
				for(int c=0; c<size; c=c+2) {
					if(map[r][c]==0) {continue;}
					map[r][c]=2;
				}
			}
		}
		
		
		counts = new int[3];
		counts[1] = 0; counts[2] = 0;
		
		dfs(-1,1,0); //�ε��� 1�� ��� ����
		dfs(-1,2,0); //�ε��� 2�� ��� ����

		System.out.println(counts[1] + counts[2]);
	}

}
