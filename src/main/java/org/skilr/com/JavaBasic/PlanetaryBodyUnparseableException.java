package org.skilr.com.JavaBasic;

import java.text.ParseException;

public class PlanetaryBodyUnparseableException extends ParseException {
    public PlanetaryBodyUnparseableException(String message) {
        super(message, -1);
    }
}
