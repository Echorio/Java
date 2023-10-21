public class Passenger {
    private String name;
    private int iD;
    private Flight flight;
    private boolean checkedIn;

    public Passenger(String name, int iD, Flight flight) {
        this.name = name;
        this.iD = iD;
        this.flight = flight;
        this.checkedIn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getiD() {
        return iD;
    }

    public void setiD(int iD) {
        this.iD = iD;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public boolean isCheckedIn() {
        return checkedIn;
    }

    public void setCheckedIn(boolean checkedIn) {
        this.checkedIn = checkedIn;
    }
}
    
    