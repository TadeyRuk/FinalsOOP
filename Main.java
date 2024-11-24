import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean choiceValid = false; 
        Seats seats = new Seats(5000, 3500, 10, 15, 5, 5);
        seats.loadSeatsFromFile("seats.txt");

        while (!choiceValid) {
            try {
                System.out.println("+------------ TICKETING SYSTEM -----------+");
                System.out.println("| [1] - View ticket types and prices      |");
                System.out.println("| [2] - Check for seats                   |");
                System.out.println("| [3] - Book a ticket                     |");
                System.out.println("| [4] - Exit                              |");
                System.out.println("+-----------------------------------------+");
                
                System.out.print("Enter your choice: ");
                int MenuChoice = sc.nextInt();

                switch (MenuChoice) {
                    case 1:
                        System.out.println("[1] - VIP Ticket prices");
                        System.out.println("[2] - Regular Ticket prices");
                        System.out.print("Enter your choice: ");
                        int ticketChoice = sc.nextInt();
                        if (ticketChoice == 1) {
                            System.out.println("+------------------------------------+");
                            System.out.println("| VIP ticket price is " + seats.getVIPValue() + "         |");
                            System.out.println("+------------------------------------+");
                            System.out.println();
                        } else if (ticketChoice == 2) {
                            System.out.println("+------------------------------------+");
                            System.out.println("| Regular Ticket price is " + seats.getRegValue() + "     |");
                            System.out.println("+------------------------------------+");
                            System.out.println();
                        } else {
                            System.out.println("Invalid choice. Please enter 1 or 2.");
                        }
                        break;
                    case 2:
                        System.out.println();
                        System.out.println("+------------ Seats Available -------------+");
                        seats.displaySeats();
                        System.out.println();
                        break;
                    case 3:
                        try {
                            System.out.println("[1] - VIP");
                            System.out.println("[2] - Regular");
                            System.out.print("Select Ticket Type: ");
                            int ticketType = sc.nextInt();

                            if (ticketType != 1 && ticketType != 2) {
                                System.out.println("Invalid ticket type. Please select 1 for VIP or 2 for Regular.");
                                break;
                            }

                            if (ticketType == 1) {
                                if (seats.getVIPSeats() <= 0) {
                                    System.out.println("Sorry, no more VIP seats available.");
                                    break;
                                }
                                if (seats.bookSeat(1)) {
                                    System.out.println("VIP Seat booked successfully");
                                } else {
                                    System.out.println("Failed to book VIP seat. Please try again.");
                                }
                            } else {
                                if (seats.getRegSeats() <= 0) {
                                    System.out.println("Sorry, no more Regular seats available.");
                                    break;
                                }
                                if (seats.bookSeat(2)) {
                                    System.out.println("Regular Seat booked successfully");
                                } else {
                                    System.out.println("Failed to book Regular seat. Please try again.");
                                }
                            }
                        } catch (InputMismatchException e) {
                            System.out.println("Invalid input. Please enter a number.");
                            sc.next();
                        }
                        break;
                    case 4:
                        System.out.println("You are now exiting the program....");
                        choiceValid = true;
                        seats.saveSeatsToFile("seats.txt");
                        break;
                    default:
                        System.out.println("You have entered an invalid input. Please try again.");
                        break;
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid Input. Please enter a number");
                sc.next();
            }
        }
        sc.close();
    }
}