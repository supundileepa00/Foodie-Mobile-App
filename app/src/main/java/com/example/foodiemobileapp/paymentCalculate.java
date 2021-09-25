package com.example.foodiemobileapp;

public class paymentCalculate {

    public int payByCoins(int availableCoins, int amount){
        if(availableCoins > amount){
            int newCoinsAmount = availableCoins - amount;
            return newCoinsAmount;
        }
        else{
            return -99;
        }
    }


    public int addCoins(int currentCoinsBal, int addCoinsAmount){
        //add 10% coins more when adding coins
        int newCoinsAmount = currentCoinsBal + addCoinsAmount + addCoinsAmount*10/100;

        return newCoinsAmount;
    }

}
