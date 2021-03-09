import java.math.BigInteger;

class Grains {

    BigInteger grainsOnSquare(final int square) {
        //throw error on invalid square
        if (square < 1 || square > 64) {
            throw new IllegalArgumentException("square must be between 1 and 64");
        }

        //take the square number, return double the last number
        //square 1, has one grain of rice = (2^0) = 1
        //square 2 has two grains  (2^1) = 2
        //square 3 has 4 grains = (2^2) = 4
        //square 4 has 8 grains = (2^3) = 8
        //square 5 has 16 grains = (2^4) = 16
        //formula is Math.pow(2,squareNUm)

        //set the base value
        BigInteger b1 = new BigInteger("2");
        //take in the input integer as a power, shift to 0 based index, create new BigInteger with .pow method
        return b1.pow(square-1);
    }

    BigInteger grainsOnBoard() {
        //loop through each square, 0-63, and add the result to the total
        BigInteger total = new BigInteger("0");
        for (int i = 1; i<65; i++) {
            total = total.add(grainsOnSquare(i));
        }
        return total;
    }

}
