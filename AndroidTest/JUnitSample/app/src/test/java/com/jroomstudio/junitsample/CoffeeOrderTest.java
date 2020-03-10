package com.jroomstudio.junitsample;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;

public class CoffeeOrderTest {
    private final static float PRICE_TEST = 5.0f;
    private CoffeeOrder mOrder;
    // Test 할 객체 셋팅
    @Before
    public void setUp() {
        mOrder = new CoffeeOrder(PRICE_TEST);
    }
    // 객체가 null 인지 확인
    @Test
    public void orderIsNotNull() {
        assertNotNull(mOrder);
    }
    //감소
    @Test
    public void orderDecrement() {
        mOrder.decrementCoffeeCount();
        assertEquals(0, mOrder.getCoffeeCount());
        mOrder.setCoffeeCount(25);
        mOrder.decrementCoffeeCount();
        assertEquals(24, mOrder.getCoffeeCount());
    }
    //증가
    @Test
    public void orderIncrement() {
        mOrder.incrementCoffeeCount();
        assertEquals(1, mOrder.getCoffeeCount());
        mOrder.setCoffeeCount(25);
        mOrder.incrementCoffeeCount();
        assertEquals(26, mOrder.getCoffeeCount());
    }
    //총액
    @Test
    public void orderTotalPrice() {
        assertEquals(0.0f, mOrder.getTotalPrice());
        mOrder.setCoffeeCount(25);
        assertEquals(PRICE_TEST * 25, mOrder.getTotalPrice());
    }
    //커피 수량
    @Test
    public void orderSetCoffeeCount() {
        mOrder.setCoffeeCount(-1);
        assertEquals(0, mOrder.getCoffeeCount());
        mOrder.setCoffeeCount(25);
        assertEquals(25, mOrder.getCoffeeCount());
    }
}