package web.controllers;

import com.tk.model.Product;
import com.tk.service.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


public class ProductControllerTest {

    @Mock
    ProductService productService;

    @InjectMocks
    ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    public void shouldFindAllProducts() throws Exception {
        List<Product> products = new ArrayList<>();
        doReturn(products).when(productService).findAll();
        this.mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(forwardedUrl("product/products"))
                .andExpect(model().attribute("products",products));

        verify(productService, times(1)).findAll();
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldSuccesfullyRegisterProduct() throws Exception {
        this.mockMvc.perform(get("/products/register"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/product-register"));
    }

    @Test
    public void  shouldSuccesfullyInsertProduct() throws Exception {
        Product product = new Product("asdf", "adf", new BigDecimal(100));
        doReturn(product).when(productService).insert(product);

        mockMvc.perform(post("/products/register")
        .param("name", product.getName())
        .param("sku", product.getSku())
        .param("price", String.valueOf(product.getPrice())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"))
                .andDo(print());

        verify(productService, Mockito.times(1)).insert(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldSuccessfullyFindProductById() throws Exception {
        Long id = 1L;
        Product expected = new Product();
        doReturn(expected).when(productService).findById(id);

        mockMvc.perform(get("/products/{id}", id))
                .andExpect(status().isOk())
                .andExpect(view().name("product/product-info"))
                .andExpect(forwardedUrl("product/product-info"))
                .andExpect(model().attribute("product", expected));

        verify(productService, Mockito.times(1)).findById(id);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldSuccessfullyFindProductByName() throws Exception {
        String name = "name";
        List<Product> expected = new ArrayList<>();

        doReturn(expected).when(productService).findByName(name);

        when(productService.findByName(name)).thenReturn(expected);
        this.mockMvc.perform(get("/products/find")
                .param("name", name))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(forwardedUrl("product/products"))
                .andExpect(model().attribute("products", expected));

        verify(productService, Mockito.times(1)).findByName(name);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldSuccessfullyFindProductBySku() throws Exception {
        String sku = "sku";
        List<Product> expected = new ArrayList<>();

        doReturn(expected).when(productService).findBySku(sku);

        when(productService.findBySku(sku)).thenReturn(expected);
        this.mockMvc.perform(get("/products/find")
                .param("sku", sku))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(forwardedUrl("product/products"))
                .andExpect(model().attribute("products", expected));

        verify(productService, Mockito.times(1)).findBySku(sku);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldDeleteById() throws Exception {
        Long id = 1L;
        Product product = new Product();
        doReturn(product).when(productService).findById(id);

        this.mockMvc.perform(post("/products/{id}/delete", id))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products"))
                .andExpect(redirectedUrl("/products"));


        verify(productService, times(1)).findById(id);
        verify(productService, times(1)).delete(product);
        verifyNoMoreInteractions(productService);
    }

    @Test
    public void shouldSuccessfullyUpdateProductById() throws Exception {
        Product product = new Product("name", "sku", new BigDecimal(120));
        when(productService.findById(1L)).thenReturn(product);
        when(productService.update(product)).thenReturn(product);

        this.mockMvc.perform(post("/products/{id}/update",1L)
                .param("name", product.getName())
                .param("sku", product.getSku())
                .param("price", String.valueOf(product.getPrice())))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products/{id}"))
                .andExpect(redirectedUrl("/products/1"));
        verify(productService, Mockito.times(1)).findById(1l);
        verify(productService, Mockito.times(1)).update(product);
        verifyNoMoreInteractions(productService);
    }
}
