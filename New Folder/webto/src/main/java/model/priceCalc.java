package model;

import entity.PriceOBJ;
import java.util.ArrayList;

/**
 *
 * @author joaci
 */
public class priceCalc {

    public PriceOBJ getPrice(String country, String kgString) {
        Control c = new Control();

        double kg1 = Double.parseDouble(kgString.replace(",", "."));

        int kg = 1;
        for (int i = 0; i < c.weights.length; i++) {
            if (c.weights[i] == kg1) {
                kg1 = c.weights[i];
                kg = i;
            }
        }

        ArrayList<double[]> lists = c.allLists.get(country);

        double[] fedexEconomy = lists.get(0).clone();
        double[] fedexPriority = lists.get(1).clone();
        double[] upsSaver = lists.get(2).clone();
        double[] upsExpress = lists.get(3).clone();
        double[] upsStandard = lists.get(4).clone();
        double[] gls = lists.get(5).clone();

        double cheap = 99999.0;
        Double weight = 0.0;

        Double fedexE, fedexP, upsSa, upsE, upsSt, glsS = 0.0;

        weight = c.weights[kg];
        if (fedexEconomy.length > 1) {
            fedexE = fedexEconomy[kg];
        } else {
            fedexE = 0.0;
        }
        if (fedexPriority.length > 1) {
            fedexP = fedexPriority[kg];
        } else {
            fedexP = 0.0;
        }
        if (upsSaver.length > 1) {
            upsSa = upsSaver[kg];
        } else {
            upsSa = 0.0;
        }
        if (upsExpress.length > 1) {
            upsE = upsExpress[kg];
        } else {
            upsE = 0.0;
        }
        if (upsStandard.length > 1) {
            upsSt = upsStandard[kg];
        } else {
            upsSt = 0.0;
        }
        if (gls.length > 1 && kg < 100) {
            glsS = gls[kg];
        } else {
            glsS = 0.0;
        }

        int carrier = 0;
        if (kg > 99) {
            lists.remove(5);
        }

        for (int i = 0; i < lists.size(); i++) {
            if (lists.get(i).length > 1) {
                if (lists.get(i)[kg] < cheap) {
                    cheap = lists.get(i)[kg];
                    carrier = i;
                }
            }
        }      
        return new PriceOBJ(weight, fedexE, fedexP, upsSa, upsE, upsSt, glsS, c.carrierNames[carrier]);
    }
}
