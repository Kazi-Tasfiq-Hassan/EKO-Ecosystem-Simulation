// Base Class
class EcoEntity {
    protected int x;
    protected int y;
    protected int age;

    public EcoEntity(int x, int y, int age) {
        this.x = x;
        this.y = y;
        this.age = age;
    }

    public int getX() { return x; }
    public void setX(int x) { this.x = x; }
    public int getY() { return y; }
    public void setY(int y) { this.y = y; }
    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }

    // Polymorphic method to be overridden
    public void updateState(double hours) {
        System.out.println("Generic ecosystem entity updating state for " + hours + " hours.");
    }

    // Overriding toString() from Object
    @Override
    public String toString() {
        return "EcoEntity [x=" + x + ", y=" + y + ", age=" + age + "]";
    }
}

// Derived Class
class Animal extends EcoEntity {
    protected int water;
    protected String sex;

    public Animal(int x, int y, int age, int water, String sex) {
        super(x, y, age); // Calling base constructor using super()
        this.water = water;
        this.sex = sex;
    }

    public int getWater() { return water; }
    public void setWater(int water) { this.water = water; }
    public String getSex() { return sex; }
    public void setSex(String sex) { this.sex = sex; }

    @Override
    public void updateState(double hours) {
        super.updateState(hours); // Using super to call base method
        System.out.println("Animal base update for sex: " + sex);
    }

    @Override
    public String toString() {
        return super.toString() + ", water=" + water + ", sex=" + sex;
    }
}

// Derived Class
class Mammal extends Animal {
    protected boolean isResting; // Additional field extending functionality

    public Mammal(int x, int y, int age, int water, String sex, boolean isResting) {
        super(x, y, age, water, sex);
        this.isResting = isResting;
    }

    public boolean isResting() { return isResting; }
    public void setIsResting(boolean isResting) { this.isResting = isResting; }

    // Additional method extending functionality
    public void regulateTemperature() {
        System.out.println("Mammal is regulating its body temperature.");
    }

    @Override
    public void updateState(double hours) {
        super.updateState(hours);
        regulateTemperature();
    }

    @Override
    public String toString() {
        return super.toString() + ", isResting=" + isResting;
    }
}

class Jackrabbit extends Mammal {
    public static final int MAX_WATER = 100;
    private static int jackrabbit_created = 0;

    // Parameterized Constructor using super()
    Jackrabbit(int x, int y, int age, int water, String sex) {
        super(x, y, age, water, sex, false);
        jackrabbit_created++;
    }

    // Default Constructor using this() chaining
    Jackrabbit() {
        this(0, 0, 1, 60, "Female");
    }

    // Lab 2 Overloaded Method 1
    public void passTime(int hours){
        int waterdrop = (hours/2)*3;
        this.water -= waterdrop;
        if(water <= 0){
            this.water = 0;
        }
        System.out.println(hours + " hours has passed in daytime and the Jackrabbit (" + sex + ") lost " + waterdrop + " units of water and now his water level is " + this.water);
    }

    // Overloaded Method 2
    public void passTime(int hours, boolean isDaytime){
        int waterdrop = 0;
        if(isDaytime){
            waterdrop = (hours/2)*3;
        } else {
            waterdrop = hours * 1;
        }
        this.water -= waterdrop;
        if(water <= 0){
            this.water = 0;
        }
        System.out.println(hours + " hours has passed at night. The water level has decreased by " + waterdrop + ". Now the current water level is " + this.water);
    }

    public void drinkFromCactus() {
        this.water += 14;
        if (this.water > MAX_WATER) {
            this.water = MAX_WATER;
        }
        System.out.println("Jackrabbit bit a cactus and drank water. Current water: " + this.water + ".");
    }

    // Required Polymorphic Override
    @Override
    public void updateState(double hours) {
        int waterDrop = (int)((hours / 2.0) * 3);
        this.water -= waterDrop;
        if (this.water < 0) this.water = 0;
        System.out.println("Jackrabbit polymorphic update: passed " + hours + " hours. Water: " + this.water);
    }

    // Required toString Override
    @Override
    public String toString() {
        return "Jackrabbit {" + super.toString() + ", totalCreated=" + jackrabbit_created + "}";
    }

    public void println(){
        System.out.println("Current Status of Jackrabbit");
        System.out.println("X Position: " + this.x + " | Y Position: " + this.y);
        System.out.println("Water Level: " + this.water);
        System.out.println("Age: " + this.age);
        System.out.println("Sex: " + this.sex);
        System.out.println("Total Jackrabbit: " + jackrabbit_created);
    }
}

// TEST CLASS
public class JackrabbitProgram2 {
    public static void main(String[] args){
        Jackrabbit initial_jackrabbit = new Jackrabbit();
        Jackrabbit newjackrabbit = new Jackrabbit(2, 5, 20, 60, "Male");

        System.out.println("INITIAL CONDITION: ");
        initial_jackrabbit.println();
        newjackrabbit.println();

        System.out.println("EXECUTING SIMULATION METHODS: ");
        newjackrabbit.passTime(4);
        initial_jackrabbit.passTime(8, false);
        newjackrabbit.drinkFromCactus();

        System.out.println("DEMONSTRATING POLYMORPHISM (Requirement 8):");
        EcoEntity polymorphicRef = new Jackrabbit(1, 1, 2, 50, "Female");
        polymorphicRef.updateState(4.0);

        System.out.println("FINAL STATE");
        initial_jackrabbit.println();
        newjackrabbit.println();
    }
}