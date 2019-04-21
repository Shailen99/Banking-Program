import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;

public class UserFunctions {
    Scanner sc = new Scanner(System.in);
    boolean validated = false;

    void SignUp()
    {
        boolean start = false;

        System.out.println("Please enter a username");
        String username = sc.next();

        //Compare inputted username to already kept files
        ArrayList<String> fileNames = new ArrayList<String>();
        File[] directory = new File("src/TextFiles").listFiles();

        for(int i = 0; i < directory.length; i++)
        {
            fileNames.add(directory[i].toString());
            //Converting directory into an arraylist of strings
            String usercheck = "src/TextFiles/" + username + ".txt";
            //Removing other parts of the string to only include the value of the name
            if(fileNames.contains(usercheck))
            {
                start = true;
                while(start)
                {
                    System.out.println("Username already in use");
                    System.out.println("Please enter a username");
                    username = sc.next();
                    usercheck = "src/TextFiles/" + username + ".txt";

                    if(fileNames.contains(usercheck))
                    {
                    }
                    else
                    {
                        break;
                    }

                }
            }
        }

        //Validating Username
        System.out.println("Please validate the given username");
        String _username = sc.next();
        System.out.println();

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

        if(password.equals(_password))
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
            String fileName = "/Users/shailensheth/IdeaProjects/BankingProgram/src/TextFiles/" + username + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write(password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void Exit()
    {
        System.exit(0);
    }

    void Login() {
        boolean loggedMenuIsActive = true;
        boolean verify = false;
        System.out.println("Log In");
        System.out.println("Username:");
        String username = sc.next();

        //Entering Password
        System.out.println();
        System.out.println("Password:");
        String Password = sc.next();

        //Verifying Username

        boolean passwordValid = false;
        boolean usernameValid = false;

        ArrayList<String> fileNames = new ArrayList<String>();
        File[] directory = new File("src/TextFiles").listFiles();

        for (int i = 0; i < directory.length; i++) {
            fileNames.add(directory[i].toString());
            //Converting directory into an arraylist of strings
        }
        String UsernameValidation = "src/TextFiles/" + username + ".txt";

        //Checking if this is a valid username
        if (fileNames.contains(UsernameValidation)) {
            usernameValid = true;
        } else {
            verify = true;
            while (verify) {
                System.out.println("Username is invalid");
            }

        }
        String strCurrentLine = Password;

        //Verifying Password
        try {

            BufferedReader objReader = new BufferedReader(new FileReader(UsernameValidation));
            String a = objReader.readLine();

            if (a.equals(Password)) {
                System.out.println("You are now logged in");
                passwordValid = true;
            }
            // if(strCurrentLine.equals(Password))

            objReader.close();

        } catch (IOException e) {

            e.printStackTrace();

        }
        //Log In Menu

        Scanner scan = new Scanner(System.in);
        Scanner depositValue = new Scanner(System.in);
        Scanner withdrawValue = new Scanner(System.in);

        if (passwordValid == true && usernameValid == true) {
            while (loggedMenuIsActive) {
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Exit");

                String action = scan.next();

                switch (action) {
                    case "1":
                        System.out.println("How much money would you like to enter into your account?");
                        String despositPrint = depositValue.next();

                        System.out.println("+" + despositPrint + " has been added to your account");

                        break;
                    case "2":
                        System.out.println("How much money would you like to take from your account?");
                        String withdrawPrint = withdrawValue.next();

                        System.out.println("+" + withdrawPrint + " has been added to your account");

                        break;
                    case "3":
                        System.out.println("Thanks for Using");
                        System.exit(0);
                        break;

                }
            }
        }
        else if(passwordValid == true && usernameValid != true)
        {
            System.out.println("Sorry but your username is invalid");
        }
        else if(passwordValid != true && usernameValid != true)
        {
            System.out.println("Both Username and Password are invalid");
        }

        else if(passwordValid != true && usernameValid == true)
        {
            System.out.println("Sorry but Password is invalid");
        }
    }
}