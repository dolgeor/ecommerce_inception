package web.controllers;

import com.tk.model.Product;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
@RequestMapping(value = "products")
public class ProductController {
    @Autowired
    ProductService productService;


    @GetMapping
    public String findAll(ModelMap model) {
        model.addAttribute("products", productService.findAll());
        return "product/products";
    }

    @GetMapping(value = "register")
    public String register() {
        return "product/product-register";
    }

    @PostMapping(value = "register")
    public String registerPost(@RequestParam("name") String name,
                               @RequestParam("sku") String sku,
                               @RequestParam("price") String price) {
        productService.insert(new Product(name, sku, new BigDecimal(price)));
        return "redirect:/products";
    }

    @GetMapping(value = "{id}")
    public String findById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("product", productService.findById(id));
        return "product/product-info";
    }

    @PostMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        productService.delete(productService.findById(id));
        return "redirect:/products";
    }

    @GetMapping(value = "/{id}/update")
    public String updateById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("product", productService.findById(id));
        return "product/product-update";
    }

    @PostMapping(value = "/{id}/update")
    public String updateByIdPost(@PathVariable("id") Long id,
                                 @RequestParam("name") String name,
                                 @RequestParam("sku") String sku,
                                 @RequestParam("price") String price) {
        Product productToUpdate = productService.findById(id);
        productToUpdate.setName(name);
        productToUpdate.setSku(sku);
        productToUpdate.setPrice(new BigDecimal(price));
        productService.update(productToUpdate);
        return "redirect:/products/{id}";
    }

    @GetMapping(value = "find")
    public String findByName(@RequestParam(value = "name", required = false) String name,
                             @RequestParam(value = "sku", required = false) String sku,
                             ModelMap model) {
        if (name != null)
            model.addAttribute("products", productService.findByName(name));
        if (sku != null)
            model.addAttribute("products", productService.findBySku(sku));
        return "product/products";
    }

}