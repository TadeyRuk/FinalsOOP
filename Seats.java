import java.util.Scanner;

public class Seats extends Tickets {
    Scanner sc = new Scanner(System.in);
    private int VIPseats;
    private int REGseats;
    private String[][] seatArrangement;
    private int currentRow = 0;
    private int currentCol = 0;

    public Seats(double VIPTickets, double RegTickets, int VIPseats, int REGseats, int rows, int cols) {
        super(VIPTickets, RegTickets);
        
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Rows and columns must be positive numbers");
        }
        
        if (VIPseats < 0 || REGseats < 0) {
            throw new IllegalArgumentException("Number of seats cannot be negative");
        }
        
        if (VIPseats + REGseats > rows * cols) {
            throw new IllegalArgumentException("Total seats cannot exceed available space");
        }
        
        this.VIPseats = VIPseats;
        this.REGseats = REGseats;

        seatArrangement = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seatArrangement[i][j] = "[0]";
            }
        }
    }

    public boolean bookSeat() {
        while (currentRow < seatArrangement.length) {
            if (seatArrangement[currentRow][currentCol].equals("[0]")) {
                seatArrangement[currentRow][currentCol] = "[X]";
                currentCol++;
                if (currentCol >= seatArrangement[0].length) {
                    currentCol = 0;
                    currentRow++;
                }
                return true;
            } else {
                currentCol++;
                if (currentCol >= seatArrangement[0].length) {
                    currentCol = 0;
                    currentRow++;
                }
            }
        }
        System.out.println("No more seats available");
        return false;
    }

    public void displaySeats() {
        System.out.println("=== Seating Arrangement ===");
        System.out.println("VIP Section ([V] Available, [X] Occupied):");
        
        for (int i = 0; i < 2; i++) {
            System.out.printf("Row %d: ", i + 1);
            for (int j = 0; j < seatArrangement[i].length; j++) {
                if (seatArrangement[i][j].equals("[0]")) {
                    System.out.print("[V] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
        
        System.out.println("\nRegular Section ([R] Available, [X] Occupied):");
        for (int i = 2; i < seatArrangement.length; i++) {
            System.out.printf("Row %d: ", i + 1);
            for (int j = 0; j < seatArrangement[i].length; j++) {
                if (seatArrangement[i][j].equals("[0]")) {
                    System.out.print("[R] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
    }

    public int getRegSeats() {
        return REGseats;
    }

    public int getVIPSeats() {
        return VIPseats;
    }

    public void setVIPseats(int VIPseats) {
        this.VIPseats = VIPseats;
    }

    public void setRegSeats(int REGseats) {
        this.REGseats = REGseats;
    }
}