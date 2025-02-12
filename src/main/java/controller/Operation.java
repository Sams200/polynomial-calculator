package controller;

public enum Operation {
    /*
    The operations this program is capable of
     */
    ADD(0),
    SUBTRACT(1),
    MULTIPLY(2),
    DIVIDE(3),
    DIFFERENTIATE(4),
    INTEGRATE(5);

    private final int number;

    Operation(int number){
        this.number=number;
    }

    public static Operation getInstance(int number){
        return switch (number){
            case 1 -> SUBTRACT;
            case 2 -> MULTIPLY;
            case 3 -> DIVIDE;
            case 4 -> DIFFERENTIATE;
            case 5 -> INTEGRATE;
            default -> ADD;
        };
    }
    public int getNumber(){
        return this.number;
    }
}
