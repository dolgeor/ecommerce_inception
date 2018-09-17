package web.controllers;

import com.tk.model.Customer;
import com.tk.service.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setup() {

        // Process mock annotations
        MockitoAnnotations.initMocks(this);

        // Setup Spring test in standalone mode
        this.mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();

    }

    @Test
    public void shouldFindAllCustomers() throws Exception {
        List<Customer> customers = Arrays.asList(
                new Customer(22L, "Daenerys Targaryen"),
                new Customer(21L, "John Snow"));
        when(customerService.findAll()).thenReturn(customers);
        this.mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(forwardedUrl("customer/customers"))
                .andExpect(model().attribute("customers", customers));

        verify(customerService, times(1)).findAll();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void shouldFindCustomerById() throws Exception {
        Customer customer = new Customer(22L, "Daenerys Targaryen");
        when(customerService.findById(1L)).thenReturn(customer);
        when(customerService.findAddressesByCustomerId(1L)).thenReturn(new ArrayList<>());

        this.mockMvc.perform(get("/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-info"))
                .andExpect(forwardedUrl("customer/customer-info"))
                .andExpect(model().attribute("customer", customer));

        verify(customerService, times(1)).findById(1L);
        verify(customerService, times(1)).findAddressesByCustomerId(1L);
        verifyNoMoreInteractions(customerService);
    }

    @Test(expected = NestedServletException.class)
    public void shouldThrowNoSuchElementExceptionWhenFindCustomerByIllegalId() throws Exception {
        when(customerService.findById(2L)).thenThrow(NestedServletException.class);
        this.mockMvc.perform(get("/customers/{id}", 2L));
    }

    @Test
    public void shouldFindCustomersByName() throws Exception {
        String name = "John";
        List<Customer> customers = Arrays.asList(
                new Customer(22L, name),
                new Customer(21L, name));
        when(customerService.findByName(name)).thenReturn(customers);
        this.mockMvc.perform(get("/customers/find")
                .param("name", name))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(forwardedUrl("customer/customers"))
                .andExpect(model().attribute("customers", customers));

        verify(customerService, times(1)).findByName(name);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void shouldGoToRegistrationPage() throws Exception {
        this.mockMvc.perform(get("/customers/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-register"))
                .andExpect(forwardedUrl("customer/customer-register"));
    }

    @Test
    public void shouldGoToUpdatePage() throws Exception {
        Long id = 1L;
        Customer customer = new Customer(22L, "Daenerys Targaryen");
        when(customerService.findById(1L)).thenReturn(customer);

        this.mockMvc.perform(get("/customers/{id}/update",1))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customer-update"))
                .andExpect(forwardedUrl("customer/customer-update"))
                .andExpect(model().attribute("customer", customer));

        verify(customerService, times(1)).findById(1L);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void shouldDeleteCustomer() throws Exception {
        Long id = 1L;
        Customer customer = new Customer(22L, "Daenerys Targaryen");

        when(customerService.findById(id)).thenReturn(customer);

        this.mockMvc.perform(post("/customers/{id}/delete", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers"))
                .andExpect(redirectedUrl("/customers"));

        verify(customerService, times(1)).findById(id);
        verify(customerService, times(1)).delete(customer);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void shouldCreateCustomer() throws Exception {
        Customer customer = new Customer(22L, "Daenerys Targaryen");
        when(customerService.insert(customer)).thenReturn(customer);

        this.mockMvc.perform(post("/customers/register")
                .param("name", customer.getName())
                .param("age", customer.getAge().toString()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/register/null/addresses"))
                .andExpect(redirectedUrl("/customers/register/null/addresses"));
        verify(customerService, times(1)).insert(customer);
        verifyNoMoreInteractions(customerService);
    }

    @Test
    public void shouldUpdateCustomer() throws Exception {
        Customer customer = new Customer(22L, "Daenerys Targaryen");
        when(customerService.findById(1L)).thenReturn(customer);
        when(customerService.update(customer)).thenReturn(customer);

        this.mockMvc.perform(post("/customers/{id}/update",1L)
                .param("name", customer.getName()))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/{id}"))
                .andExpect(redirectedUrl("/customers/1"));
        verify(customerService,times(1)).findById(1l);
        verify(customerService, times(1)).update(customer);
        verifyNoMoreInteractions(customerService);
    }

}
