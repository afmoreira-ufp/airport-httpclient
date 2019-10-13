package afmiguez.me.ufp.esof.airport.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Company extends BaseModel{
    private String name;

    private Set<Flight> flights=new HashSet<>();

    private Set<Pilot> pilots=new HashSet<>();

    public Company(String name) {
        this.setName(name);
    }

    public void addFlight(Flight flight){

        this.flights.add(flight);
        flight.setCompany(this);
    }

    public void addPilot(Pilot pilot){
        this.pilots.add(pilot);
        pilot.setCompany(this);
    }
}
