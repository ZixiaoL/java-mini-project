package edu.nyu.cs9053.homework9;

public class Latte implements CoffeeDrink {

    @Override public boolean isDecaf() {
        return false;
    }

    @Override public boolean containsMilk() {
        return true;
    }

}
