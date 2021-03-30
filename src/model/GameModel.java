package model;

import java.awt.*;

public class GameModel {
    private int[][] data;
    private int[] score;
    private boolean[][] vis;
    private ShipColor[] shipColor;
    private int numberOfShip;
    final int n = 8;
    int last;
    int p1, p2;
    int turns;
    //-1 已经访问过
    //0 海洋
    //n 分数
    public int tryATry(int x, int y) {
        System.out.println(x+" "+y);
        if (vis[x][y]) return -1;
        vis[x][y] = true;
        turns++;
        if(turns%2 == 1) p1 += score[data[x][y]];
        else p2 += score[data[x][y]];
        return data[x][y];
    }
    public int getP1(){
        return p1;
    }
    public int getP2(){
        return p2;
    }
    public boolean check() {
        return last == 0;
    }

    public GameModel(int numberOfShip) {
        turns = 0;
        p1 = 0;
        p2 = 0;
        this.numberOfShip = numberOfShip;
        data = new int[n][];
        score = new int[numberOfShip + 1];
        shipColor = new ShipColor[numberOfShip + 1];
        vis = new boolean[n][];
        for (int i = 0; i < n; i++) {
            data[i] = new int[n];
            vis[i] = new boolean[n];
        }
//        初始化地图
        eraseData();
//        初始化船
        for (int i = 1; i <= numberOfShip; i++) {
            initShip(i);
            score[i] = i * 10 + (int) (Math.random() * i);
            shipColor[i] = new ShipColor();
        }
//        DEBUG
//        for(int i = 0 ; i < n ; i++){
//            for(int j = 0 ; j < n ;j++){
//                System.out.print(data[i][j]);
//            }
//            System.out.println("");
//        }
//        for(int i = 1 ; i <= numberOfShip ; i++){
//            System.out.println(colorOfShip[i].getB());
//        }
    }

    void eraseData() {
        last = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                data[i][j] = 0;
                vis[i][j] = false;
            }
        }
    }

    public int getNumberOfShip() {
        return numberOfShip;
    }

    //随机生成两艘船并初始化数组
    void initShip(int p) {
        int ax = (int) (Math.random() * (n - 1));
        int ay = (int) (Math.random() * (n - 1));
        if (p % 2 == 1) {
            int al = (int) (Math.random() * (n - ax - 1) + 1);
            for (int i = ax; i <= ax + al; i++) {
                if (data[i][ay] != 0) {
                    initShip(p);
                    return;
                }
            }
            for (int i = ax; i <= ax + al; i++) {
                data[i][ay] = p;
                last++;
            }
        } else {
            int al = (int) (Math.random() * (n - ay - 1) + 1);
            for (int i = ay; i <= ay + al; i++) {
                if (data[ax][i] != 0) {
                    initShip(p);
                    return;
                }
            }
            for (int i = ay; i <= ay + al; i++) {
                data[ax][i] = p;
            }
        }
    }

    public ShipColor[] getShipColor() {
        return shipColor;
    }

    public static void main(String[] args) {
        GameModel gameModel = new GameModel(4);
    }
}

