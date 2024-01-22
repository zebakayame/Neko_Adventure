package code;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.File;
import java.util.Scanner;

public class Mapping {
    
    int[][] map;
    int alphaX;
    int alphaY;
    
    GamePanel gp;
    
    public Mapping(GamePanel gp){
        this.gp = gp;
        map = new int[100][100];
        
        processMap();
        showMap();
    }

    private void processMap() {
        try {
            File mapF = new File(System.getProperty("user.dir") + "/ressource/Maps/testMap.nkmp");
            Scanner scan = new Scanner(mapF);
            while (scan.hasNextLine()) {
                String data = scan.nextLine();
                String[] arrOfdata = data.split(":");
                String[] cor = arrOfdata[0].split(",");
                int corX = Integer.parseInt(cor[0]);
                int corY = Integer.parseInt(cor[1]);
                
                String[] function = arrOfdata[1].split("=");
                String f = function[0];
                int n = Integer.parseInt(function[1]);
                int tileType = Integer.parseInt(arrOfdata[2]);
                switch (f) {
                    case "l_x":
                        for (int i = 0; i < n; i++) {
                            map[corX + i][corY] = tileType;
                        }
                        break;
                    case "l_y":
                        for (int i = 0; i < n; i++) {
                            map[corX][corY + i] = tileType;
                        }
                        break;    
                    default:
                        break;
                }

                System.out.println(f);
            }
            scan.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        
    }

    public void changeAlpha(int x, int y){
        alphaX = -x;
        alphaY = -y;
    }


    private void showMap() {
        for(int i=0;i<10;i++){
            for(int j=0;j<10;j++){
                System.out.print("\t" + map[i][j]);
            }
            System.out.println();
        }
    }


    public void drawMap(Graphics2D gp2){
        for(int i = 0; i < map.length;i++){
            for(int j=0;j<map[0].length; j++){
                int x = i*gp.tilesScale + alphaX;
                int y = j*gp.tilesScale + alphaY;
                if(map[i][j] == 0){
                    gp2.setColor(Color.GRAY);
                    gp2.fillRect(x, y, gp.tilesScale, gp.tilesScale);
                }else{
                    gp2.setColor(Color.WHITE);
                    gp2.fillRect(x, y, gp.tilesScale, gp.tilesScale);
                }
            }
        }
    }
}
