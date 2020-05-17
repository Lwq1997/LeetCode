package com.lwq;

import java.util.Random;

public class weChatRandomMoney {

    public static double getRandomMoney(LeftMoneyPackage _leftMoneyPackage) {
        // remainSize 剩余的红包数量
        // remainMoney 剩余的钱
        if (_leftMoneyPackage.remainSize == 1) {
            _leftMoneyPackage.remainSize--;
            return (double) Math.round(_leftMoneyPackage.remainMoney * 100) / 100;
        }
        Random r = new Random();
        double min = 0.01; //
        double max = _leftMoneyPackage.remainMoney / _leftMoneyPackage.remainSize * 2;


        double money = r.nextDouble() * max;
        money = money <= min ? 0.01 : money;
        money = Math.floor(money * 100) / 100;
        _leftMoneyPackage.remainSize--;
        _leftMoneyPackage.remainMoney -= money;
        return money;
    }
    public static void main(String[] args) {
        LeftMoneyPackage moneyPackage = new LeftMoneyPackage();
        moneyPackage.remainMoney = 0.03;
        moneyPackage.remainSize = 2;

        while (moneyPackage.remainSize != 0) {
            System.out.print(getRandomMoney(moneyPackage) + "   ");

        }
    }

    private static class LeftMoneyPackage {
        public double remainMoney;
        public Integer remainSize;
    }
}