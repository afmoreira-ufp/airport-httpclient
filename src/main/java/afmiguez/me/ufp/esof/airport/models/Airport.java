package afmiguez.me.ufp.esof.airport.models;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Airport extends BaseModel{
    private String name;

    private City city;

    public Airport(String name) {
        this.setName(name);
    }
}
