package facades;

import entity.Country;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author joaci
 */
public class PriceGetter {

    EntityManagerFactory emf;

    public PriceGetter(String persistence) {
        this.emf = Persistence.createEntityManagerFactory(persistence);
    }

    public Country getCountry(String name) {
        EntityManager em = getEntityManager();
        return em.find(Country.class, name);
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
