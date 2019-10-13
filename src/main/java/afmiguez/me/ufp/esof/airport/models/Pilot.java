package afmiguez.me.ufp.esof.airport.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Pilot extends BaseModel {
    private String name;

    @JsonIgnore
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Company company;

    public Pilot(String name) {
        this.name = name;
    }

    private Set<Flight> flights=new HashSet<>();

    public void addFlight(Flight flight){
        this.flights.add(flight);
        flight.setPilot(this);
    }
}
