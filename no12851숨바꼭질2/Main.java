package no12851숨바꼭질2;


/*
 * 이 문제의 포인트는 숨바꼭질 1과는 달리 해당 경우의 수까지 출력
 * 숨바꼭질 1처럼 q에 넣을때 방문을 해버리면 안됨
 * 예를들어
 * 1 4 의 경우 숨바꼭질 1처럼 풀경우 Q에는 [0,2,2,3,4] 밖에 안들어가므로 4가 한번만 검출된다
 * 첫번째 2에서 4를 넣을때 check[4]를 이미 방문해버려서 두번재 2에서 4를 넣을때 못 넣는다
 * 
 * 하지만 q에서 꺼낼때 방문을 한다면
 * 1 4 의 경우 Q에는 [0,2,2,3,4,3,4 ...] 로 들어가므로 4가 두번 검출된다 , 
 * 이때, q검사에는 while문을 도는 시작시점에 q사이즈 만큼 돌아 해당 Time씩만 검사한다.
 * 
 * 따라서, 이 문제의 중요한 배울점은
 * 방문을 언제 하느냐에 따라서 Q에 들어가게되는 경우가 달라지며
 * 문제에 따라 적절히 선택해서 사용할 수 있어야 한다.
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
			
			//해당 Time만큼만 for문을 돈다
			for(int i=0; i<size; i++) {
				
				int temp = q.poll();
				int tempcount = counts.poll();
				
				check[temp]=true; // ***
				

				//K를 찾았을경우
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
			
			//for문을 다 돌았을때 flag가 true라면 답을 찾은것
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
