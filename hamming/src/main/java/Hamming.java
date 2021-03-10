public class Hamming {
    String leftStrand = new String();
    String rightStrand = new String();
    int hamming = 0;

    public Hamming(String leftStrand, String rightStrand) {
        this.leftStrand = leftStrand;
        this.rightStrand = rightStrand;
        this.hamming = getHammingDistance();
    }

    public int getHammingDistance() {
        int distance = 0;
        if (leftStrand.length() != rightStrand.length()) {
            if (leftStrand.length()==0){
                throw new IllegalArgumentException("left strand must not be empty.");
            }
            if (rightStrand.length()==0){
                throw new IllegalArgumentException("right strand must not be empty.");
            }
            throw new IllegalArgumentException("leftStrand and rightStrand must be of equal length.");
        }
        else {
            if (leftStrand.length() == 0 || rightStrand.length() == 0) {
                return distance;
            }
            else {
                for (int i=0; i < leftStrand.length(); i++) {
                    if (leftStrand.charAt(i) != rightStrand.charAt(i)) {
                        distance++;
                    }
                }
            }
        }
        return distance;
    }
}
