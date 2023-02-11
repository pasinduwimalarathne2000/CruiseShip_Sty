import java.io.*;
import java.util.*;
/**Arrays solution of the Cruise Ship Boarding.
 * Pasindu Wimalarathne
 * w1868270
 * 2022.04.15*/

public class Cruise_array{
        static String cabName,cabSurname;
        static int cabNum = 0;
        static int amount;
        static String[][] ship = new String[13][3];/** adding 2d array */
        /**Main methods and running menu functionalities implemented*/
        public static void main(String[] args) {
            /* Initializing variables */
            Scanner responce = new Scanner(System.in);
            boolean Q = true;
            initialise(ship);
            System.out.println();
            System.out.println();

            /* try catch inside the while true loop */
            while (Q) {
                try {
                    display();
                    System.out.println("Enter the Letter :  ");
                    String variable = responce.next().toUpperCase(Locale.ROOT);

                    switch (variable) {
                        case "A":
                            Add();
                            break;
                        case "V":
                            View();
                            break;
                        case "E":
                            EmptyCabin();
                            break;
                        case "D":
                            DeleteCabin();
                            break;
                        case "F":
                            FindCabin();
                            break;
                        case "S":
                            StoreCabin(ship);
                            break;
                        case "L":
                            LoadCabin();
                            break;
                        case "O":
                            alphabetically();
                            break;
                        case "X":
                            Q = false;
                            break;
                        default:
                            System.out.println("| Incorrect input |");
                            System.out.println();
                    }
                } catch (Exception e) {
                    System.out.println("| Letter is incorrect |");
                }

            }
            System.out.println();
        }


        /** Method to view the menu */
        public static void display() {
            System.out.println("````````````````````````````````````````````````````");
            System.out.println("Welcome to Cruise Ship Boarding!!!");
            System.out.println("A: Add a customer to a cabin");
            System.out.println("V: View all cabins");
            System.out.println("E: Display Empty cabin");
            System.out.println("D: Delete customer from cabin");
            System.out.println("F: Find cabin from customer name");
            System.out.println("S: Store program data into file");
            System.out.println("L: Load program data from file");
            System.out.println("O: View passengersOrdered alphabetically by name.");
            System.out.println("X: Exit ");
            System.out.println();
            System.out.println("```````````````````````````````````````````````````");
        }


        /**  Method to initialise all the cabins are available */
        public static void initialise(String shipRef[][]) {
            for (int x = 0; x < shipRef.length; x++) {

                for (int y = 0; y < shipRef[x].length; y++) {
                    shipRef[x][y] = "e";
                }

            }
        }


        /** Method to view already taken cabins */
        public static void View() {
            for (int x = 1; x < ship.length; x++) {
                if (ship[x][0].equals("e")) {
                    System.out.println("Cabin " + x + " is empty ");
                } else {
                    System.out.println("Cabin " + x + " occupied by " + ship[x][0]);

                }
            }
            System.out.println("...................................................");
        }


        /** Method to add customers in the cabin */
        public static void Add() {
            try {
                Scanner responce = new Scanner(System.in);
                System.out.println("Enter cabin number (1-12) or Exit to press X :");
                cabNum = responce.nextInt();
                if (cabNum!=13){

                    /** if condition to check the cabins that are already occupied */
                    if (ship[cabNum][0].equals("e")) {

                        System.out.println("Enter first name for cabin " + cabNum+ " :");
                        cabName = responce.next();

                        System.out.println("Enter surname for cabin " + cabNum + " :");
                        cabSurname = responce.next();

                        System.out.println("Enter the Expenses of customer " + cabNum + " :");
                        amount = responce.nextInt();
                        String price = java.lang.String.valueOf(amount);
                        System.out.println("!!Customer Added Successfully!!");

                        ship[cabNum][0]= cabName;
                        ship[cabNum][1]= cabSurname;
                        ship[cabNum][2]= price;

                    } else {
                        System.out.println("| Cabin is occupied |"); /** if user inputs same cabin name again */
                    }
                    System.out.println();
                }
                else {
                    System.exit(0);
                }


            } catch (Exception e) {
                System.out.println("| Enter a valid cabin number |"); /** if the cabin number is not between 1 - 12 */
                System.out.println();

            }
        }


        /** Method to delete a customer name from a list */
        public static void DeleteCabin() {
            Scanner responce = new Scanner(System.in);
            System.out.println("Name of the customer you want to delete ? ");
            String Revname = responce.next();

            for (int y = 0; (y < ship.length); y++) {

                for (int z = 0; z <= 4; z++) {

                    if (ship[y][0].equals(Revname)) {
                        ship[y][0] ="e";
                        ship[y][1] ="e";
                        ship[y][2] ="e";

                    }
                }
            }
            System.out.println("!!Delete Successfully!!");
        }


        /** Method to find the cabin of a customer */
        public static void FindCabin() {
            Scanner responce = new Scanner(System.in);
            System.out.println("Enter the name you want to find out ? ");
            String findCus = responce.next();

            for (int y = 0; (y < ship.length); y++) {

                if (ship[y][0].equals(findCus)) {
                    System.out.println("Cabin number is " + y);
                    System.out.println("Customer surname is " + ship[y][1]);
                    System.out.println("Customer Expenses are " + ship[y][2]);
                }
            }
            System.out.println();
        }

        /** Method to display available cabin numbers */
        public static void EmptyCabin() {
            for (int x = 1; x < 13; x++) {
                if (ship[x][0].equals("e")) System.out.println("Cabin " + x + " is empty ");
            }
            System.out.println("...................................................");
        }

        /** Method to save details to a file */
        public static void StoreCabin(String[][] ship) throws IOException {

            FileWriter save = new FileWriter("CabinStore.txt");
            for (int x = 1; x < 13; x++) {

                save.write("\n" +"Cabin "+ x  +"\n"+" -> Customer Name: " + ship[x][0] + "|" +  "|" + "Customer Surname :" + ship[x][1] +
                        "|"+"|"+ " Customer Expenses :" + ship[x][2] );

            }

            System.out.println("!!Successfully Saved Data!!");
            save.close();
            System.out.println();
        }

        /** Method to load details from the file */
        public static void LoadCabin() throws FileNotFoundException {

            FileReader save = new FileReader("CabinStore.txt");   /** read the file which saved the
                                                                      details in Store cabin */
            Scanner readercab = new Scanner(save);
            while (readercab.hasNextLine()) {
                String input = readercab.nextLine();
                System.out.println(input);

            }
            System.out.println("...................................................");
            readercab.close();

            System.out.println();


        }
        public static void alphabetically(){
            System.out.println("Odered Names:");
            for(int i=0;i<ship.length;i++){
                System.out.println(ship[i][0]);

        }
    }



}
