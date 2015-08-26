package ua.epam.rd.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import ua.epam.rd.domain.Pizza;
import ua.epam.rd.domain.Users;
import ua.epam.rd.service.OrderService;
import ua.epam.rd.service.PizzaService;
import ua.epam.rd.service.UsersService;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by alex on 8/26/15.
 */
@RunWith(MockitoJUnitRunner.class)
//@MockitoJUnitRunner
public class OrderControllerMockitoTestRunner {

    @Mock
    protected OrderService orderService;

    @Mock
    protected PizzaService pizzaService;

    @Mock
    protected UsersService usersService;

    protected Model model = mock(Model.class);

    protected Map requestParam = mock(Map.class, RETURNS_DEEP_STUBS);
    private Map.Entry<String, String> requestEntry = mock(Map.Entry.class);

    @InjectMocks
    OrderController testedController = new OrderController();

    @Test
    public void numberOfCallsInternalServiceMethodsInPlaceOrderTest() {
        //given
        when(requestParam.entrySet().iterator().next()).thenReturn(requestEntry, null);
        when(requestEntry.getKey()).thenReturn("1");
        when(requestEntry.getValue()).thenReturn("10");

        //when
        testedController.placeOrder(requestParam, model);

        //then
        verify(orderService).placeOrder(anyMap(), (Users)anyObject());
        verify(usersService).getUserByLogin(anyString());
        verify(usersService).getUserBalance((Users)anyObject());
        verify(usersService).increaseCustomerBalance((Users)anyObject(), anyDouble());
    }

    @Test
    public void correctnessOfOrderPriceAccountingTest(){

        //given
        //emulate two types of pizzas in order with prices 20 and 30
        Pizza pizza = mock(Pizza.class);
        when(pizza.getPrice()).thenReturn(20.,30.);

        when(pizzaService.getPizzaById(anyLong())).thenReturn(pizza);

        //let user orders 10 pizzas with price 20 and 5 with price 5 (total ammount 350)
        when(requestParam.entrySet().iterator().next()).thenReturn(requestEntry, requestEntry, null);
        when(requestEntry.getKey()).thenReturn("1","2");
        when(requestEntry.getValue()).thenReturn("10","5");

        //it also has 100 at his discount card
        when(usersService.getUserBalance((Users)anyObject())).thenReturn(100.);

        ArgumentCaptor<Double> argument = ArgumentCaptor.forClass(Double.class);

        //when
        testedController.placeOrder(requestParam, model);

        //Then
        //verify(usersService.increaseCustomerBalance((Users) anyObject(), argument.capture()));
        verify(model).addAttribute("OrderPrice", argument.capture());

        //argument.getValue()
        assertEquals(argument.getValue().doubleValue(), 340., 0.1);
    }

}
