class Leap {

    boolean isLeapYear(int year) {
        boolean result = true;
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
            result = false;
            }
        } else {
            result = false;
        }
        return result;
    }
}