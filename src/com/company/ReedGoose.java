package com.company;

public class ReedGoose {
    static int reedGooseMaxHealth = 2;
    static int reedGooseHealth = reedGooseMaxHealth;
    static int reedGooseStrength = 2;


    public static void reedGooseHeal(){

        reedGooseHealth = reedGooseMaxHealth;
    }
    public static  boolean reedGooseIsAlive(){
        if(reedGooseHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}