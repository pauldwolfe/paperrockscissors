package com.github.bartjohn;

public class ConstantStrategy implements Strategy {
    private int selection;

    public ConstantStrategy(int selection) {
        this.selection = selection;
    }

    public int execute() {
        return selection;
    }
}
