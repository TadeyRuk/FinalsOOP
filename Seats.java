import java.io.*;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class Seats extends Tickets {
    Scanner sc = new Scanner(System.in);
    private int VIPseats;
    private int REGseats;
    private List<List<String>> seatArrangement;
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

        seatArrangement = new ArrayList<>();
        for (int i = 0; i < rows; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < cols; j++) {
                row.add("[0]");
            }
            seatArrangement.add(row);
        }
    }

    public boolean bookSeat(int seatType) {
        int totalCols = seatArrangement.get(0).size();
        int vipEndRow = (VIPseats + totalCols - 1) / totalCols;
        
        while (currentRow < seatArrangement.size()) {
            boolean isVIPSection = currentRow < vipEndRow;
            
            if (currentCol >= totalCols) {
                currentCol = 0;
                currentRow++;
                continue;
            }

            if (seatArrangement.get(currentRow).get(currentCol).equals("[0]")) {
                if ((seatType == 1 && isVIPSection && getVIPSeats() > 0) || 
                    (seatType == 2 && !isVIPSection && getRegSeats() > 0)) {
                    seatArrangement.get(currentRow).set(currentCol, "[X]");
                    
                    if (isVIPSection) {
                        setVIPSeats(getVIPSeats() - 1);
                    } else {
                        setRegSeats(getRegSeats() - 1);
                    }

                    currentCol++;
                    if (currentCol >= totalCols) {
                        currentCol = 0;
                        currentRow++;
                    }
                    return true;
                }
            }
            currentCol++;
            if (currentCol >= totalCols) {
                currentCol = 0;
                currentRow++;
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
            for (int j = 0; j < seatArrangement.get(i).size(); j++) {
                if (seatArrangement.get(i).get(j).equals("[0]")) {
                    System.out.print("[V] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
        
        System.out.println("\nRegular Section ([R] Available, [X] Occupied):");
        for (int i = 2; i < seatArrangement.size(); i++) {
            System.out.printf("Row %d: ", i + 1);
            for (int j = 0; j < seatArrangement.get(i).size(); j++) {
                if (seatArrangement.get(i).get(j).equals("[0]")) {
                    System.out.print("[R] ");
                } else {
                    System.out.print("[X] ");
                }
            }
            System.out.println();
        }
    }

    public void saveSeatsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            writer.write(VIPseats + " " + REGseats + "\n");
            for (List<String> row : seatArrangement) {
                writer.write(String.join(" ", row) + "\n");
            }
        } catch (IOException e) {
            System.out.println("Error saving seats: " + e.getMessage());
        }
    }

    public void loadSeatsFromFile(String filename) {
        File file = new File(filename);
        if (!file.exists()) {
            initializeDefaultSeating();
            return;
        }

        try (Scanner scanner = new Scanner(file)) {
            if (scanner.hasNextLine()) {
                String[] counts = scanner.nextLine().split(" ");
                VIPseats = Integer.parseInt(counts[0]);
                REGseats = Integer.parseInt(counts[1]);
            }
            
            seatArrangement.clear();
            while (scanner.hasNextLine()) {
                String[] seats = scanner.nextLine().split(" ");
                List<String> row = new ArrayList<>();
                for (String seat : seats) {
                    row.add(seat);
                }
                seatArrangement.add(row);
            }
        } catch (Exception e) {
            System.out.println("Error loading seats: " + e.getMessage());
            initializeDefaultSeating();
        }
    }

    private void initializeDefaultSeating() {
        seatArrangement = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            List<String> row = new ArrayList<>();
            for (int j = 0; j < 5; j++) {
                row.add("[0]");
            }
            seatArrangement.add(row);
        }
    }

    public int getRegSeats() {
        return REGseats;
    }

    public int getVIPSeats() {
        return VIPseats;
    }

    public void setVIPSeats(int VIPseats) {
        this.VIPseats = VIPseats;
    }

    public void setRegSeats(int REGseats) {
        this.REGseats = REGseats;
    }
}