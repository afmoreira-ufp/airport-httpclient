package afmiguez.me.ufp.esof.airport.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
public class City extends BaseModel{
    private String name;

    @ToString.Exclude
    @JsonIgnore
    @EqualsAndHashCode.Exclude
    private Airport airport;

    public City(String name) {
        this.setName(name);
    }

    public void setAirport(Airport airport){
        this.airport=airport;
        airport.setCity(this);
    }

}
