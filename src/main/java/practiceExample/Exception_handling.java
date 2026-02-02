package practiceExample;

import java.util.Scanner;


class MyException extends Exception{
    MyException(String message){
        super(message);
    }
}
class Exception_handling{
    static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter your age:");
            int age = sc.nextInt();
            if(age < 18){
                throw new MyException("You are not Eligible");
            }
        }catch(MyException e){
            System.out.println("Exception:" + e.getMessage());
        }
        finally{
            System.out.println("hey");
        }
        sc.close();


//        FileReader fr = new FileReader("file.txt"); --> FileNotFound Exception.

//        int [] arr = {2,3,4};
//        System.out.println(arr[3]); --> ArrayOutOfBound Exception.
    }
}