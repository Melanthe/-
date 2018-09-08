package task1;

import common.MyExceptions;

public class Task1 {
    public static void main(String[] argh){

        try {
            Taylor tl = new Taylor();
            tl.init();
            tl.sum();
            tl.standartSum();
            tl.show();
        }
        catch(MyExceptions e){
            System.out.println(e);
        }
        catch(NumberFormatException e){
            System.out.println("Invalid input!");
        }
        catch(Exception e){
            System.out.println("Error!");
        }
    }
}
