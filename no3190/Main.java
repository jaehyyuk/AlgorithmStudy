package no3190;
import java.io.*;
import java.util.*;

/*
 * 1. ���� ��ġ���� ���� ��ġ�� ���ɴ� �̶�, ������ dirnow[][] �� dirnext[][]�� ���ɴ� (��������迭, ������ ������� �� ������ �����������)
 * 2. dirnext�� ���� ���� ������ �޶�����
 * 3. �̸� ���� ��ġ�� �����Ѵ�.
 * 	3-1. ������ ��ġ�� r,c�� ���� �ȿ� �����°�? -> �ƴϸ� break�� �ɰ� ����������. 
 * 4. ���� �ȿ� ���´ٸ� ������ ��ġ�� ���������� ������Ʈ �����ش�
 * 	4-1. ������ ��ġ�� dirnow�� �����ϱ� ���� dirnext��
 *  4-2. ������ ��ġ�� dirnext�� �־��� ���� ������ ���� �� �����Ѵ�
 *   4-2-1. ������ �ٲ���ϴ� time�� ��� ������ ��ġ�� dirnow(������ dirnext)�� �޾ƿ� ���������� ���� ���Ѵ� (get_dir�Լ�)
 *   4-2-2. ������ �ٲ��� �ʾƵ� �� ��� (������)dirnext�� (������)dirnow�� ���� ����.
 * 5. ���������� ������Ʈ ������ ������ ��ġ�� �ִ� ���� ��� ���� ������ ���� �˾ƺ���.
 * 	5-1. ������ ��ġ�� ���� ����̸� �Դ´� --> tail��ġ ���� x
 *  5-2. ������ ��ġ�� ���̴� --> break;
 *  5-3. ������ ��ġ�� �ƹ��͵� ���� --> tail��ġ ����
 */

public class Main {
	
	static int N;
	static int[][] map;
	static int[][] dirnow;
	static int[][] dirnext;
	
	
	static final int right = 1;
	static final int up = 2;
	static final int left = 3;
	static final int down = 4;
	
	static int[] vecRow = {-1,0,-1,0,1};
	static int[] vecCol = {-1,1,0,-1,0};
	
	//������ ����� �ٲ� ������ ������ ���� 4���� �� �ϳ��� ����
	static int get_dir(int nowdir, char c) {
		
		int dir = 0;
		switch(nowdir) {
		
			case 1:
				if(c=='D') {
					dir=down;
				}
				else {
					dir = up;
				}
				break;
				
			case 2:
				if(c=='D') {
					dir = right;
				}else {
					dir = left;
				}
				break;
				
			case 3:
				if(c=='D') {
					dir=up;
				}else {
					dir=down;
				}
				
				break;
				
			case 4:
				if(c=='D') {
					dir = left;
				}else {
					dir=right;
				}
				
			
		}
		
		return dir;
		
	}
	
	public static void main(String[] args) throws IOException {
	
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		N = Integer.parseInt(br.readLine());
		map = new int[N+1][N+1];
		dirnow = new int[N+1][N+1];
		dirnext = new int[N+1][N+1];
		
		int app = Integer.parseInt(br.readLine());
		
		for(int i=0; i<app; i++) {
			String st[] = br.readLine().split(" ");
			int r = Integer.parseInt(st[0]);
			int c = Integer.parseInt(st[1]);
			
			map[r][c] = 2;
		}
		
		int turn = Integer.parseInt(br.readLine());
		Queue<Integer> time = new LinkedList<>();
		Queue<Character> dir = new LinkedList<>();
		for(int i=0; i<turn; i++) {
			String st[] = br.readLine().split(" ");
			time.add(Integer.parseInt(st[0]));
			dir.add(st[1].charAt(0));   //*** charAt!
		}

		/////////////////////... Input///////////////////////
		
		//setting initial value
		dirnow[1][1] = right;
		dirnext[1][1] = right;
		map[1][1] = 1;
		int nowR = 1; int nowC = 1; //head location
		int endR = 1; int endC = 1; //tail location
		
		
		// polling the next turning point values
		int nexttime = time.poll();
		char nextdir = dir.poll();
		
		int totaltime = 0; // time of moving value
		
		while(true) {  
			//1. ���� ��ġ���� ���� ��ġ�� ���ɴ� �̶�, ������ dirnow[][] �� dirnext[][]�� ���ɴ� (��������迭, ������ ������� �� ������ �����������)
			int nowd = dirnow[nowR][nowC]; //���� ��ġ�� ���� ����
			int nextd = dirnext[nowR][nowC]; //���� ��ġ�� ���� ����
			
			//2. dirnext�� ���� ���� ������ �޶����� (r,c ����)
			nowR = nowR+vecRow[nextd];
			nowC = nowC+vecCol[nextd];
			
			//3-1. ������ ��ġ�� r,c�� ���� �ȿ� �����°�?
			if(nowR>=1 && nowR<=N && nowC>=1 && nowC<=N) {
				
				//������ ��ġ�� ���ΰ�?
				if(map[nowR][nowC]==1) break;
				
				//4-1. ������ ��ġ�� dirnow�� �����ϱ� ���� dirnext��
				dirnow[nowR][nowC] = nextd;
				
				totaltime++; //������ �̹Ƿ� �ð� ����
				
				if(totaltime == nexttime) {
					// ������ �ٲ���ϴ� time�� ��� ������ ��ġ�� dirnow(������ dirnext)�� �޾ƿ� ���������� ���� ���Ѵ� (get_dir�Լ�)
					dirnext[nowR][nowC] = get_dir(nextd, nextdir);
					
					//nexttime �� nextdir����
					if(!time.isEmpty()) {
						nexttime = time.poll();
						nextdir = dir.poll();
					}
					
					
				}else {
					//������ �ٲ��� �ʾƵ� �� ��� (������)dirnext�� (������)dirnow�� ���� ����.
					dirnext[nowR][nowC] = nextd; //������ dirnow�� �������� dirnext�̹Ƿ� nextd�� ����
				}
				
				

				//������ ��ġ�� ���� ����̸� �Դ´� --> tail��ġ ���� x, ��ǥ��
				if(map[nowR][nowC]==2){
					map[nowR][nowC]=1;
				}
				//������ ��ġ�� �ƹ��͵� ���� -->  ��ǥ��, �������� ö��, tail��ġ ����
				else {

					map[nowR][nowC]=1;
					map[endR][endC] = 0; //���� tail 0�ʱ�ȭ

					int nextEnddir = dirnext[endR][endC];
					endR = endR + vecRow[nextEnddir];
					endC = endC + vecCol[nextEnddir];
				}
				
			}
			//������ġ�� ������ �Ѿ --> �� --> break
			else {
				break;
			}
			
			
			
		}
		System.out.println(totaltime+1);
		
	}

}
