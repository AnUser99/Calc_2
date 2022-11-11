public class Main {
    public static void main(String[] args) throws Exception {
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        String input = scanner.nextLine();
        System.out.println(calc(input));
    }

    public static String calc(String input) throws Exception {
        String[] operands = input.split("[+\\-*/]");
        if (operands.length != 2) throw new Exception("Invalid input expression!");
        char operand = input.charAt(operands[0].length());
        operands[0] = operands[0].trim();
        operands[1] = operands[1].trim();
        CountingSystem countingSystem = typeOfCountingSystem(operands);
        int a = stringToInt(operands[0], countingSystem);
        int b = stringToInt(operands[1], countingSystem);
        int result = switch (operand) {
            case '+' -> a + b;
            case '-' -> a - b;
            case '*' -> a * b;
            case '/' -> a / b;
            default -> throw new Exception("Invalid input expression!");
        };
        if (countingSystem == CountingSystem.Arabic) return String.valueOf(result);
        if (result < 1) throw new Exception("Invalid result!");
        final String[] ONES = {"", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX", "X"};
        final String[] TENS = {"", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC", "C"};
        return TENS[result / 10] + ONES[result % 10];
    }

    enum Romans {Nothing, I, II, III, IV, V, VI, VII, VIII, IX, X;}

    static int stringToInt(String n, CountingSystem cs) throws Exception {
        int result = cs == CountingSystem.Arabic ? Integer.parseInt(n) : Romans.valueOf(n).ordinal();
        if (result < 1 || result > 10) throw new Exception("Invalid input expression!");
        return result;
    }

    enum CountingSystem {Arabic, Roman;}

    static CountingSystem typeOfCountingSystem(String[] operands) throws Exception {
        CountingSystem cs = typeOfCountingSystem(operands[0]);
        if (cs != typeOfCountingSystem(operands[1])) throw new Exception("Invalid input expression!");
        return cs;
    }

    static CountingSystem typeOfCountingSystem(String operand) {
        return Character.isDigit(operand.charAt(0)) ? CountingSystem.Arabic : CountingSystem.Roman;
    }
}