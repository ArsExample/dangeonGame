package com.company;

public class KitKidok {
    static  int kitKidokMaxHealth = 5;
    static int kitKidokHealth = kitKidokMaxHealth;
    static int kitKidokStrength = 1;


    public static void kitKidokHeal(){

         kitKidokHealth = kitKidokMaxHealth;
    }
    public static  boolean kitKidokIsAlive(){
        if(kitKidokHealth<=0){
            return false;
        }else{
            return true;
        }

    }
}
