package test;

public class testBoolean {
    public static void main(String[] args) {

        /*
        * При использовании && - если первая часть лог. операции уже удовлетворяет условию,
        * то вторая даже не исполняется. В то время как & - всегда проверяем обе части
        * Аналогичная ситуация и с оператором | и ||
        */

        if(true | testbool()){
            System.out.println("123");
        }
    }


    private static boolean testbool(){
        System.out.println("Выполнили метод testbool()!");
        return false;
    }
}

