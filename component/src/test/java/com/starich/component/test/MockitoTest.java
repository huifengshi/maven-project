package com.starich.component.test;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Jason on 2017/3/12.
 */
public class MockitoTest {

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test(){
        List mockedList = mock(List.class);

        mockedList.add("one");
        mockedList.clear();

        verify(mockedList).add("one");
        verify(mockedList).clear();

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(1)).thenThrow(new RuntimeException());

        System.out.println(mockedList.get(0));
        try {
            System.out.println(mockedList.get(1));
        }catch (RuntimeException re){

        }
        System.out.println(mockedList.get(999));

        verify(mockedList).get(0);


        when(mockedList.get(anyInt())).thenReturn("element");

        System.out.println(mockedList.get(999));
    }

    @Test
    public void test2(){
        List mockedList = mock(List.class);
        mockedList.add("once");

        mockedList.add("twice");
        mockedList.add("twice");

        mockedList.add("three times");
        mockedList.add("three times");
        mockedList.add("three times");

        verify(mockedList).add("once");
        verify(mockedList, times(1)).add("once");

        verify(mockedList, times(2)).add("twice");
        verify(mockedList, times(3)).add("three times");

        verify(mockedList, never()).add("never happened");

        verify(mockedList, atLeastOnce()).add("three times");
        //verify(mockedList, atLeast(2)).add("five times");
        verify(mockedList, atMost(5)).add("three times");

        doThrow(new RuntimeException()).when(mockedList).clear();
        mockedList.clear();
    }

    @Test
    public void test6(){
        List singleMock = mock(List.class);

        singleMock.add("was added first");
        singleMock.add("was added second");

        InOrder inOrder = inOrder(singleMock);

        inOrder.verify(singleMock).add("was added first");
        inOrder.verify(singleMock).add("was added second");

        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("was called first");
        secondMock.add("was called second");

        InOrder inOrder1 = inOrder(firstMock, secondMock);
        inOrder1.verify(firstMock).add("was called first");
        inOrder1.verify(secondMock).add("was called second");
    }

    @Test
    public void test7(){
        List mockOne = mock(List.class);
        List mockTwo = mock(List.class);
        List mockThree = mock(List.class);

        mockOne.add("one");

        verify(mockOne).add("one");

        verify(mockOne, never()).add("two");

        verifyZeroInteractions(mockTwo, mockThree);
    }

    @Test
    public void test8(){
        List mockedList = mock(List.class);

        mockedList.add("two");
        mockedList.add("one");

        verify(mockedList).add("one");
        verify(mockedList).add("two");
        verifyNoMoreInteractions(mockedList);
    }

    @Test
    public void test10(){
        List mockedList = mock(List.class);
        List mockedList2 = mock(List.class);
        when(mockedList.get(anyInt())).thenReturn("1").thenReturn("2");
        when(mockedList2.get(anyInt())).thenReturn("one", "two", "three");
        System.out.println(mockedList.get(999));
        System.out.println(mockedList.get(1));
        System.out.println(mockedList.get(2));

        System.out.println(mockedList2.get(999));
        System.out.println(mockedList2.get(1));
        System.out.println(mockedList2.get(2));
    }

    @Test
    public void test13(){
        List list = new LinkedList();
        List listSpy = spy(list);
        when(listSpy.size()).thenReturn(100);

        listSpy.add("one");
        listSpy.add("two");

        System.out.println(listSpy.get(0));

        System.out.println(listSpy.size());

        verify(listSpy).add("one");
        verify(listSpy).add("two");

    }
}
