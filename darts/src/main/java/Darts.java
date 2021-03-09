class Darts {

    double distance = 0.0;
    Darts(double x, double y) {
        //get the distance from the center
        distance = Math.sqrt(Math.pow(x,2)+(Math.pow(y,2)));
    }

    int score() {
        if (this.distance <= 10) {
            if (this.distance <= 5) {
                if (this.distance <= 1) {
                    return 10;
                }
                return 5;
            }
            return 1;
        }
        else {
            return 0;
        }
    }
}
