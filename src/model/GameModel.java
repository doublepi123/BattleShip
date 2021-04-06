package model;

import java.awt.*;

public class GameModel {
    private int row,col;
    private int[][] data;
    private int[] score;
    private boolean[][] vis;
    private ShipColor[] shipColor;
    final private int numberOfShip = 4;
    final int n = 8;
    int last;
    int p1, p2;
    int turns;
    int model;
    //-1 已经访问过
    //0 海洋
    //n 分数
    public int tryATry(int x, int y) {
        System.out.println(x+" "+y);
        if (vis[x][y]) return -1;
        vis[x][y] = true;
        turns++;
        if(data[x][y] > 0) last--;
        System.out.println("last  : "+last);
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

    public GameModel(int row,int col,int model) {
        turns = 0;
        p1 = 0;
        p2 = 0;
        this.model = model;
        this.row = row;
        this.col = col;
        data = new int[8][];
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
        for(int i = 0 ; i < n ; i++){
            for(int j = 0 ; j < n ;j++){
                System.out.print(data[i][j]);
            }
            System.out.println("");
        }
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

    public int getNumberOfShip(int x, int y) {
        return data[x][y];
    }

    //随机生成两艘船并初始化数组
    void initShip(int p) {
        int ax = (int) (Math.random() * (row - 1));
        int ay = (int) (Math.random() * (col - 1));
        if (p % 2 == 1) {
            int al = p;
            if(ax+al >= row)
            {
                initShip(p);
                return;
            }
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
            int al = p;
            if(ay+al >= col){
                initShip(p);
                return;
            }
            for (int i = ay; i <= ay + al; i++) {
                if (data[ax][i] != 0) {
                    initShip(p);
                    return;
                }
            }
            for (int i = ay; i <= ay + al; i++) {
                data[ax][i] = p;
                last++;
            }
        }
    }

    public Color getShipColor(int i) {
        if(i > 4 || i < 1) return Color.BLUE;
//        System.out.println(i);
        return new Color(shipColor[i].r,shipColor[i].g,Math.min(shipColor[i].b,128));
    }

    public void saveRecord(){

    }
}

