
import java.util.*;
import java.io.*;

public class Sol2{

    public static void main(String[] args) {
        int n = 10;
        int t = 60;
        int m = 45; 
        //String[] timetable = {"23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59","23:59"};
        n=3; t=15; m=2;
        //String[] timetable = {"08:10","08:30","08:59","09:10","09:13","09:15","09:23","09:25"};

        n=10; t=60; m=45;
        String[] timetable = {"08:00","09:09","09:10"};

        int[] inttime = new int[timetable.length];
        
        for(int i=0; i<inttime.length; i++){
            inttime[i] = Integer.parseInt(timetable[i].substring(0,2))*60 + 
            Integer.parseInt(timetable[i].substring(3,5));
        }

        Arrays.sort(inttime);

        int bustime[] = new int[n];
        int start = 540;
        int people[] = new int[n];
        int last_people_time[] = new int[n];
        for(int i=0; i<bustime.length; i++){
            bustime[i] = start;
            start += t;
            last_people_time[i] = -1;
            people[i] = m;
        }

        int index = 0;
        for(int i=0; i<inttime.length; i++){
            if(inttime[i]>bustime[index] && people[index] !=0){index++;}
            if(index==n){break;}
            if(inttime[i]<=bustime[index] && people[index]>0){
                people[index]--;
                last_people_time[index] = inttime[i];
                if(people[index]==0){index++;}
            }
            if(index==n){break;}
        }






        int answer = 0;
        if(people[n-1]==0){
            answer = last_people_time[n-1]-1;
        }
        else{
            answer = bustime[n-1];
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