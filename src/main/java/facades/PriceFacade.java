package facades;

import entity.Country;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;

/**
 *
 * @author joaci
 */
public class PriceFacade {

    EntityManagerFactory emf;

    public PriceFacade(String persistence) {
        this.emf = Persistence.createEntityManagerFactory(persistence);
    }
    
    public static void main(String[] args) {
        new PriceFacade("pu").starter();
    }
    
    public void starter(){
//        Country c = getCountry("Denmark");
//        double[] test = {109.89,112.86,113.67,115.56,117.45,118.53,120.96,122.31,123.12,124.74,126.09,127.44,129.06,130.41,132.84,133.65,135.27,136.08,137.97,140.13,142.83,142.83,147.15,147.15,150.12,150.12,154.17,154.17,157.68,157.68,162.0,162.0,164.97,164.97,169.02,169.02,172.26,172.26,176.58,176.58,183.06,183.06,183.06,183.06,189.81,189.81,189.81,189.81,197.37,197.37,197.37,197.37,204.39,204.39,204.39,204.39,211.14,211.14,211.14,211.14,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71};
//        double[] test = {102,112.86,113.67,115.56,117.45,118.53,120.96,122.31,123.12,124.74,126.09,127.44,129.06,130.41,132.84,133.65,135.27,136.08,137.97,140.13,142.83,142.83,147.15,147.15,150.12,150.12,154.17,154.17,157.68,157.68,162.0,162.0,164.97,164.97,169.02,169.02,172.26,172.26,176.58,176.58,183.06,183.06,183.06,183.06,189.81,189.81,189.81,189.81,197.37,197.37,197.37,197.37,204.39,204.39,204.39,204.39,211.14,211.14,211.14,211.14,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,227.61,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,244.35,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,260.28,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,277.02,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,294.03,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,309.96,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,326.43,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71,343.71};
//        List<double[]> updatedList = c.getPrices();
//        updatedList.set(2, test);
//        c.setPrices(updatedList);
//        updateCountry(c);
        System.out.println(getCountry("Denmark").getPrices().get(2)[0]);
    }

    public Country getCountry(String name) {
        EntityManager em = getEntityManager();
        return em.find(Country.class, name);
    }
    
    public Country createCountry(Country country) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(country);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            r.printStackTrace();
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return getCountry(country.getName());
    }
    
    public Country updateCountry(Country country) {
        EntityManager em = getEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(country);
            em.getTransaction().commit();
        } catch (RollbackException r) {
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
        return country;
    }
    
    private EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public List<Country> getAllCountries() {
        EntityManager em = getEntityManager();
        return em.createQuery("SELECT c FROM Country c", Country.class).getResultList();
    }
}
