package entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author joaci
 */
@Entity
public class Country implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String name;
    private List<double[]> prices;

    public Country() {
    }

    public Country(String name, List<double[]> prices) {
        this.name = name;
        this.prices = prices;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
     public List<double[]> getPrices() {
        return prices;
    }

    public void setPrices(List<double[]> prices) {
        this.prices = prices;
    }

    @Override
    public String toString() {
        return "Country{" + "name=" + name + ", prices=" + prices + '}';
    }

}
