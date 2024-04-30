package numbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

class AmazingNumber{
    private long number;
    static public String[] properties = {"BUZZ", "DUCK", "PALINDROMIC", "GAPFUL", "SPY", "SQUARE",
                                        "SUNNY", "JUMPING", "SAD", "HAPPY", "EVEN", "ODD"};

    AmazingNumber(long number){
        this.number = number;
    }

    public boolean isOdd(){
        return this.number % 2 == 1 ? true : false;
    }

    public boolean isBuzz(){
        // is ending with seven
        String s = Long.toString(this.number);
        if(s.charAt(s.length() - 1) == '7') return true ;
        // is divisible by seven
        return this.number % 7 == 0 ? true : false ;
    }

    public boolean isDuck(){
        return String.valueOf(this.number).contains("0") ? true : false;
    }

    public boolean isPalindromic(){
        String reverse = new StringBuilder(String.valueOf(this.number)).reverse().toString();
        return String.valueOf(this.number).equals(reverse);
    }

    public boolean isGapful(){
        String strNumber = String.valueOf(this.number);
        if (strNumber.length() >= 3) {
            int firstDigit = Character.getNumericValue(strNumber.charAt(0));
            int lastDigit = Character.getNumericValue(strNumber.charAt(strNumber.length() - 1));
            int concatenated = Integer.parseInt(String.valueOf(firstDigit) + String.valueOf(lastDigit));
            return this.number % concatenated == 0 ? true : false;
        } else {
            return false;
        }
    }

    public boolean isSpy(){
        String s = String.valueOf(this.number);
        int[] intArray = new int[s.length()];
        for (int i = 0; i < s.length(); i++) {
            intArray[i] = Character.getNumericValue(s.charAt(i));
        }
        int sum = 0;
        int prod = 1;
        for (int i = 0; i < intArray.length; i++) {
            sum += intArray[i];
            prod *= intArray[i];
        }
        if (sum == prod) return true;
        return false;
    }

    public boolean isSquare(){
        double squareRoot = Math.sqrt(this.number);
        double roundedSquareRoot = Math.round(squareRoot);
        if (roundedSquareRoot == squareRoot) return true;
        return false;
    }

    public boolean isSunny(){
        double squareRoot = Math.sqrt(this.number + 1);
        double roundedSquareRoot = Math.round(squareRoot);
        if (roundedSquareRoot == squareRoot) return true;
        return false;
    }

    public boolean isJumping(){
        int[] digits = String.valueOf(this.number).chars().map(Character::getNumericValue).toArray();
        if (digits.length == 1) return true;
        for (int i = 0; i < digits.length - 1; i++) {
            if (Math.abs(digits[i] - digits[i + 1]) != 1) {
                return false;
            }
        }
        return true;
    }

    private long doHappySequence(long number) {
        // get all the digits
        char[] chars = Long.toString(number).toCharArray();

        long sum = 0;
        for (int i = 0; i < chars.length; i++) {
            long x = Character.getNumericValue(chars[i]);
            sum += x * x;
        }
        return sum;
    }

    public boolean isHappy(){
        long value = this.number;
        do{
            value = doHappySequence(value);
        }while(value != 1 && value != 4);

        if (value == 1) return true;
        return false;
    }

    public boolean isSad(){
        return !isHappy();
    }

    private String addProperty(String txt, String property){
        String s;
        if(txt.length() == 0){
            s = String.format("%d is %s", this.number, property.toLowerCase());
        }else{
            s = String.format("%s, %s", txt, property.toLowerCase());
        }
        return s;
    }

    public void printShortStatement(){
        String s = "";
        for(String property : properties){
            if(isKey(property)) s = addProperty(s, property);
        }
        System.out.println(s);
    }

    public boolean isKey(String key){
        switch(key.toUpperCase()){
            case "EVEN":
                if(!this.isOdd()) return true;
                break;
            case "ODD":
                if(this.isOdd()) return true;
                break;
            case "BUZZ":
                if(this.isBuzz()) return true;
                break;
            case "DUCK":
                if(this.isDuck()) return true;
                break;
            case "PALINDROMIC":
                if(this.isPalindromic()) return true;
                break;
            case "GAPFUL":
                if(this.isGapful()) return true;
                break;
            case "SPY":
                if(this.isSpy()) return true;
                break;
            case "SQUARE":
                if(this.isSquare()) return true;
                break;
            case "SUNNY":
                if(this.isSunny()) return true;
                break;
            case "JUMPING":
                if(this.isJumping()) return true;
                break;
            case "HAPPY":
                if(this.isHappy()) return true;
                break;
            case "SAD":
                if(this.isSad()) return true;
                break;
        }
        return false;
    }

    public void printStatement(){
        System.out.printf("Properties of %d\n", this.number);
        for(String property: properties){
            switch(property){
                case "EVEN":
                    System.out.printf("        even: %b\n", !this.isOdd());
                    break;
                case "ODD":
                    System.out.printf("         odd: %b\n", this.isOdd());
                    break;
                case "BUZZ":
                    System.out.printf("        buzz: %b\n", this.isBuzz());
                    break;
                case "DUCK":
                    System.out.printf("        duck: %b\n", this.isDuck());
                    break;
                case "PALINDROMIC":
                    System.out.printf(" palindromic: %b\n", this.isPalindromic());
                    break;
                case "GAPFUL":
                    System.out.printf("      gapful: %b\n", this.isGapful());
                    break;
                case "SPY":
                    System.out.printf("         spy: %b\n", this.isSpy());
                    break;
                case "SQUARE":
                    System.out.printf("      square: %b\n", this.isSquare());
                    break;
                case "SUNNY":
                    System.out.printf("       sunny: %b\n", this.isSunny());
                    break;
                case "JUMPING":
                    System.out.printf("     jumping: %b\n", this.isJumping());
                    break;
                case "HAPPY":
                    System.out.printf("       happy: %b\n", this.isHappy());
                    break;
                case "SAD":
                    System.out.printf("         sad: %b\n", this.isSad());
                    break;
            }
        }
    }
}

class FilterParser{
    String[] propertyList = {};
    long number = -1;
    int count = 0;
    boolean ok = false;

    FilterParser(String[] properties){
        ok = true;
        try {
            if (properties.length == 0) {
                throw new IllegalArgumentException("No properties provided");
            }
            if(!setNumber(properties[0])) throw new IllegalArgumentException("No number definied");
        }catch (Exception e){
            System.out.println("\nThe first parameter should be a natural number or zero.");
            ok = false;
        }

        try {
            if (properties.length > 1) {
                if(!setCounter(properties[1])) throw new IllegalArgumentException("No counter definied");
            }
        }catch (Exception e){
            System.out.println("The second parameter should be a natural number.");
            ok = false;
        }

        try{
            if(properties.length > 2){
                propertyList = Arrays.copyOfRange(properties, 2, properties.length);
                // set to upper case
                for(int i = 0; i < propertyList.length; i++){
                    propertyList[i] = propertyList[i].toUpperCase();
                }
                for(String key: propertyList) {
                    if (!isValidProperty(key)) throw new IllegalArgumentException(key);
                }
            }
        }catch(Exception e){
            System.out.printf("The property [%s] is wrong.\n", e.getMessage().toUpperCase());
            System.out.printf("Available properties: %s", Arrays.toString(AmazingNumber.properties));
            ok = false;
        }
        // check if there are two excluding properties
        if(!checkExcludingProperty("ODD", "EVEN")) ok = false;
        if(!checkExcludingProperty("DUCK", "SPY")) ok = false;
        if(!checkExcludingProperty("SUNNY", "SQUARE")) ok = false;
        if(!checkExcludingProperty("SAD", "HAPPY")) ok = false;
        if(!checkExcludingProperty("-SAD", "-HAPPY")) ok = false;
        if(!checkExcludingProperty("-ODD", "-EVEN")) ok = false;
        for(String p: AmazingNumber.properties){
            if(!checkExcludingProperty(p, "-" + p)) ok = false;
        }

    }
    private boolean checkExcludingProperty(String p1, String p2){
        ArrayList<String> list = new ArrayList<String>();
        list.addAll(Arrays.asList(propertyList));
        if(list.contains(p1.toUpperCase()) && list.contains(p2.toUpperCase())){
            System.out.printf("The request contains mutually exclusive properties: [%s, %s]\n", p1, p2 );
            System.out.println("There are no numbers with these properties.");

            return false;
        }
        return true;
    }

    private boolean setNumber(String number) {
        this.number = Long.parseLong(number);
        if (this.number < 0) {
            return false;
        }
        return true;
    }

    private boolean setCounter(String counter) {
        this.count = Integer.parseInt(counter);
        if (this.count < 1) {
            return false;
        }
        return true;
    }

    boolean isValidProperty(String property){
        for(String p : AmazingNumber.properties){
            String negP = "-" + p;
            if (p.equals(property.toUpperCase()) || negP.equals(property.toUpperCase())) {
                return true;
            }
        }
        return false;
    }

    boolean filterNumber(AmazingNumber number){
        for(String key: propertyList){
            if(key.charAt(0) == '-'){
                String dummyKey = key.substring(1);
                if(number.isKey(dummyKey)) return false;
            }else {
                if (!number.isKey(key)) return false;
            }
        }
        return true;
    }

    void showNumbers(){
        if (count == 0) {
            AmazingNumber a = new AmazingNumber(number);
            a.printStatement();
        }else {
            int cnt = 0;
            int iterator = 0;
            do {
                AmazingNumber a = new AmazingNumber(number + iterator);
                if (filterNumber(a)) {
                    cnt++;
                    a.printShortStatement();
                }
                iterator++;
            } while (cnt < count);
        }
    }
}

public class Main {
    public static void main(String[] args) {
//        write your code here
        System.out.println("Welcome to Amazing Numbers!\n");
        System.out.println("Supported requests:");
        System.out.println("- enter a natural number to know its properties;");
        System.out.println("- enter two natural numbers to obtain the properties of the list:");
        System.out.println("  * the first parameter represents a starting number;");
        System.out.println("  * the second parameter shows how many consecutive numbers are to be printed;");
        System.out.println("- two natural numbers and properties to search for;");
        System.out.println("- a property preceded by minus must not be present in numbers;");
        System.out.println("- separate the parameters with one space;");
        System.out.println("- enter 0 to exit.");

        Scanner sc = new Scanner(System.in);


        long number = -1;
        int counter = 0;

        do {
            System.out.print("\nEnter a request: ");
            String s = sc.nextLine();
            String[] lst = s.split(" ");

            FilterParser filter = new FilterParser(lst);
            number = filter.number;
            counter = filter.count;
            if (!filter.ok) continue;

            if(number == 0) {
                continue;
            }
            filter.showNumbers();
            /*if(lst.length == 1) {
                AmazingNumber a = new AmazingNumber(number);
                a.printStatement();
                continue;
            }
            for(int i = 0; i < counter; i++) {
                AmazingNumber a = new AmazingNumber(number + i);
                a.printShortStatement();
            }*/
        }while(number != 0);

        System.out.println("\nGoodbye!");


    }
}
