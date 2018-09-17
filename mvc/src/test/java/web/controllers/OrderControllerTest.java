package web.controllers;

import com.tk.model.Address;
import com.tk.model.Customer;
import com.tk.model.order.Order;
import com.tk.model.Product;
import com.tk.service.CustomerService;
import com.tk.service.OrderService;
import com.tk.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class OrderControllerTest {

    @Mock
    OrderService orderService;

    @Mock
    CustomerService customerService;

    @Mock
    ProductService productService;

    @InjectMocks
    OrderController orderController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
    }

    @Test
    public void shouldSuccessfullyFindAllOrders() throws Exception {
        List<Order> expected = new ArrayList<>();

        doReturn(expected).when(orderService).findAll();

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(view().name("order/orders"))
                .andExpect(forwardedUrl("order/orders"))
                .andExpect(model().attribute("orders", expected));

        verify(orderService, times(1)).findAll();
        verifyNoMoreInteractions(orderService);

    }



    @Test
    public void shouldNotInsertOrderOnExistingOne() throws Exception {

        Customer customer = new Customer( 18L, "arst");
        customer.setId(1L);
        Address address = new Address("addr", customer);
        address.setId(1l);
        Order order = new Order(customer, address, address);
        doReturn(order).when(orderService).findByCustomerIdAndEditableOrderAndAddresses(customer.getId(), address.getId(), address.getId());
        mockMvc.perform(post("/orders/register")
                .param("customerId", String.valueOf(1L))
                .param("billingAddressId", String.valueOf(1L))
                .param("shippingAddressId", String.valueOf(1L)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/find?orderId=" + order.getOrderId()))
                .andExpect(redirectedUrl("/orders/find?orderId=" + order.getOrderId()));
        verify(orderService, times(1)).findByCustomerIdAndEditableOrderAndAddresses(customer.getId(), address.getId(), address.getId());
        verifyNoMoreInteractions(orderService);
    }

    @Test
    public void shouldInsertOrder() throws Exception {

        Customer customer = new Customer( 18L, "arst");
        customer.setId(1L);
        Address address = new Address("addr", customer);
        address.setId(1l);
        Order order = new Order(customer, address, address);

        doReturn(null).when(orderService).findByCustomerIdAndEditableOrderAndAddresses(customer.getId(), address.getId(), address.getId());
        doReturn(customer).when(customerService).findById(customer.getId());
        doReturn(address).when(customerService).findAddressById(address.getId());
        doReturn(order).when(orderService).insert(order);

        mockMvc.perform(post("/orders/register")
                .param("customerId", String.valueOf(1L))
                .param("billingAddressId", String.valueOf(1L))
                .param("shippingAddressId", String.valueOf(1L)))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/orders/find?orderId=null"))
                .andExpect(redirectedUrl("/orders/find?orderId=null"));

        verify(orderService, times(1)).findByCustomerIdAndEditableOrderAndAddresses(customer.getId(), address.getId(), address.getId());
        verify(orderService, times(1)).insert(order);
        verify(customerService, times(1)).findById(customer.getId());
        verify(customerService, times(2)).findAddressById(address.getId());

        verifyNoMoreInteractions(customerService);
        verifyNoMoreInteractions(orderService);
    }
}
