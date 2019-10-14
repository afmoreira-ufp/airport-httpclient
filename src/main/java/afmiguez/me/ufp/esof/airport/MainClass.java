package afmiguez.me.ufp.esof.airport;

import afmiguez.me.ufp.esof.airport.models.BaseModel;
import afmiguez.me.ufp.esof.airport.models.Company;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Class just to test http requests
 */
public class MainClass {

    public static void main(String[] args) {
        RestTemplate restTemplate=new RestTemplate();


        ResponseEntity<List> airportsResponse=restTemplate.getForEntity("http://127.0.0.1:8081/airport",List.class);
        System.out.println(airportsResponse);
        System.out.println(airportsResponse.getBody());


        ResponseEntity<List> flightsResponse=restTemplate.getForEntity("http://127.0.0.1:8081/flight",List.class);
        System.out.println(flightsResponse);
        System.out.println(flightsResponse.getBody());


        Company company=new Company();
        company.setName("company1");
        HttpHeaders headers = new HttpHeaders();
        HttpEntity<BaseModel> payload=new HttpEntity<>(company,headers);

        try{
            ResponseEntity<Company> companyResponseEntity=restTemplate.postForEntity("http://127.0.0.1:8081/company",payload,Company.class);
            System.out.println(companyResponseEntity);
            System.out.println(companyResponseEntity.getBody());
        }catch(HttpClientErrorException exc){
            System.out.println("Error");
        }

        try{
            ResponseEntity<Company> companyResponseEntity=restTemplate.postForEntity("http://127.0.0.1:8081/company",payload,Company.class);
            System.out.println(companyResponseEntity);
            System.out.println(companyResponseEntity.getBody());
        }catch(HttpClientErrorException exc){
            System.out.println("Error");
        }


    }

}
