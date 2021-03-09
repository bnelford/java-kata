import java.util.Arrays;
import java.util.Collections;
import java.util.Random;

class DnDCharacter {
    int strength = 0;
    int dexterity = 0;
    int constitution = 0;
    int intelligence = 0;
    int wisdom = 0;
    int charisma = 0;
    int hitPoints = 0;

    DnDCharacter() {
        this.strength = ability();
        this.dexterity = ability();
        this.constitution = ability();
        this.intelligence = ability();
        this.wisdom = ability();
        this.charisma = ability();
        this.hitPoints = 10+modifier(constitution);
    }


    int ability() {
        Random generator = new Random(System.currentTimeMillis());
        int abilityInt = 0;
        //initialize
        Integer[] rolls = {0,0,0,0};
        //populate
        for (int i = 0; i<4; i++) {
            rolls[i] = (int)(generator.nextDouble() * 5) + 1;
        }
        //negate min to sum
        int min = Collections.min(Arrays.asList(rolls));
        for (int i = 0; i<4; i++) {
            if (rolls[i] == min) {
                rolls[i] = 0;
                break;
            }
        }
        //add up remaining totals
        for (int i = 0; i<4; i++) {
            abilityInt = abilityInt + rolls[i];
        }
        return abilityInt;
    }

    int modifier(int input) {
        int number = 0;
        double stepOne = (double)(input - 10);
        double stepTwo = stepOne/2;
        number = (int)Math.floor(stepTwo);
        return number;
    }

    int getStrength() {
        return this.strength;
    }

    int getDexterity() {
        return this.dexterity;
    }

    int getConstitution() {
        return this.constitution;
    }

    int getIntelligence() {
        return this.intelligence;
    }

    int getWisdom() {
        return this.wisdom;
    }

    int getCharisma() {
        return this.charisma;
    }

    int getHitpoints() {
        return this.hitPoints;
    }

}
