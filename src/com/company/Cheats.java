package com.company;

import java.util.Scanner;
//TODO: kill enemy, kill yourself,
public class Cheats extends Thread{
    static boolean godMode = false;
    public void run(){
        Scanner scan = new Scanner(System.in);
        String command = scan.nextLine();
        if(command.equals("god")&& godMode == false){
            godMode = true;
            Main.health = 999999;
            Main.strength = 999999;
            Main.money = 999999;
            command = scan.nextLine();

        }else if(command.equals("god")&& godMode == true){
            godMode = false;
            Main.health = Main.maxHealth;
            System.out.println("God mode is disabled");
            command = scan.nextLine();
        }else if(command.equals("ez")){
            switch (Main.enemy){
                case rukonog:
                    Ruconog.rukonogHealth = 0;
                    return;
                case limboMushroom:
                    LimboMushroom.limboMushroomHealth = 0;
                    return;
                case kitKidok:
                    KitKidok.kitKidokHealth = 0;
                    return;
                case reedGoose:
                    ReedGoose.reedGooseHealth = 0;
                    return;
                case threeHorned:
                    ThreeHorned.threeHornedHealth = 0;
                    return;
                case quickAvocado:
                    QuickAvocado.quickAvocadoHealth = 0;
                    return;
            }
            System.out.println("Killed!");
            command = scan.nextLine();
        }else if(command.equals("suicide")){
            Main.health = 0;
            System.out.println("killed");
            command = scan.nextLine();
        }
        }
    }


