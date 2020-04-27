package com.gmail.programmerfromearth.controller.utils;

public enum Value {
    ONE(1), TWO(2), THREE(3), FOUR(4), FIVE(5);

    private Integer value;

    Value(Integer value) {
        this.value = value;
    }

    public Integer getValue() {
        return value;
    }
}
