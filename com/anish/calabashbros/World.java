package com.anish.calabashbros;
import com.anish.maze.MazeGenerator;

public class World {

    public static final int WIDTH = 42;
    public static final int HEIGHT = 42;

    private Tile<Thing>[][] tiles;
    private int[][] map;

    public World() {

        if (tiles == null) {
            tiles = new Tile[WIDTH][HEIGHT];
        }

        MazeGenerator mGenerator = new MazeGenerator(WIDTH-2);
        mGenerator.generateMaze();
        int[][] maze = mGenerator.getMaze();

       
        //初始化地图并在迷宫边缘加一层墙壁
        map = new int[WIDTH][HEIGHT];
        for (int i = 0; i < WIDTH; i++) {
            for (int j = 0; j < HEIGHT; j++) {
                tiles[i][j] = new Tile<>(i, j);
                if (i == 0 || j == 0 || i == WIDTH -1 || j == HEIGHT-1){
                    tiles[i][j].setThing(new Wall(this));
                    map[i][j] = 0;
                    continue;
                }

                if (maze[i-1][j-1] == 1){
                    tiles[i][j].setThing(new Floor(this));
                    map[i][j] = 1;
                }
                else{
                    tiles[i][j].setThing(new Wall(this));
                    map[i][j] = 0;
                }
                
            }
        }
        tiles[1][1].setThing(new Sign(this));
        tiles[WIDTH-2][HEIGHT-2].setThing(new Sign(this));
    }

    public Thing get(int x, int y) {
        return this.tiles[x][y].getThing();
    }

    public void put(Thing t, int x, int y) {
        this.tiles[x][y].setThing(t);
    }

    public int[][] getMap(){
        return map;
    }

}
