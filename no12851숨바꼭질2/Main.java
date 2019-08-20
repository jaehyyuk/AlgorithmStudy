package no12851���ٲ���2;


/*
 * �� ������ ����Ʈ�� ���ٲ��� 1���� �޸� �ش� ����� ������ ���
 * ���ٲ��� 1ó�� q�� ������ �湮�� �ع����� �ȵ�
 * �������
 * 1 4 �� ��� ���ٲ��� 1ó�� Ǯ��� Q���� [0,2,2,3,4] �ۿ� �ȵ��Ƿ� 4�� �ѹ��� ����ȴ�
 * ù��° 2���� 4�� ������ check[4]�� �̹� �湮�ع����� �ι��� 2���� 4�� ������ �� �ִ´�
 * 
 * ������ q���� ������ �湮�� �Ѵٸ�
 * 1 4 �� ��� Q���� [0,2,2,3,4,3,4 ...] �� ���Ƿ� 4�� �ι� ����ȴ� , 
 * �̶�, q�˻翡�� while���� ���� ���۽����� q������ ��ŭ ���� �ش� Time���� �˻��Ѵ�.
 * 
 * ����, �� ������ �߿��� �������
 * �湮�� ���� �ϴ��Ŀ� ���� Q�� ���ԵǴ� ��찡 �޶�����
 * ������ ���� ������ �����ؼ� ����� �� �־�� �Ѵ�.
 * 
 */


import java.util.Scanner;
import java.util.LinkedList;
import java.util.Queue;


public class Main {
	
	static int N,K;
	
	static void BFS(Queue<Integer> q,Queue<Integer> counts,boolean[] check, int goal) {
		
		int ans=0;
		int count = 0;
		boolean flag = false;

	
		
		while(!q.isEmpty()) {
			
			int size = q.size();
			
			//�ش� Time��ŭ�� for���� ����
			for(int i=0; i<size; i++) {
				
				int temp = q.poll();
				int tempcount = counts.poll();
				
				check[temp]=true; // ***
				

				//K�� ã�������
				if(temp == goal) {
					ans = tempcount;
					count++;
					flag = true;
				}
				
				

				
				if(temp-1 >=0) {
					if(!check[temp-1]) {
						q.add(temp-1); counts.add(tempcount+1);
					}
				}
				
				if(temp+1 <=100000) {
					if(!check[temp+1]) {
						q.add(temp+1); counts.add(tempcount+1);
					}
				}
				
				if(temp*2 <= 100000) {
					if(!check[temp*2]) {
						q.add(temp*2); counts.add(tempcount+1);	
					}
				}
				
			}
			
			//for���� �� �������� flag�� true��� ���� ã����
			if(flag) {
				System.out.println(ans);
				System.out.println(count);
				
				return;
			}
					
		}
		
	}

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		N = sc.nextInt();
		K = sc.nextInt();
		
		Queue<Integer> q = new LinkedList<>();
		Queue<Integer> c = new LinkedList<>();
		boolean[] check = new boolean[100001];
		
		q.add(N);
		c.add(0);
		check[N] = true;
		
		BFS(q, c, check, K);
		

	}

}
