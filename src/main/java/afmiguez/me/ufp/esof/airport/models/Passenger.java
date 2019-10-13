package afmiguez.me.ufp.esof.airport.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Passenger extends BaseModel{
    private String name;
    private Flight flight;
    private Seat seat;
}
