package com.company;

public class LimboMushroom {
    static int limboMushroomMaxHealth = 2;
    static int limboMushroomHealth = limboMushroomMaxHealth;
    static int limboMushroomStrength = 2;


    public static void limboMushroomHeal(){

        limboMushroomHealth = limboMushroomMaxHealth;
    }
    public static  boolean limboMushroomIsAlive(){
        if(limboMushroomHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}