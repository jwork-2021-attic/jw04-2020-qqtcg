package com.anish.screen;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.lang.reflect.Array;
import java.util.Random;

import com.anish.calabashbros.BubbleSorter;
import com.anish.calabashbros.Calabash;
import com.anish.calabashbros.QuickSorter;
import com.anish.calabashbros.World;

import asciiPanel.AsciiPanel;

public class WorldScreen implements Screen {

    private World world;
    private Calabash[][] bros;
    String[] sortSteps;

    public WorldScreen() {
        world = new World();

        bros = new Calabash[15][15];

        int[][] rankTool = getRandomTwo(15, 15);
        

        for (int i = 0; i < 15; i++){
            for (int j = 0; j < 15; j++){
                int seed = rankTool[i][j];
                bros[i][j] = new Calabash(
                    new Color(225 - seed, seed + 30, 255 - seed), seed, world);
                world.put(bros[i][j], 2*j + 5, 2*i + 5);
            }
        }


        /*bros[3] = new Calabash(new Color(204, 0, 0), 1, world);
        bros[5] = new Calabash(new Color(255, 165, 0), 2, world);
        bros[1] = new Calabash(new Color(252, 233, 79), 3, world);
        bros[0] = new Calabash(new Color(78, 154, 6), 4, world);
        bros[4] = new Calabash(new Color(50, 175, 255), 5, world);
        bros[6] = new Calabash(new Color(114, 159, 207), 6, world);
        bros[2] = new Calabash(new Color(173, 127, 168), 7, world);

        world.put(bros[0], 10, 10);
        world.put(bros[1], 12, 10);
        world.put(bros[2], 14, 10);
        world.put(bros[3], 16, 10);
        world.put(bros[4], 18, 10);
        world.put(bros[5], 20, 10);
        world.put(bros[6], 22, 10);*/

        BubbleSorter<Calabash> b = new BubbleSorter<>();
        QuickSorter<Calabash> q = new QuickSorter<>();
        Calabash[] temp = new Calabash[15 * 15];
        for (int i = 0; i < 15; i++){
            System.arraycopy(bros[i], 0, temp, i*15, 15);
        }
        q.load(temp);
        q.sort();

        sortSteps = this.parsePlan(q.getPlan());
    }

    private String[] parsePlan(String plan) {
        return plan.split("\n");
    }

    private void execute(Calabash[] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }

    private Calabash getBroByRank(Calabash[] bros, int rank) {
        for (Calabash bro : bros) {
            if (bro.getRank() == rank) {
                return bro;
            }
        }
        return null;
    }

    private Calabash getBroByRank(Calabash[][] bros, int rank) {
        for (Calabash[] bro : bros) {
            for (Calabash br : bro){  
                if (br.getRank() == rank) {
                return br;
            }
        }
        }
        return null;
    }

    private void execute(Calabash[][] bros, String step) {
        String[] couple = step.split("<->");
        getBroByRank(bros, Integer.parseInt(couple[0])).swap(getBroByRank(bros, Integer.parseInt(couple[1])));
    }


    @Override
    public void displayOutput(AsciiPanel terminal) {

        for (int x = 0; x < World.WIDTH; x++) {
            for (int y = 0; y < World.HEIGHT; y++) {

                terminal.write(world.get(x, y).getGlyph(), x, y, world.get(x, y).getColor());

            }
        }
    }

    int i = 0;

    @Override
    public Screen respondToUserInput(KeyEvent key) {

        if (i < this.sortSteps.length) {
            this.execute(bros, sortSteps[i]);
            i++;
        }

        return this;
    }

    public int[][] getRandomTwo(int heigtht, int weigth){
        int[] randLine = getRandom(heigtht*weigth);
        int[][] twoDimensionalArray = new int[heigtht][weigth];
        for (int i = 0; i < randLine.length; i++){
            twoDimensionalArray[i / heigtht][i - (i / heigtht) * weigth] = randLine[i];
        }

        return twoDimensionalArray;

    }


    public int[] getRandom(int size){
        Random r = new Random();
        //产生随机数数组
        int[] randomNumber = new int[size];
        for (int i = 0; i < size; i++){
            randomNumber[i] = i;
        }

        for (int j = 0; j < size; j++){
            int temp = randomNumber[j];
            int tempSign = r.nextInt(size);
            randomNumber[j] = randomNumber[tempSign];
            randomNumber[tempSign] = temp;
        }
        return randomNumber;
    }

}
