import java.util.Scanner;

public class Main {

    /*
 Ask the user if they want to sign up or log in. Do nothing for now for log in

If they choose sign up, allow them to enter in a username and password.  Double Validate both these entries.

Once they have entered the info, create a new text file titled as their username, and the first line of that file should be their password.

     */
    public static void main(String[] args) {
        UserFunctions usefunctions = new UserFunctions();
        boolean menuIsActive = true;
        Scanner sc = new Scanner(System.in);
        while(menuIsActive)
        {
            System.out.println("1. Log In");
            System.out.println("2. Sign Up");
            System.out.println("3. Exit Program");

            String choice = sc.next();

            switch(choice)
            {
                case "1":
                    System.out.println("Sorry, this function has not been implemented!");
                    break;
                case "2":
                    usefunctions.SignUp();
                    break;
                case "3":
                    System.out.println("Thank You for Using");
                    usefunctions.Exit();
                    break;
            }
        }

    }
}
