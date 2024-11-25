abstract class Tickets {
    private double VIPTickets;
    private double RegTickets;

    public Tickets (double VIPTickets, double RegTickets){
        this.VIPTickets = VIPTickets;
        this.RegTickets = RegTickets;
    }

    public double getVIPValue(){
        return VIPTickets;
        
    }

    public double getRegValue(){
        return RegTickets;
    }

}