import java.util.Scanner;

public class Main {

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
                    usefunctions.Login();

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
