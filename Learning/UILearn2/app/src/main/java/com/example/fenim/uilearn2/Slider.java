package com.example.fenim.uilearn2;

public class Slider {
    private String name;
    private Integer val;

    public Slider(String name, Integer val) {
        this.name = name;
        this.val = val;
    }

    public String getName() {
        return name;
    }

    public void setName(String myString) {
        this.name = myString;
    }

    public Integer getVal() {
        return val;
    }

    public void setVal(Integer val) {
        this.val = val;
    }

}
