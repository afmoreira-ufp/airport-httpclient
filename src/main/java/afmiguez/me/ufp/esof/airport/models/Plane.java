package afmiguez.me.ufp.esof.airport.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data

@NoArgsConstructor
public class Plane extends BaseModel{
    private String model;
    private String serialNumber;
    private int flightHours;

    private Set<Flight> flights=new HashSet<>();

    private Set<Seat> seats=new HashSet<>();

    public Plane(String model,String serialNumber) {
        this.setModel(model);
        this.setSerialNumber(serialNumber);
    }

    public void addFlight(Flight flight){
        this.flights.add(flight);
        flight.setPlane(this);
    }

    public void addSeat(Seat seat){
        this.seats.add(seat);
    }

}
