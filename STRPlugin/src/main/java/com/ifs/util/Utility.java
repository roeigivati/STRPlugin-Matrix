package com.ifs.util;

import java.util.Collections;
import java.util.List;

/**
 * This class contains utility methods for String.
 *
 */
public final class Utility {

    /**
     * Holds an empty string.
     */
    private static final String EMPTY_STRING = "";

    private Utility() {

    }
    /**
     * If the string is null, return empty "" string value;
     * @param object 
     * @return String value of the object
     */
    public static String valueOf(final Object object) {
        return ((object == null) ? EMPTY_STRING : object.toString());
    }
    
    /**
     * If the object is null, then return the default value
     * @param str
     * @param defaultValue - String value
     * @return
     */
    public static String valueOf(final Object str, final String defaultValue) {
        if (str instanceof String) {
            String strObj = (String) str;
            return ((strObj == null || strObj.trim().equals("")) ? defaultValue
                : strObj);
        } else {            
            return ((str == null) ? defaultValue : str.toString());
        }
    }
    
    /**
     * Checks whether the input String is null or is empty.
     * @param inputString The input String for empty or null check.
     * @return Returns true if the input string is either null or empty.
     */
    public static Boolean isNull(final String inputString) {        
        if ((null == inputString) || (0 == inputString.trim().length())) {
            return Boolean.TRUE;
        } else {
            return Boolean.FALSE;
        }            
    } 
    
    public static List isNull( List other ) {
        return other == null ? Collections.EMPTY_LIST : other;
    }
 
}
