package web.controllers;


import com.tk.model.Address;
import com.tk.model.Customer;
import com.tk.model.order.Item;
import com.tk.model.order.Order;
import com.tk.service.CustomerService;
import com.tk.service.ItemService;
import com.tk.service.OrderService;
import com.tk.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(value = "orders")
public class OrderController {
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerService customerService;
    @Autowired
    ProductService productService;
    @Autowired
    ItemService itemService;

    @GetMapping
    public String findAll(ModelMap model) {
        model.addAttribute("orders", orderService.findAll());
        return "order/orders";
    }

    @GetMapping(value = "register")
    public String register(@RequestParam(value = "customerId", required = false) Long customerId, ModelMap model) {
        if (customerId == null) {
            model.addAttribute("customers", customerService.findAll());
        } else {
            model.addAttribute("customer", customerService.findById(customerId));
            model.addAttribute("addresses", customerService.findAddressesByCustomerId(customerId));
        }
        return "order/order-register";
    }

    @PostMapping(value = "register")
    public String registerPost(@RequestParam("customerId") Long customerId,
                               @RequestParam("billingAddressId") Long billingAddressId,
                               @RequestParam("shippingAddressId") Long shippingAddressId) {
        Order order = orderService.findByCustomerIdAndEditableOrderAndAddresses(customerId, billingAddressId, shippingAddressId);
        if (order == null) {
            order = orderService.insert(new Order(
                    customerService.findById(customerId),
                    customerService.findAddressById(billingAddressId),
                    customerService.findAddressById(shippingAddressId)));
        }
        return "redirect:/orders/find?orderId=" + order.getOrderId();
    }

    @PostMapping(value = "items/{id}/delete")
    public String deleteById(@PathVariable("id") Long id,
                             @RequestParam("orderId") String orderId) {
        itemService.delete(itemService.findById(id));
        return "redirect:/orders/find?orderId=" + orderId;
    }

    @PostMapping(value = "items/{id}/update")
    public String updateById(@PathVariable("id") Long id,
                             @RequestParam("orderId") String orderId,
                             @RequestParam("quantity") Long quantity) {

        itemService.update(itemService.findById(id), quantity);
        return "redirect:/orders/find?orderId=" + orderId;
    }

    @PostMapping(value = "find")
    public String registerPost(@RequestParam(value = "orderId") String orderId,
                               @RequestParam(value = "product") Long productId,
                               @RequestParam(value = "quantity") Long quantity) {
        itemService.insert(new Item(
                orderService.findByOrderId(orderId),
                productService.findById(productId),
                quantity));
        return "redirect:/orders/find?orderId=" + orderId;
    }

    @PostMapping(value = "buy")
    public String registerPost(@RequestParam(value = "orderId") String orderId) {
        orderService.makeUneditable(orderService.findByOrderId(orderId));
        return "redirect:/orders/find?orderId=" + orderId;
    }


    @GetMapping(value = "find")
    public String findBy(@RequestParam(value = "orderId", required = false) String orderId,
                         @RequestParam(value = "customerId", required = false) Long customerId,
                         @RequestParam(value = "from", required = false) String from,
                         ModelMap model) {
        if (orderId != null) {

            Order order = orderService.findByOrderId(orderId);
            Long id = order.getId();
            List<Item> items = itemService.findByOrderId(id);
            BigDecimal total = new BigDecimal(
                    items
                            .stream()
                            .mapToDouble(i -> i.getPrice().doubleValue() * i.getQuantity())
                            .sum()
            );
            model.addAttribute("products", productService.findAll());
            model.addAttribute("order", order);
            model.addAttribute("items", items);
            model.addAttribute("total", total);

            return "order/order-info";
        }
        List<Order> orders = null;
        if (customerId != null)
            orders = orderService.findByCustomerId(customerId);
        if (from != null)
            orders = orderService.findByDate(Date.valueOf(from));
        model.addAttribute("orders", orders);
        return "order/orders";
    }
}
