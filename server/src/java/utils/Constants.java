/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Vladimir Aca
 */
public class Constants {
    public static final Map<Integer, String> TYPE_ROUTE;
    static {
        Map<Integer, String> type = new HashMap<>();
        type.put(1, "ON_DEMAND");
        type.put(2, "AUTOMATIC");
        TYPE_ROUTE = Collections.unmodifiableMap(type);
    }
}
