package jsonEntity;

/**
 *
 * @author joacim
 */
public class PriceOBJ {

    private Double weight;
    private Double fedexE;
    private Double fedexP;
    private Double upsSA;
    private Double upsE;
    private Double upsSt;
    private Double glsS;
    private String cheapest;

    public PriceOBJ(Double weight, Double fedexE, Double fedexP, Double upsSA, Double upsE, Double upsSt, Double glsS, String carrier) {
        this.weight = weight;
        this.fedexE = fedexE;
        this.fedexP = fedexP;
        this.upsSA = upsSA;
        this.upsE = upsE;
        this.upsSt = upsSt;
        this.glsS = glsS;
        this.cheapest = carrier;
    }
}