package com.company;

public class QuickAvocado {
    static int quickAvocadoMaxHealth = 1;
    static int quickAvocadoHealth = quickAvocadoMaxHealth;
    static int quickAvocadoStrength = 3;


    public static void quickAvocadoHeal(){

        quickAvocadoHealth = quickAvocadoMaxHealth;
    }
    public static  boolean quickAvocadoIsAlive(){
        if(quickAvocadoHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}
