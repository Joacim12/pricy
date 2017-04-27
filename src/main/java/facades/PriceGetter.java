package facades;

import entity.Country;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author joaci
 */
public class PriceGetter {

    EntityManagerFactory emf;

    public PriceGetter(String persistence) {
        this.emf = Persistence.createEntityManagerFactory(persistence);
    }

//    public static void main(String[] args) {
//        new PriceGetter("pu").starter();
//    }
//
//    public void starter() {
//        Control c = new Control();
//        c.allLists.forEach((value, key) -> {
//            List<double[]> lists = c.allLists.get(value);     
//            createCountry(new Country(value, lists));
//        });
//    }

    public Country getCountry(String name) {
        EntityManager em = getEntityManager();
        return em.find(Country.class, name);
    }

    public void createCountry(Country country) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(country);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
