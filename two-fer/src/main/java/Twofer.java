public class Twofer {
    public String twofer(String name) {
        if (name == null) {
            return "One for you, one for me.";
        }
        else {
            String result = "One for " + name + ", one for me.";
            return result;
        }
    }
}
