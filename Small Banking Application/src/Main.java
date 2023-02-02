import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        Scanner scan = new Scanner(System.in);

        String user_userName;
        int user_password;
        int counter = 0, tries =3;

        String[] userName = {"Tian", "Jacques", "Julia", "Bunny"};
        int[] password = {5535, 8543, 2022, 1234};

        System.out.println("Enter your username:");
        user_userName = scan.nextLine();

        //Using a for loop that will break when it finds the matching username entered that is in the array.
        for (int i = 0; i <4; i++) {

            if (userName[i].equals(user_userName)) {
                System.out.println("Username APPROVED.");
                System.out.println("Enter your password:");
                user_password = scan.nextInt();

                while (password[i] != user_password && counter != 2) {
                    counter +=1;
                    tries -= 1;
                    System.out.println("You have " + tries + " attempts remaining" );
                    System.out.println("Your password is incorrect");
                    user_password = scan.nextInt();
                }

                //This is completely unnecessary but im keeping it for the clarity.
                if (password[i] == user_password){
                    System.out.println("Your password was approved");
                    System.out.println("Welcome to your account "+ userName[i]);
                    String name_of_user = userName[i];
                    bankMenu(name_of_user);
                }else{
                    System.out.println("Incorrect password has been entered too many times. Bye Bye");
                }
                //To break the for loop after the matched username has been found and if the password matches or not it will break
                break;
            }
        }
        scan.close();
    }

    //Void that calls the banking menu, contains switches and loops and saves the transactions.
    public static void bankMenu(String name) throws IOException {
        Scanner scan = new Scanner(System.in);
        int option;
        double money, moneyAmount = 5000;

        //The options for the user to select
      do {
          System.out.println();
          System.out.println("Please select and option below:");
          System.out.println("1. Draw money");
          System.out.println("2. Deposit money");
          System.out.println("3. Check balance");
          System.out.println("4. Exit");
          option = scan.nextInt();

          //add while to make sure only 1 to 4 is accepted.
          switch (option) {
              case 1 -> {
                  System.out.println("How much do you want to withdraw?: Your current amount is R" + moneyAmount);
                  money = scan.nextDouble();
                  while (money > moneyAmount) {
                      System.out.println("The amount you are trying to withdraw exceeds your total amount available.");
                      System.out.println("Please try again.");
                      money = scan.nextDouble();
                  }
                  moneyAmount -= money;
              }
              case 2 -> {
                  System.out.println("How much do you want to deposit?: Your current amount is R" + moneyAmount);
                  money = scan.nextDouble();
                  while (money < 0) {
                      System.out.println("The amount you are trying to deposit is not accepted its a negative value");
                      System.out.println("Please try again.");
                      money = scan.nextDouble();
                  }
                  moneyAmount += money;
              }
              case 3 -> System.out.println("Your current amount is R" + moneyAmount);
              case 4 -> {System.out.println("Thank you for using our bank, have a great day, bye bye!");
                    printReceipt(moneyAmount, name);
              }
          }
         }while(option != 4);
        scan.close();
    }

    //Void that prints the receipt for the user
    public static void printReceipt(double balance, String name)throws IOException{

        //Create a Writer constructor to write the contents of out program to a textDoc.
        FileWriter write = new FileWriter("Receipt.txt");

        write.write("Mr/Ms " +name + " your balance is currently: R" + balance);

        write.close();
    }
}
