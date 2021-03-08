class ReverseString {

    String reverse(String inputString) {
        String resultString = new String();
        if (inputString == "" || inputString == null) {
            resultString = "";
        }
        else {
            char[] inputStringArray = inputString.toCharArray();
            int adjustedLength = inputString.length()-1;
            for (int i=adjustedLength; i>-1; i--) {
                resultString = resultString.concat(Character.toString(inputStringArray[i]));
            }
        }
        return resultString;
    }
}
