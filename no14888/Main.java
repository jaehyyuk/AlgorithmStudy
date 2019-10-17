package no14888;
/*
 * 
 * DFS ���Ʈ���� ����
 * �ʱ���۰��� ó�� ������ ����
 * �� �������� ī��Ʈ�� ���� dfs���Կ��θ� �����Ѵ�(visit�迭�� ���� ����)
 * depth�� N�� ������, �� ��� ������ �� ���� �������� max�� min�� �� ���� �Ѵ�
 * 
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.util.StringTokenizer;
public class Main {

	
	static int N;
	static int[] nums;
	static int[] operator;
	static int max = Integer.MIN_VALUE;
	static int min = Integer.MAX_VALUE;
	
	static void dfs(int depth, int calculated) {
		
		
		if(depth>=N) {
			
			if(calculated>max) {max = calculated;}
			if(calculated<min) {min = calculated;}
			
			return;}
		
		int num = nums[depth];
		
		for(int i=0; i<4; i++) {
			if(operator[i]>0) {
				if(i==0) {
					operator[i]--;
					dfs(depth+1, calculated+num);
					operator[i]++;
					
				}else if(i==1) {
					operator[i]--;
					dfs(depth+1, calculated-num);
					operator[i]++;
				}else if(i==2){
					operator[i]--;
					dfs(depth+1, calculated*num);
					operator[i]++;
				}else {
					operator[i]--;
					dfs(depth+1, calculated/num);
					operator[i]++;
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		N = Integer.parseInt(br.readLine());
		nums = new int[N];
		operator = new int[4];
		
		StringTokenizer st;
		st = new StringTokenizer(br.readLine()," ");	
		for(int i=0; i<N; i++) {
			nums[i] = Integer.parseInt(st.nextToken());
		}
		
		st = new StringTokenizer(br.readLine()," ");
		for(int i=0; i<4; i++) {
			operator[i] = Integer.parseInt(st.nextToken());
		}
		
		dfs(1,nums[0]);
		System.out.println(max);
		System.out.println(min);
	}

}
