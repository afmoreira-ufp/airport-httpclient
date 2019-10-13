package afmiguez.me.ufp.esof.airport.models;

import lombok.Data;

@Data

public class Seat extends BaseModel{
    private String local;


    private Passenger passenger;
}
