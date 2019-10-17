package no14500;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
 * �� ������ �������̼��� ��������� ��ǥ���� ���Ѵ�
 * �ش� ��ǥ���� ������ ���Ѵ�
 * ��� ��츦 �ѷ��� ������ �ݺ��Ѵ�
 * 
 * �������̼��� ��츦 �� ������ ������� ���͸� �Է����� �¾Ƽ� Ǫ�� �Լ��� ������
 * �̶�, ��,���� ��� ��������� �Ų������̾����� �����Ƿ� �ٸ� �Լ��� ǰ
 * 
 */
public class Main {

	static int N,M; //n=row, m=col
	static int[][] map;
	static int[] vecr = {0,-1,0,1};
	static int[] vecc = {1,0,-1,0};
	static int max = 0;
	
	//��,��,��,���� �� ���࿡ ���� �Լ�
	//���� ���, ���� ��� r,c�� ù��°ĭ, r1,c1�� �ι�°ĭ �׸��� v1�� r,c���� r1,c1�� �������� ����
	static void cal(int v1, int v2, int v3) {
		int r1,r2,r3,c1,c2,c3;
		int sum;
		for(int r=0; r<N; r++) {
			for(int c=0; c<M; c++) {
				sum = 0;
				//�� ĭ�� ��ǥ�� ����
				r1 = r+vecr[v1];
				c1 = c+vecc[v1];
				r2 = r1+vecr[v2];
				c2 = c1+vecc[v2];
				r3 = r2+vecr[v3];
				c3 = c2+vecc[v3];
				
				//������ �ʰ��ϸ� ������
				if(!(r1>=0 && r1<N && c1>=0 && c1<M)) {continue;}
				if(!(r2>=0 && r2<N && c2>=0 && c2<M)) {continue;}
				if(!(r3>=0 && r3<N && c3>=0 && c3<M)) {continue;}
				
				sum = sum + map[r][c] + map[r1][c1] + map[r2][c2] + map[r3][c3];
				if(sum>max) {max = sum;}
				
			}
		}
	}
	
	//���� �������̼��� ��� v1�� ��or���� ���� v2�� �ѿ��� ��or�Ʒ� �ӿ��� ��or���� ����
	static void cal2(int v1, int v2) {
		int r1,r2,r3,c1,c2,c3;
		int sum;
		
			for (int r = 0; r < N; r++) {
				for (int c = 0; c < M; c++) {
					sum = 0;
					r1 = r + vecr[v1];
					c1 = c + vecc[v1];
					r2 = r1 + vecr[v1];
					c2 = c1 + vecc[v1];
					r3 = r1 + vecr[v2];
					c3 = c1 + vecc[v2];
					if(!(r1>=0 && r1<N && c1>=0 && c1<M)) {continue;}
					if(!(r2>=0 && r2<N && c2>=0 && c2<M)) {continue;}
					if(!(r3>=0 && r3<N && c3>=0 && c3<M)) {continue;}
					
					sum = sum + map[r][c] + map[r1][c1] + map[r2][c2] + map[r3][c3];
					if(sum>max) {max = sum;}
				}
			}
		
		
		
	}
	

	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		String[] st = br.readLine().split(" ");
		N = Integer.parseInt(st[0]);
		M = Integer.parseInt(st[1]);
		map = new int[N][M];
		
		StringTokenizer st1;
		for(int r=0; r<N; r++) {
			st1 = new StringTokenizer(br.readLine()," ");
			for(int c=0; c<M; c++) {
				map[r][c] = Integer.parseInt(st1.nextToken());
			}
		}

		
		//�� 19���� �������̼��� ����
		cal(0,0,0);
		cal(3,3,3);
		cal(0,3,2);
		cal(3,3,0);
		cal(1,0,0);
		cal(0,3,3);
		cal(0,0,1);
		cal(3,3,2);
		cal(0,0,3);
		cal(2,3,3);
		cal(3,0,0);
		cal(3,0,3);
		cal(0,1,0);
		cal(3,2,3);
		cal(0,3,0);
		
		cal2(0,3);
		cal2(0,1);
		cal2(3,2);
		cal2(3,0);
		
		System.out.println(max);
		

	}

}
