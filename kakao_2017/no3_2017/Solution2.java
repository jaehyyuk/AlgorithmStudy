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
			// �� ����� ������ == ĳ�û����� 3�ε� ��� �������� ������? ĳ�ô� 1�� ���� �Ǿ���ϴµ� 3���� �־����!

			for (int i = 0; i < cities.length; i++) {
				String temp = cities[i].toLowerCase();

				if (link.contains(temp)) {
					link.remove(temp);
					link.addLast(temp);
					time += 1;
				} else {

					if (link.size() < cacheSize) { //ĳ���� ����� �� ��á�����
						link.addLast(temp);
						time += 5;
					
					} else { //�� á�����

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
