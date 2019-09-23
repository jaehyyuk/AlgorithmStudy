package no3_2017;

import java.util.LinkedList;

public class Solution2 {

	public static void main(String[] args) {
		int cacheSize = 3;
		String[] cities = { "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul", "Jeju", "Pangyo", "Seoul" };

		LinkedList<String> link = new LinkedList<>();
		int time = 0;

		if (cacheSize == 0) {
			time = 5 * cities.length;
		}

		else {

			/*
			 * for(int i=0; i<cacheSize; i++) { 
			 * 	link.addLast(cities[i].toLowerCase());
			 * time+=5; }
			 */
			// 이 방법의 문제점 == 캐시사이즈 3인데 모두 같은것이 들어오면? 캐시는 1로 유지 되어야하는데 3까지 넣어버림!

			for (int i = 0; i < cities.length; i++) {
				String temp = cities[i].toLowerCase();

				if (link.contains(temp)) {
					link.remove(temp);
					link.addLast(temp);
					time += 1;
				} else {

					if (link.size() < cacheSize) { //캐시의 사이즈가 다 안찼을경우
						link.addLast(temp);
						time += 5;
					
					} else { //다 찼을경우

						link.pollFirst();
						link.addLast(temp);
						time += 5;
					}
				}

			}
		}
		System.out.println(time);

	}

}
