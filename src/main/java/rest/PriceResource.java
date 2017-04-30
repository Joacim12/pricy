package rest;

import com.google.gson.Gson;
import control.Control;
import entity.Country;
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
     *
     * @param kg
     * @param country
     * @return an instance of java.lang.String
     */
    @GET
    @Path("{kg}/{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPriceCountry(@PathParam("kg") String kg, @PathParam("country") String country) {
        return gson.toJson(control.getPrice(country, kg));
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getAllCountries() {
        return gson.toJson(control.getAllCountries());
    }

    @GET
    @Path("{country}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getCountry(@PathParam("country") String country) {
        return gson.toJson(control.getCountry(country));
    }

    /**
     * PUT method for updating or creating an instance of PriceResource
     *
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
        content = content.substring(0, content.lastIndexOf(":")) +content.substring(content.lastIndexOf(":"), content.length()).replace("\"", "");
        Country c = gson.fromJson(content, Country.class);
        control.updateCountry(c);
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public void postJson(String content) {
    }
}
