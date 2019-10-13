package afmiguez.me.ufp.esof.airport;

import afmiguez.me.ufp.esof.airport.models.Airport;
import afmiguez.me.ufp.esof.airport.models.BaseModel;
import afmiguez.me.ufp.esof.airport.models.Company;
import afmiguez.me.ufp.esof.airport.models.Flight;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import lombok.Getter;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;


public class MainClass {

    private enum HTTPMethod{
        GET,
        POST
    }

    private final HttpClient httpClient = HttpClient.newBuilder()
            .version(HttpClient.Version.HTTP_2)
            .build();

    private ObjectMapper objectMapper;

    public MainClass() {
        //for reading jdk8 time formats
        this.objectMapper =new ObjectMapper().registerModule(new JavaTimeModule());
        this.objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        this.objectMapper.setDateFormat(new StdDateFormat());
    }

    private List<?> getList(String json, Class<?> classZ){
        try {
            return objectMapper.readValue(json, objectMapper.getTypeFactory().constructCollectionType(List.class, classZ));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private BaseModel getObject(String json,Class<?> classz) throws JsonProcessingException {
        return (BaseModel) this.objectMapper.readValue(json,classz);
    }


    public static void main(String[] args) throws JsonProcessingException {
        MainClass mainClass=new MainClass();
        HttpResponse<String> airportsResponse=mainClass.sendRequest("http://127.0.0.1:8081/airport",HTTPMethod.GET,null);

        List<Airport> airports= (List<Airport>) mainClass.getList(airportsResponse.body(),Airport.class);
        System.out.println(airports);

        HttpResponse<String> flightsResponse=mainClass.sendRequest("http://127.0.0.1:8081/flight",HTTPMethod.GET,null);
        List<Flight>flights= (List<Flight>) mainClass.getList(flightsResponse.body(),Flight.class);
        System.out.println(flights);

        Company companyToCreate=new Company();
        companyToCreate.setName("company1");


        HttpResponse<String> companyResponse=mainClass.sendRequest("http://127.0.0.1:8081/company",HTTPMethod.POST,companyToCreate);
        Company company= (Company) mainClass.getObject(companyResponse.body(),Company.class);
        System.out.println(company);

        companyResponse=mainClass.sendRequest("http://127.0.0.1:8081/company",HTTPMethod.POST,companyToCreate);
        company= (Company) mainClass.getObject(companyResponse.body(),Company.class);
        System.out.println(company);
    }

    private HttpResponse<String> sendRequest(String url,HTTPMethod method,BaseModel payLoadString) throws JsonProcessingException {

        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
//                .GET()
                .uri(URI.create(url))
                .setHeader("User-Agent", "Java 11 HttpClient Bot"); // add request header
//                .build();


        if(method.equals(HTTPMethod.GET)){
            requestBuilder=requestBuilder.GET();
        }else if(method.equals(HTTPMethod.POST)){
            requestBuilder.setHeader("Content-Type","application/json");
            HttpRequest.BodyPublisher payLoad=HttpRequest.BodyPublishers.ofString(this.objectMapper.writeValueAsString(payLoadString));
            requestBuilder=requestBuilder.POST(payLoad);
        }

        HttpRequest request=requestBuilder.build();



        HttpResponse<String> response = null;
        try {
            response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            HttpHeaders headers = response.headers();
            headers.map().forEach((k, v) -> System.out.println(k + ":" + v));

            // print status code
            System.out.println(response.statusCode());

            // print response body
            System.out.println(response.body());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        // print response headers
        return response;

    }


}
