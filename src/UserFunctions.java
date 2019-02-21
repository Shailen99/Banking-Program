import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UserFunctions {

    ArrayList<User> Registered =new ArrayList <User>();
    Scanner sc = new Scanner(System.in);
    boolean validated = false;
    void SignUp()
    {
        System.out.println("Please enter a username");
        String username = sc.next();

        //Validating Username
        System.out.println("Please validate the given username");
        String _username = sc.next();

        if(username.equals(_username))
        {
        }
        else
        {
            validated = true;
            System.out.println("WRONG");
            while(validated)
            {
                System.out.println("Please validate the given username");
                _username = sc.next();

                if(username.matches(_username))
                {
                    break;
                }
            }

        }

        System.out.println("Username Validated");

        //Insert a password
        System.out.println("Please insert a password:");
        String password = sc.next();

        //Validating Password
        boolean validatePassword = false;
        System.out.println("Please validate password:");
        String _password = sc.next();

        if(password.equals(password))
        {

        }
        else{
            validatePassword = true;
            while(validatePassword)
            {
                System.out.println("Not Matching");
                System.out.println("Please validate the password:");
                _password = sc.next();

                if(password.matches(_password))
                {
                    break;
                }
            }

        }
        //Print all data in a text file
        System.out.println("All Done! Your information should be in a text file.");
        System.out.println("Exit the program in the main menu to see the file.");
        try {
            String fileName = username + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write(password);
            writer.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
