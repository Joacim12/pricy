/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

/**
 *
 * @author joaci
 */
public class PriceOBJ {

    private Double weight;
    private Double fedexE;
    private Double fedexP;
    private Double upsSA;
    private Double upsE;
    private Double upsSt;
    private Double glsS;
    private String carrier;

    public PriceOBJ(Double weight, Double fedexE, Double fedexP, Double upsSA, Double upsE, Double upsSt, Double glsS, String carrier) {
        this.weight = weight;
        this.fedexE = fedexE;
        this.fedexP = fedexP;
        this.upsSA = upsSA;
        this.upsE = upsE;
        this.upsSt = upsSt;
        this.glsS = glsS;
        this.carrier = carrier;
    }

}
