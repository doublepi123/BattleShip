package model;

import java.io.*;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Comparator;

public class Rank implements Serializable {
    final int maxNumberOfRank = 10;
    private Integer []data = null;
    public Rank(){
        data = new Integer[maxNumberOfRank];
        for(int i = 0 ; i < maxNumberOfRank ; i++){
            data[i] = 0;
        }
    }

    public int push(int score){
        Arrays.sort(data,Comparator.reverseOrder());
        int pos = maxNumberOfRank-1;
        if(score > data[pos]){
            data[pos--] = score;
        }else return -1;
        while(pos != 1){
            if(data[pos] < data[pos+1]) {
                int t = data[pos];
                data[pos] = data[pos+1];
                data[pos+1] =t;
            }else break;
        }
        Arrays.sort(data,Comparator.reverseOrder());
        return pos;
    }

    public Integer[] getData() {
        Arrays.sort(data,Comparator.reverseOrder());
        return data;
    }
}
