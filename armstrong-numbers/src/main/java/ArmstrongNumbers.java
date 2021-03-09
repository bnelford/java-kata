class ArmstrongNumbers {

    boolean isArmstrongNumber(int numberToCheck) {

        String numberAsString = Integer.toString(numberToCheck);
        //split number into an array of numbers
        char[] numberAsStringArray = numberAsString.toCharArray();

        int total = 0;
        //for loop for array length

        for (int i = 0; i < numberAsStringArray.length; i++) {
            //first digit raised to array length, add to total
            int number = Character.getNumericValue(numberAsStringArray[i]);
            total = total + (int) Math.pow((double)number,(double)numberAsStringArray.length);
        }
        return total == numberToCheck;
    }
}
