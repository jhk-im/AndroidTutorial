package com.jroomstudio.unittestuitest;

import com.jroomstudio.unittestuitest.sample.JUnitExample;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JUnitExampleTest {

    private JUnitExample jUnitExample;

    /**
     * Before -> 여러 테스트를 실행하기 전에 비슷한 개체를 만든다.
     **/
    @Before
    public void setUp() {
        jUnitExample = new JUnitExample();
    }

    /**
     * Test -> 테스트 케이스로 실행 될 수 있음을 JUnit 에 알린다.
     **/
    @Test
    public void plusOperationTest(){
        assertEquals(4, 2 + 2);
    }

    @Test
    public void plusTest() {
        int result = jUnitExample.plus(15,10);
        assertEquals(25, result);
    }
}
