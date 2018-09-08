package common;

public class MyExceptions extends Exception{

    private String st;

    public MyExceptions(String st){
        this.st = st;
    }

    @Override
    public String toString() {
        return st;
    }
};
