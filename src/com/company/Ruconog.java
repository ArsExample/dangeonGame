package com.company;

public class Ruconog {
    static int rukonogMaxHealth = 2;
    static int rukonogHealth = rukonogMaxHealth;
    static int rukonogStrength = 1;



    public static void rukonogHeal(){

        rukonogHealth = rukonogMaxHealth;
    }
    public static  boolean rukonogIsAlive(){
        if(rukonogHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}
//бульба