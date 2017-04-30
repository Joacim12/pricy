package control;

import entity.Country;
import facades.PriceFacade;
import java.util.List;
import jsonEntity.PriceOBJ;
import model.CalculatePrice;

/**
 *
 * @author joacim
 */
public class Control {
    CalculatePrice cp = new CalculatePrice();
    PriceFacade priceFacade = new PriceFacade("pu");
    
    
    public PriceOBJ getPrice(String country, String kgString){
        return cp.getPrice(country, kgString);
    }
    
    public List<Country> getAllCountries(){
        return priceFacade.getAllCountries();
    }
    
    public Country getCountry(String country){
        return priceFacade.getCountry(country);
    }
    
}
