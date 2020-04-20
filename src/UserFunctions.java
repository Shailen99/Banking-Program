import javax.sound.sampled.Line;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.rmi.registry.LocateRegistry;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;

public class UserFunctions {
    Scanner sc = new Scanner(System.in);
    boolean validated = false;

    void SignUp() {
        boolean start = false;

        System.out.println("Please enter a username");
        String username = sc.next();

        //Compare inputted username to already kept files
        ArrayList<String> fileNames = new ArrayList<String>();
        File[] directory = new File("src/TextFiles").listFiles();

        for (int i = 0; i < directory.length; i++) {
            fileNames.add(directory[i].toString());
            //Converting directory into an arraylist of strings
            String usercheck = "src/TextFiles/" + username + ".txt";
            //Removing other parts of the string to only include the value of the name
            if (fileNames.contains(usercheck)) {
                start = true;
                while (start) {
                    System.out.println("Username already in use");
                    System.out.println("Please enter a username");
                    username = sc.next();
                    usercheck = "src/TextFiles/" + username + ".txt";

                    if (fileNames.contains(usercheck)) {
                    } else {
                        break;
                    }
                }
            }
        }

        //Validating Username
        System.out.println("Please validate the given username");
        String _username = sc.next();
        System.out.println();

        if (username.equals(_username)) {
        } else {
            validated = true;
            System.out.println("WRONG");
            while (validated) {
                System.out.println("Please validate the given username");
                _username = sc.next();
                if (username.matches(_username)) {
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

        if (password.equals(_password)) {
        } else {
            validatePassword = true;
            while (validatePassword) {
                System.out.println("Not Matching");
                System.out.println("Please validate the password:");
                _password = sc.next();

                if (password.matches(_password)) {
                    break;
                }
            }

        }
        //Print all data in a text file
        System.out.println("All Done! Your information should be in a text file.");
        try {
            String fileName = "src/TextFiles/" + username + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            writer.write(password);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    void Exit() {
        System.exit(0);
    }

    void Login() {
        boolean loggedMenuIsActive = false;
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


        //Checking if File EXISTS
        if (fileNames.contains(UsernameValidation)) {
            usernameValid = true;
            System.out.println("Username is valid");
        } else {
            System.out.println("Inputs do not exist for Username and Password");
            usernameValid = false;
            passwordValid = false;

        }


        //Verifying Password
        if (usernameValid) {
            try {

                BufferedReader objReader = new BufferedReader(new FileReader(UsernameValidation));
                String a = objReader.readLine();

                if (a.equals(Password)) {
                    System.out.println("You are now logged in");
                    passwordValid = true;
                    usernameValid = true;
                }

                objReader.close();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //Log In Menu

        Scanner scan = new Scanner(System.in);
        Scanner depositValue = new Scanner(System.in);
        Scanner withdrawValue = new Scanner(System.in);


        if (passwordValid == true && usernameValid == true) {
            int LineNumber = 0;
            String line;
            loggedMenuIsActive = true;

            while (loggedMenuIsActive) {
                int total = 0;
                try {
                    BufferedReader br = new BufferedReader(new FileReader(UsernameValidation));
                    br.readLine();


                    line = br.readLine();

                    //Checking Total Balance Value

                    while (line != null && line.length() > 0) {
                        LineNumber++; //Counts number of transactions
                        char c = line.charAt(0);
                        if (c == 43) //deposit
                        {
                            charRemoveAt(line, 0);
                            int DepositValue = Integer.parseInt(line);

                            total = total + DepositValue;

                        } else if (c == 45) //withdraw
                        {
                            charRemoveAt(line, 0);
                            int WithdrawValue = Integer.parseInt(line);
                            WithdrawValue = WithdrawValue * -1;
                            total = total - WithdrawValue;
                        }
                        line = br.readLine();
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }

                //Main Menu
                System.out.println("Current Total: " + total);
                System.out.println("1. Deposit Money");
                System.out.println("2. Withdraw Money");
                System.out.println("3. Exit and Save Total Balance");

                String action = scan.next();
                ArrayList<Integer> DepositValues = new ArrayList<Integer>();
                ArrayList<Integer> WithdrawValues = new ArrayList<Integer>();

                switch (action) {
                    case "1":


                        System.out.println("How much money would you like to enter into your account?");
                        String depositPrint = depositValue.next();
                        if (depositPrint.matches("[0-9]+")) {

                            int depositVal = Integer.parseInt(depositPrint);
                            DepositValues.add(depositVal);

                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(UsernameValidation, true));

                                writer.newLine();

                                writer.write("+" + depositVal);

                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Please Enter A Valid Amount");
                        }

                        break;
                    case "2":
                        System.out.println("How much money would you like to take from your account?");
                        String withdrawPrint = withdrawValue.next();

                        if (withdrawPrint.matches("[0-9]+")) {

                            int withdrawVal = Integer.parseInt(withdrawPrint);

                            WithdrawValues.add(withdrawVal);

                            System.out.println(total);
                            try {
                                BufferedWriter writer = new BufferedWriter(new FileWriter(UsernameValidation, true));

                                writer.newLine();

                                writer.write("-" + withdrawVal);

                                writer.close();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } else {
                            System.out.println("Please Enter A Valid Amount");
                        }

                        break;
                    case "3":
                        System.out.println("Thanks for Using");
                        //Adds total 10 lines below the latest transaction

                        LineNumber = LineNumber + 1;
                        try {
                            BufferedWriter writer = new BufferedWriter(new FileWriter(UsernameValidation, true));
                            LineNumberReader lnr = new LineNumberReader(new FileReader(UsernameValidation));
                            lnr.setLineNumber(LineNumber);


                            for (int i = 1; i <= lnr.getLineNumber(); i++) {
                                writer.newLine();
                            }
                            writer.write("=" + total);
                            writer.close();
                            lnr.close();

                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        System.exit(0);
                        break;
                }

            }
        } else if (passwordValid != true && usernameValid == true) {
            System.out.println("Sorry but Password is invalid");
        }

    }

    public static String charRemoveAt(String str, int p) {
        return str.substring(0, p) + str.substring(p + 1);
    }
}
