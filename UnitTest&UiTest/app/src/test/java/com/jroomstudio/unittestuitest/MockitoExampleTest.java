package com.jroomstudio.unittestuitest;

import com.jroomstudio.unittestuitest.sample.MockitoExample;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockitoExampleTest {

    @Test
    public void test() {

        //mock 을 통해 Person 객체를 생성해줍니다.
        MockitoExample p = mock(MockitoExample.class);
        //p 객체가 존재하는지 확인합니다.
        assertTrue( p != null);

        //when 함수를 통애 p 객체의 getName, getAga 반환값을 정해줍니다.
        when(p.getName()).thenReturn("BlackJin");
        when(p.getAge()).thenReturn(27);

        //p 의 반환 값이 기대값과 같은지 확인 합니다.
        assertTrue("BlackJin".equals(p.getName()));
        assertTrue(27 == p.getAge());

    }

}
