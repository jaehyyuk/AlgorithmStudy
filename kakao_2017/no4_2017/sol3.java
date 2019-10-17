import java.util.*;


/*
*   시간계산할때 시,분,초 한단위로 통일되게 계산하자.
*   운행횟수와 최대운행크루 수 만큼 q에서 제거하는 방법을 써야함
*   이때, q가 빌때를 잘 핸들링 해야함!! q가 비는 경우는 타임테이블 수가 적거나
*   모두 마지막 타임 이후에 시간이 있을 경우다
*   n-1까지 운행을 마무리하고 n번째 운행에서 몇명이 탔는지 체크
*   m보다 적게 탔다면 n번째 운행시간까지가 답
*   m만큼 탔다면 마지막크루의 시간-1
*/

public class sol3{
    public static void main(String[] args) {
        int n = 10;
        int t = 60;
        int m = 45; 
        String[] timetable = {"23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59"};
       // n=3; t=15; m=2;
        //String[] timetable = {"08:10","08:30","08:59","09:10","09:13","09:15","09:23","09:25"};

        //n=10; t=60; m=45;
        //String[] timetable = {"08:00","09:09","09:10"};
        
        int time_lastbus = 540 + (n-1)*t; //마지막버스시간

        int[] inttime = new int[timetable.length]; //입력시간을 분으로 통일한 시간을 저장
        //파싱
        for(int i=0; i<inttime.length; i++){
            inttime[i] = Integer.parseInt(timetable[i].substring(0,2))*60 + 
            Integer.parseInt(timetable[i].substring(3,5));
        }
        //오름차순정리
        Arrays.sort(inttime);
        //크루들을 태울지말지 정할 큐
        Queue<Integer> q = new LinkedList<>();

        for(int i=0; i<inttime.length;i++){
            if(inttime[i]<=time_lastbus){
                q.add(inttime[i]);
            }
            else{
                break;
            }
        }

        //n-1번째까지 운행
        for(int i=0; i<n-1; i++){
            int time = 540 + i*t;
            for(int j=0; j<m; j++){
                if(q.size()==0){break;} //큐사이즈가 0이라면 더이상 볼것이 없음
                if(q.peek()<=time){
                    q.poll();
                }
            }
            if(q.size()==0){break;}
        }

        //n번째 운행
        int count = 0;
        int last_person_time = 0;
        for(int i=0; i<m; i++){
            if(q.size()==0){break;}
            if(q.peek()<=time_lastbus){
                count++;
                last_person_time = q.poll();
            }
        }

        int answer = 0;
        if(count==m){
            answer = last_person_time - 1;
        }else{
            answer = time_lastbus;
        }


        String ans = "";
        if(answer/60==0){
            ans = ans + "00:";
        }
        else if(answer/60<10){
            ans = ans + "0" + (answer/60)+":" ;
        }
        else{
            ans = ans + (answer/60) + ":" ;
        }

        if(answer%60==0){
            ans = ans + "00";
        }
        else if(answer%60 < 10){
            ans = ans + "0" + (answer%60);
        }
        else{
            ans = ans + (answer%60);
        }
        

        System.out.println(
            ans
        );


    
    
    }
}