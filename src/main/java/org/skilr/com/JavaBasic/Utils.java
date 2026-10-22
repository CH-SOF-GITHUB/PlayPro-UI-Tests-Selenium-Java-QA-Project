package org.skilr.com.JavaBasic;

public class Utils {
    public static PlanetaryBody parsePlanetaryBody(String body) throws PlanetaryBodyUnparseableException {

        //
        if("sun".equalsIgnoreCase(body)) {
            return PlanetaryBody.SUN;
        } else if("moon".equalsIgnoreCase(body)) {
            return PlanetaryBody.MOON;
        } else {
            throw new PlanetaryBodyUnparseableException("We don't know what " + body + "is!");
        }
    }
}
