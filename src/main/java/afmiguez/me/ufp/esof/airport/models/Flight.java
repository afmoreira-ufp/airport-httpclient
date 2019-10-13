package afmiguez.me.ufp.esof.airport.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class Flight extends BaseModel {

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;

    private String flightNumber;

    private Airport departure;

    private Airport arrival;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Company company;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Pilot pilot;


    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JsonIgnore
    private Plane plane;

    private Set<Passenger> passengers=new HashSet<>();

    public Flight(LocalDate date, String flightNumber) {
        this.setDate(date);
        this.setFlightNumber(flightNumber);
    }

}
