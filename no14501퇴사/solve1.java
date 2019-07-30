package no14501퇴사;

import java.io.*;
import java.util.*;

public class solve1 {

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int workday = Integer.parseInt(br.readLine());
		
		int[] days = new int[workday+2];
		int[] income = new int[workday+2];
		boolean[] take = new boolean[workday+2];
		int[] dp = new int[workday+2];
		
		
		for(int i=1; i<=workday; i++) {
			
			StringTokenizer st = new StringTokenizer(br.readLine()," ");
			
			days[i] = Integer.parseInt(st.nextToken());
			income[i] = Integer.parseInt(st.nextToken());
			
		}
		
		
		for(int idx = workday ; idx>=1; idx--) {
			
			int endDay = idx+days[idx]-1;
			if(endDay>workday) { //해당 idx번째의 끝나는 일이 퇴사일을 넘기면
				dp[idx] = dp[idx+1];
			}
			else { //퇴사일을 넘기지 않으면
				
				dp[idx] = Math.max(dp[idx+1], income[idx] + dp[endDay+1]);
				
			}
			
		}
		
		System.out.println(dp[1]);
		
		
		
		
		
		
		
		
		
		
		
		
		/*  첫번째 시도
		int maxSumIncome = 0;
		for(int idx=workday; idx>=1; idx--) {
			
			if(idx + days[idx] - 1 > workday) {
				take[idx] = false;
			}
			else {
				
				if(days[idx]==1) {
					take[idx]=true;
					maxSumIncome += income[idx];
				}
				else {
					int endcase = idx+days[idx]-1;
					
					int compare = maxSumIncome;
					for(int i=idx+1; i<=endcase; i++) {
						
						if(take[i]) {
							compare = compare - income[i];
						}
						
					}
					
					compare += income[idx];
					
					if(compare > maxSumIncome) {
						
						for(int i=idx+1 ; i<=endcase; i++) {
							take[i] = false;
						}
						take[idx] = true;
						maxSumIncome = compare;
					}
					else {
						take[idx] = false;
					}
					
				}
				
				
			}
			
		}
		
		System.out.println(maxSumIncome);
		*/
		
		
		

	}

}
