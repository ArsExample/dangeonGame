package com.company;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.Graphics;
import java.util.Random;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import javax.imageio.ImageIO;

import static com.company.KitKidok.kitKidokIsAlive;
import static com.company.Main.img;
import static com.company.Main.img1;
import static com.company.StatePlace.*;
import static com.company.Enemy.*;
import static com.company.AttackType.*;
import static com.company.EnemyAttackType.*;
import static com.company.Ruconog.*;
import static com.company.LimboMushroom.*;
import static com.company.ThreeHorned.*;
import static com.company.KitKidok.*;
import static com.company.QuickAvocado.*;
import static com.company.ReedGoose.*;

public class Main {
    static StatePlace place = underground;
    static Enemy enemy = null;
    static AttackType attacktype;
    static EnemyAttackType enemyAttackType;

    static int maxHealth = 3;

    static int strength = 1;
    static int health = maxHealth;
    static int money = 0;

    static int dice;
    static int enemyDice;

    static boolean alive = true;
    static boolean useDice = true;

    static Random r = new Random();
    static Random r1 = new Random();

    static boolean ok = false;

    static BufferedImage img;

    static {
        try {
            img = ImageIO.read(new File("LES.jpg"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage img5;

    static {
        try {
            img5 = ImageIO.read(new File("strelka2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedImage img10;

    static {
        try {
            img10 = ImageIO.read(new File("h0.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedImage img6;

    static {
        try {
            img6 = ImageIO.read(new File("strelka3.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedImage img8;
    static {
        try {
            img8 = ImageIO.read(new File("str1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage img3;

    static {
        try {
            img3 = ImageIO.read(new File("strelka1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage img7;
    static {
        try {
            img7 = ImageIO.read(new File("strelka4.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    static BufferedImage img9;
    static {
        try {
            img9 = ImageIO.read(new File("str2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static BufferedImage img2;

    static {
        try {
            img2 = ImageIO.read(new File("pixilart-drawing_10.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static BufferedImage img1;

    static {
        try {
            img1 = ImageIO.read(new File("PODZEMELIE.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Thread cheatsThread = new Cheats();
        cheatsThread.start();
        if (alive == true || health>0) {
            Random r = new Random();
            JFrame frame = new JFrame();
            frame.setTitle("Some title");
            frame.setUndecorated(false);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            frame.setSize(800, 700);

            frame.setLayout(null);

            DrawingArea drawingArea = new DrawingArea();
            drawingArea.setBounds(0, 0, 550, 400);
            frame.add(drawingArea);


            //Если нужна постоянная перерисовка
//        Timer repaintFrame = new Timer(1000 / 40, e -> drawingArea.repaint());
//        repaintFrame.start();


            JTextArea text1 = new JTextArea();
            text1.setBounds(560, 30, 200, 400);
            text1.setEnabled(false);
            text1.setFont(new Font("Calibri", Font.PLAIN, 16));
            text1.setDisabledTextColor(Color.BLACK);
            text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
            frame.add(text1);


            JTextArea text = new JTextArea();
            text.setBounds(210, 455, 320, 120);
            text.setEnabled(false);
            text.append("Вы проснулсь в каком-то подземелье.\nКуда вы пойдёте?\n");
            text.setFont(new Font("Calibri", Font.PLAIN, 16));
            text.setDisabledTextColor(Color.BLACK);
            frame.add(text);

            JButton button = new JButton("GO FOREST");//forest, underground, house
            button.setBounds(40, 510, 160, 35);
            button.addActionListener(l -> {
                if (place == house && health > 0) {
                    place = houseToForest;
                    text.setText("");
                    text.setText("ёпрст, на вашем пути встретился руконог!\n");
                    enemy = rukonog;
                    Ruconog.rukonogHeal();
                    useDice = true;
                } else if (place == underground && health > 0) {
                    place = undergroundToForest;
                    text.setText("");
                    enemy = reedGoose;
                    text.setText("ммм, в камышах вы нашли\nкамышового гуся!\n");
                    ReedGoose.reedGooseHeal();
                    useDice = true;
                    ok = false;
                } else {
                    alive = false;
                }

            });
            frame.add(button);

            JButton button1 = new JButton("GO UNDERGROUND");
            button1.setBounds(40, 455, 160, 35);
            button1.addActionListener(l -> {
                if (place == house && health > 0) {
                    place = houseToUnderground;
                    text.setText("");
                    enemy = quickAvocado;
                    text.setText("шшш, вы встетили быстрое авокадо!\n");
                    QuickAvocado.quickAvocadoHeal();
                    useDice = true;
                    ok = false;
                } else if (place == forest && health > 0) {
                    place = forestToUnderground;
                    text.setText("омг, вы встетили забвенный гриб!\n");
                    enemy = limboMushroom;
                    LimboMushroom.limboMushroomHeal();
                    useDice = true;
                    ok = false;
                } else {
                    alive = false;
                }

            });
            frame.add(button1);


            JButton button2 = new JButton("GO HOUSE");
            button2.setBounds(540, 455, 160, 35);
            button2.addActionListener(l -> {
                if (place == forest && health > 0) {
                    place = forestToHouse;
                    text.setText("пам - пабам, к вам выплыл кит-кидок!\n");
                    enemy = kitKidok;
                    LimboMushroom.limboMushroomHeal();
                    useDice = true;
                    ok = false;
                } else if (place == underground && health > 0) {
                    place = undergroundToHouse;
                    enemy = threeHorned;
                    text.setText("вау, вы встетили трирогого!\n");
                    ThreeHorned.threeHornedHeal();
                    useDice = true;
                    ok = false;
                } else {
                    alive = false;
                }
            });
            frame.add(button2);


            JButton button3 = new JButton("DICE");//forest, underground, house
            button3.setBounds(540, 510, 160, 35);
            button3.addActionListener(l -> {
                if (enemy == rukonog && useDice == true && rukonogHealth > 0 && health > 0) {
                    useDice = false;
                    dice = r.nextInt(6);
                    // 0=1; 1=2; 2=3; 3=4; 4=5; 5=6;
                    if (dice == 0) {
                        text.append("На кубике выпало 1!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 1) {
                        text.append("На кубике выпало 2!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 2) {
                        text.append("На кубике выпало 3!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 3) {
                        text.append("На кубике выпало 4!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 4) {
                        text.append("На кубике выпало 5!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    } else if (dice == 5) {
                        text.append("На кубике выпало 6!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    }
                    enemyDice = r1.nextInt(6);
                    if (enemyDice == 0) {
                        text.append("руконог бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 1) {
                        text.append("руконог бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 2) {
                        text.append("руконог бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 3) {
                        text.append("руконог бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 4) {
                        text.append("руконог поставил щит!\n");
                        enemyAttackType = enemyShield;
                    } else if (enemyDice == 5) {
                        text.append("руконог поставил щит!\n");
                        enemyAttackType = enemyShield;
                    }
                    if (attacktype == longAttack && enemyAttackType == enemyShield) {
                        rukonogHealth -= strength;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                        text.append("Вы выйграли, у руконога " + rukonogHealth + " хп");
                    } else if (attacktype == shield && enemyAttackType == enemyShortAttack) {
                        rukonogHealth -= strength;
                        text.append("Вы выйграли, у руконога " + rukonogHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyLongAttack) {
                        rukonogHealth -= strength;
                        text.append("Вы выйграли, у руконога " + rukonogHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shield && enemyAttackType == enemyLongAttack) {
                        health -= rukonogStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyShield) {
                        health -= rukonogStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == longAttack && enemyAttackType == enemyShortAttack) {
                        health -= rukonogStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else {
                        text.append("Ничего не произошло\n");
                    }
                    ok = true;


                }else if(enemy == limboMushroom && health>0 && limboMushroomIsAlive() == true && useDice == true){
                    ok = false;
                    dice = r.nextInt(6);
                    if (dice == 0) {
                        text.append("На кубике выпало 1!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 1) {
                        text.append("На кубике выпало 2!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 2) {
                        text.append("На кубике выпало 3!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 3) {
                        text.append("На кубике выпало 4!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 4) {
                        text.append("На кубике выпало 5!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    } else if (dice == 5) {
                        text.append("На кубике выпало 6!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    }
                    enemyDice = r1.nextInt(6);
                    if (enemyDice == 0) {
                        text.append("забвенный гриб бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 1) {
                        text.append("забвенный гриб бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 2) {
                        text.append("забвенный гриб бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 3) {
                        text.append("забвенный гриб бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 4) {
                        text.append("забвенный гриб поставил щит!\n");
                        enemyAttackType = enemyShield;
                    } else if (enemyDice == 5) {
                        text.append("забвенный гриб поставил щит!\n");
                        enemyAttackType = enemyShield;
                    }
                    if (attacktype == longAttack && enemyAttackType == enemyShield) {
                        limboMushroomHealth -= strength;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                        text.append("Вы выйграли, у забвенного гриба  " + limboMushroomHealth + " хп");
                    } else if (attacktype == shield && enemyAttackType == enemyShortAttack) {
                        limboMushroomHealth -= strength;
                        text.append("Вы выйграли, у забвенного гриба " + limboMushroomHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyLongAttack) {
                        limboMushroomHealth -= strength;
                        text.append("Вы выйграли, у забвенного гриба " + limboMushroomHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shield && enemyAttackType == enemyLongAttack) {
                        health -= limboMushroomStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyShield) {
                        health -= limboMushroomStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == longAttack && enemyAttackType == enemyShortAttack) {
                        health -= limboMushroomStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else {
                        text.append("Ничего не произошло\n");
                    }
                    ok = true;

                }else if(enemy == threeHorned && health>0 && threeHornedIsAlive() == true && useDice == true){
                    ok = false;
                    dice = r.nextInt(6);
                    if (dice == 0) {
                        text.append("На кубике выпало 1!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 1) {
                        text.append("На кубике выпало 2!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 2) {
                        text.append("На кубике выпало 3!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 3) {
                        text.append("На кубике выпало 4!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 4) {
                        text.append("На кубике выпало 5!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    } else if (dice == 5) {
                        text.append("На кубике выпало 6!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    }
                    enemyDice = r1.nextInt(6);
                    if (enemyDice == 0) {
                        text.append("трирогий бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 1) {
                        text.append("трирогий бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 2) {
                        text.append("трирогий бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 3) {
                        text.append("трирогий бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 4) {
                        text.append("трирогий поставил щит!\n");
                        enemyAttackType = enemyShield;
                    } else if (enemyDice == 5) {
                        text.append("трирогий поставил щит!\n");
                        enemyAttackType = enemyShield;
                    }
                    if (attacktype == longAttack && enemyAttackType == enemyShield) {
                        threeHornedHealth -= strength;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                        text.append("Вы выйграли, у трирогого  " + threeHornedHealth + " хп");
                    } else if (attacktype == shield && enemyAttackType == enemyShortAttack) {
                        threeHornedHealth -= strength;
                        text.append("Вы выйграли, у трирогого " + threeHornedHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyLongAttack) {
                        threeHornedHealth -= strength;
                        text.append("Вы выйграли, у трирогого " + threeHornedHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shield && enemyAttackType == enemyLongAttack) {
                        health -= threeHornedStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyShield) {
                        health -= threeHornedStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == longAttack && enemyAttackType == enemyShortAttack) {
                        health -= threeHornedStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else {
                        text.append("Ничего не произошло\n");
                    }
                    ok = true;



                }else if(enemy == quickAvocado && health>0 && quickAvocadoIsAlive() == true && useDice == true){
                    ok = false;
                    dice = r.nextInt(6);
                    if (dice == 0) {
                        text.append("На кубике выпало 1!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 1) {
                        text.append("На кубике выпало 2!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 2) {
                        text.append("На кубике выпало 3!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 3) {
                        text.append("На кубике выпало 4!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 4) {
                        text.append("На кубике выпало 5!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    } else if (dice == 5) {
                        text.append("На кубике выпало 6!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    }
                    //start
                    enemyDice = r1.nextInt(6);
                    if (enemyDice == 0) {
                        text.append("быстрое авокадо бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 1) {
                        text.append("быстрое авокадо бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 2) {
                        text.append("быстрое авокадо бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 3) {
                        text.append("быстрое авокадо бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 4) {
                        text.append("быстрое авокадо поставил щит!\n");
                        enemyAttackType = enemyShield;
                    } else if (enemyDice == 5) {
                        text.append("быстрое авокадо поставил щит!\n");
                        enemyAttackType = enemyShield;
                    }
                    if (attacktype == longAttack && enemyAttackType == enemyShield) {
                        quickAvocadoHealth -= strength;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                        text.append("Вы выйграли, у быстрого авокадо  " + quickAvocadoHealth + " хп");
                    } else if (attacktype == shield && enemyAttackType == enemyShortAttack) {
                        quickAvocadoHealth -= strength;
                        text.append("Вы выйграли, у быстрого авокадо " + quickAvocadoHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyLongAttack) {
                        quickAvocadoHealth -= strength;
                        text.append("Вы выйграли, у быстрого авокадо " + quickAvocadoHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shield && enemyAttackType == enemyLongAttack) {
                        health -= threeHornedStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyShield) {
                        health -= quickAvocadoStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == longAttack && enemyAttackType == enemyShortAttack) {
                        health -= quickAvocadoStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else {
                        text.append("Ничего не произошло\n");
                    }
                    ok = true;
                    //stop
                }else if(enemy == reedGoose && health>0 && reedGooseIsAlive() == true && useDice == true){
                    ok = false;
                    dice = r.nextInt(6);
                    if (dice == 0) {
                        text.append("На кубике выпало 1!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 1) {
                        text.append("На кубике выпало 2!\n");
                        text.append("Вы бьёте длинным ударом!\n");
                        attacktype = longAttack;
                    } else if (dice == 2) {
                        text.append("На кубике выпало 3!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 3) {
                        text.append("На кубике выпало 4!\n");
                        text.append("Вы бьёте коротким ударом!\n");
                        attacktype = shortAttack;
                    } else if (dice == 4) {
                        text.append("На кубике выпало 5!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    } else if (dice == 5) {
                        text.append("На кубике выпало 6!\n");
                        text.append("Вы ставите щит!\n");
                        attacktype = shield;
                    }
                    //start
                    enemyDice = r1.nextInt(6);
                    if (enemyDice == 0) {
                        text.append("камышовый гусь бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 1) {
                        text.append("камышовый гусь бьёт длинным ударом!\n");
                        enemyAttackType = enemyLongAttack;
                    } else if (enemyDice == 2) {
                        text.append("камышовый гусь бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 3) {
                        text.append("камышовый гусь бьёт коротким ударом!\n");
                        enemyAttackType = enemyShortAttack;
                    } else if (enemyDice == 4) {
                        text.append("камышовый гусь поставил щит!\n");
                        enemyAttackType = enemyShield;
                    } else if (enemyDice == 5) {
                        text.append("камышовый гусь поставил щит!\n");
                        enemyAttackType = enemyShield;
                    }
                    if (attacktype == longAttack && enemyAttackType == enemyShield) {
                        reedGooseHealth -= strength;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                        text.append("Вы выйграли, у камышового гуся  " + reedGooseHealth + " хп");
                    } else if (attacktype == shield && enemyAttackType == enemyShortAttack) {
                        reedGooseHealth -= strength;
                        text.append("Вы выйграли, у камышового гуся " + reedGooseHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyLongAttack) {
                        reedGooseHealth -= strength;
                        text.append("Вы выйграли, у камышового гуся " + reedGooseHealth + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shield && enemyAttackType == enemyLongAttack) {
                        health -= threeHornedStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == shortAttack && enemyAttackType == enemyShield) {
                        health -= reedGooseStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else if (attacktype == longAttack && enemyAttackType == enemyShortAttack) {
                        health -= reedGooseStrength;
                        text.append("вы проиграли, у вас осталось " + health + " хп");
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    } else {
                        text.append("Ничего не произошло\n");
                    }
                    ok = true;
                    //stop
                }

            });
            frame.add(button3);

            JButton button4 = new JButton("OK");
            button4.setBounds(300, 580, 160, 35);
            button4.addActionListener(l -> {
                if (ok == true && health > 0 && rukonogIsAlive() == true && enemy == rukonog) {
                    ok = false;
                    text.setText("");
                    text.append("Что же будет дальше?\n");
                    useDice = true;
                } else if (enemy == rukonog && rukonogIsAlive() == false && ok == true) {
                    text.setText("");
                    text.append("руконог повержен, вы получили 5 монет!\n");
                    money += 5;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = forest;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы находитесь в лесу.\n");
                    dice = r.nextInt(1);
                    if(dice ==0 && Cheats.godMode == true){
                        text.append("Вы нашли мёд,\nкоторый восстановил ваше здоровье!\n");
                        health = maxHealth;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    }
                    System.out.println(dice);
                    text.append("Куда вы отправитесь теперь?\n");
                }else if (ok == true && health > 0 && limboMushroomIsAlive() == true && enemy == limboMushroom) {
                    ok = false;
                    text.setText("");
                    text.append("Что же будет дальше?\n");
                    useDice = true;
                }else if (limboMushroomIsAlive() == false && ok == true && enemy == limboMushroom) {
                    text.setText("");
                    text.append("забвенный гриб повержен, вы получили 7 монет!\n");
                    money += 7;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = underground;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы находитесь в подземелье.\n");
                }else if (threeHornedIsAlive() == false && ok == true && enemy == threeHorned) {
                    text.setText("");
                    text.append("трирогий повержен, вы получили 8 монет!\n");
                    money += 8;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = house;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы находитесь в подземелье.\n");
                }else if (kitKidokIsAlive() == false && ok == true && enemy == kitKidok) {
                    text.setText("");
                    text.append("кит-кидок, вы получили 8 монет!\n");
                    money += 8;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = house;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы попали в магазин!\n");
                }else if (ok == true && health > 0 && threeHornedIsAlive() == true && enemy == threeHorned) {
                    ok = false;
                    text.setText("");
                    text.append("Что же будет дальше?\n");
                    useDice = true;
                }else if (quickAvocadoIsAlive() == false && ok == true && enemy == quickAvocado) {
                    text.setText("");
                    text.append("быстрое авокадо, вы получили 6 монет!\n");
                    money += 6;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = underground;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы попали в подземелье!\n");
                }else if (ok == true && health > 0 && quickAvocadoIsAlive() == true && enemy == quickAvocado) {
                    ok = false;
                    text.setText("");
                    text.append("Что же будет дальше?\n");
                    useDice = true;
                }else if (reedGooseIsAlive() == false && ok == true && enemy == reedGoose) {
                    text.setText("");
                    text.append("камышовый гусь повержен, вы получили 10 монет!\n");
                    
                    money += 10;
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    useDice = false;
                    place = forest;
                    drawingArea.repaint();
                    ok = false;
                    text.append("Вы попали в лес!\n");
                    dice = r.nextInt(1);
                    if(dice ==0 && Cheats.godMode == true){
                        text.append("Вы нашли мёд,\nкоторый восстановил ваше здоровье!\n");
                        health = maxHealth;
                        text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    }
                    text.append("Куда вы отправитесь дальше?\n");
                }else if(ok == true && health > 0 && reedGooseIsAlive() == true && enemy == reedGoose){
                    ok = false;
                    text.setText("");
                    text.append("Что же будет дальше?\n");
                    useDice = true;
                }

            });
            frame.add(button4);

            JButton respawn = new JButton("RESPAWN");
            respawn.setBounds(120, 580, 160, 35);
            respawn.addActionListener(l->{
                if(alive == false || health<=0){
                    alive = true;
                    health = 3;
                    maxHealth = 3;
                    strength = 1;
                    place = underground;
                    money = 0;
                    useDice = false;
                    ok = false;
                    text.setText("");
                    System.out.println("The end?");
                    text1.setText("     Statistic\n\nstrength = " + strength + "\n\nhealth = " + health + "\n\nmoney = " + money);
                    text.append("Вы проснулсь в каком-то подземелье.\nКуда вы пойдёте?\n");

                }
            });
            frame.add(respawn);



            frame.setVisible(true);
        } else {
            System.out.println("You died");
            alive = false;
            //TODO: after each "u have x hp" if(hp<0) --> u should respawn
            //TODO: it is hard XD
        }
    }


    static class DrawingArea extends JPanel {
        Color color = new Color(255, 255, 255);
        int size1 = 550;
        int size2 = 400;

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.WHITE);
            g.fillRect(50, 50, size1, size2);
            g.drawImage(img, 200, 60, null);
            g.setColor(Color.BLACK);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 18));
            g.drawString("FOREST", 200, 180);

                                                    //не запускается
            g.drawImage(img1, 80, 270, null);
            g.drawString("UNDERGROUND", 80, 390);

            g.drawImage(img2, 400, 170, null);
            g.drawString("SHOP", 400, 290);

            g.drawImage(img3, 150, 170, null);

            g.drawImage(img5,120,170,null);

            g.drawImage(img6,190,240,null);

            g.drawImage(img7,190, 220,null);

            g.drawImage(img8,300,100,null);

            g.drawImage(img9,320,75,null);


            if(place == forest || place == forestToHouse || place == forestToUnderground){
                g.drawImage(img10,140,80,null);

            }else if(place == house|| place == houseToForest || place == houseToUnderground){
                g.drawImage(img10,420,120,null);

            }else if(place == underground || place == undergroundToForest || place == undergroundToHouse){
                g.drawImage(img10,220,320,null);

            }

        }
    }
    }





