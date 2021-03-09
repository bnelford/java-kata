class ResistorColorDuo {
    int value(String[] colors) {
        String result = "";
        String color = new String();
        for (int i = 0; i <= 1; i++) {
            color = colors[i];
            result = result.concat(Integer.toString(colorCode(color)));
        }
        return Integer.valueOf(result);
    }

    int colorCode(String color) {
        String[] colorArray = colors();
        int index = -1;
        for (int i = 0; i < colorArray.length; i++) {
            if (color == colorArray[i]) {
                index = i;
            }
            continue;
        }
        return index;
    }

    String[] colors() {
        String[] colorArray = {"black", "brown", "red", "orange", "yellow", "green", "blue", "violet", "grey", "white"};
        return colorArray;
    }
}
