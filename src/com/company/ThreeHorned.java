package com.company;

public class ThreeHorned {
    static int threeHornedMaxHealth = 3;
    static int threeHornedHealth = threeHornedMaxHealth;
    static int threeHornedStrength = 2;


    public static void threeHornedHeal(){
        threeHornedHealth = threeHornedMaxHealth;
    }
    public static  boolean threeHornedIsAlive(){
        if(threeHornedHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}