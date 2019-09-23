package no3_2017;
import java.util.LinkedList;

public class Solution {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = {"Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul"};
		
		LinkedList<String> link = new LinkedList<>();
		int time = 0;
		for(int i=0; i<cacheSize; i++) {
			link.add(cities[i]);
			time += 5;
		}
		
		
		
		for(int i=cacheSize; i<cities.length; i++) {
			
			String temp = cities[i];
			boolean flag = false;
			
			
			
			
			for(int j=0; j<cacheSize; j++) {
				if(link.get(j).equalsIgnoreCase(temp)) {
					temp = link.remove(j);
					link.add(temp);
					flag = true;
					break;
				}
			}
			
			
			
			if(flag) {
				time += 1;
			}
			else {
				link.pollFirst();
				link.add(temp);
				time+=5;
			}
			
			
		}
		System.out.println(time);
		
		

	}

}
