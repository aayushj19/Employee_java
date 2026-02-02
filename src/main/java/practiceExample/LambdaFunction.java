package practiceExample;

interface Animal{
    void sound(int i);
}

class Employee{
    public String name;
    public int age;
}

public class LambdaFunction{
    static void main(String[] args) {
        Animal Dog = (i)-> System.out.println("I am barking");
        Dog.sound(10);
    }
}