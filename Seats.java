import java.io.*;
public class Seats extends Tickets {
    private int VIPseats;
    private int REGseats;
    private String[][] seatArrangement;

    public Seats(double VIPTickets, double RegTickets, int VIPseats, int REGseats, int rows, int cols) {
        super(VIPTickets, RegTickets);
        this.VIPseats = VIPseats;
        this.REGseats = REGseats;
        seatArrangement = new String[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                seatArrangement[i][j] = "[0]";
            }
        }
    }

    public boolean bookSeat(int type) {
        if (type == 1 && VIPseats > 0) {
            for (int i = 0; i < seatArrangement.length; i++) {
                for (int j = 0; j < seatArrangement[i].length; j++) {
                    if (seatArrangement[i][j].equals("[0]")) {
                        seatArrangement[i][j] = "[X]";
                        VIPseats--;
                        return true;
                    }
                }
            }
        } else if (type == 2 && REGseats > 0) {
            for (int i = 0; i < seatArrangement.length; i++) {
                for (int j = 0; j < seatArrangement[i].length; j++) {
                    if (seatArrangement[i][j].equals("[0]")) {
                        seatArrangement[i][j] = "[X]";
                        REGseats--;
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void displaySeats() {
        System.out.println("\nVIP Section:");
        for (int i = 0; i < seatArrangement.length / 2; i++) {
            for (String seat : seatArrangement[i]) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }

        System.out.println("\nRegular Section:");
        for (int i = seatArrangement.length / 2; i < seatArrangement.length; i++) {
            for (String seat : seatArrangement[i]) {
                System.out.print(seat + " ");
            }
            System.out.println();
        }
    }

    public void loadSeatsFromFile(String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            int row = 0;
            while ((line = reader.readLine()) != null && row < seatArrangement.length) {
                String[] seatData = line.split(" ");
                System.arraycopy(seatData, 0, seatArrangement[row], 0, seatData.length);
                row++;
            }
        } catch (IOException e) {
            System.out.println("\nError loading seats: " + e.getMessage());
        }
    }

    public void saveSeatsToFile(String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String[] row : seatArrangement) {
                writer.write(String.join(" ", row));
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("\nError saving seats: " + e.getMessage());
        }
    }

    public int getVIPSeats() {
        return VIPseats;
    }

    public int getRegSeats() {
        return REGseats;
    }
}