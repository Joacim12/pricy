package rest;

import com.google.gson.Gson;
import control.Control;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author joacim
 */
@Path("price")
public class PriceResource {
    
    Gson gson = new Gson();
    Control control = new Control();
    
    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PriceResource
     */
    public PriceResource() {
    }

    /**
     * Retrieves representation of an instance of rest.PriceResource
     * @param kg
     * @param country
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{kg}/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJson(@PathParam("kg") String kg,@PathParam("country") String country) {
        return Response.ok()
                .entity(gson.toJson(control.getPrice(country, kg)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();       
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllCountries() {
        return Response.ok()
                .entity(gson.toJson(control.getAllCountries()))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();       
    }
    
     @GET
     @Path("{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCountry(@PathParam("country") String country) {
        return Response.ok()
                .entity(gson.toJson(control.getCountry(country)))
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();       
    }
    
    
    
    

    /**
     * PUT method for updating or creating an instance of PriceResource
     * @param content representation for the resource
     * @return 
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        System.out.println(content);
    }
}
