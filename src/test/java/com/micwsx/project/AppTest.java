package com.micwsx.project;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import java.util.HashMap;

/**
 * Unit test for simple App.
 */
public class AppTest {
    /**
     * Rigorous Test :-)
     */
    @Test
    public void shouldAnswerWithTrue() {
        HashMap<String,String> hashMap=new HashMap<>();
        hashMap.put("t", "e");
        System.out.println(hashMap);

        String[] array = {};
        System.out.println(String.join(",", array));
    }
}
