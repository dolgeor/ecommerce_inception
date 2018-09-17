package web.controllers;


import com.tk.model.Address;
import com.tk.model.Customer;
import com.tk.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(value = "customers")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    @GetMapping
    public String findAll(ModelMap model) {
        model.addAttribute("customers", customerService.findAll());
        return "customer/customers";
    }

    @GetMapping(value = "register")
    public String register(ModelMap model) {
        return "customer/customer-register";
    }

    @PostMapping(value = "register")
    public String registerPost(@RequestParam("name") String name,
                               @RequestParam("age") Long age) {
        Customer customer = customerService.insert(new Customer(age, name));
        return "redirect:/customers/register/" + customer.getId() + "/addresses";
    }

    @GetMapping(value = "register/{id}/addresses")
    public String registerAddresses(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("addresses", customerService.findAddressesByCustomerId(id));
        return "customer/customer-address-register";
    }

    @PostMapping(value = "/register/addresses")
    public String registerAddressesPost(@RequestParam("addr") String address,
                                        @RequestParam("customerId") Long id) {
        Customer customer = customerService.findById(id);
        customerService.insertCustomerAddress(new Address(address, customer));
        return "redirect:/customers/register/" + id + "/addresses";
    }

    @GetMapping(value = "{id}")
    public String findById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("customer", customerService.findById(id));
        model.addAttribute("adresses", customerService.findAddressesByCustomerId(id));
        return "customer/customer-info";
    }

    @PostMapping(value = "/{id}/delete")
    public String deleteById(@PathVariable("id") Long id) {
        customerService.delete(customerService.findById(id));
        return "redirect:/customers";
    }

    @PostMapping(value = "/{customerId}/addresses/{id}/delete")
    public String deleteCustomerAddressById(@PathVariable("customerId") Long customerId,
                                            @PathVariable("id") Long id) {
        customerService.deleteCustomerAddress(id);
        return "redirect:/customers/" + customerId;
    }
    @PostMapping(value = "/{customerId}/addresses/{id}/update")
    public String updateCustomerAddressById(@PathVariable("customerId") Long customerId,
                                            @PathVariable("id") Long id,
                                            @RequestParam("addressLine") String addressLine) {
        Address address = customerService.findAddressById(id);
        address.setAddressLine(addressLine);
        customerService.updateCustomerAddress(address);
        return "redirect:/customers/" + customerId;
    }
    @GetMapping(value = "/{id}/update")
    public String updateById(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("customer", customerService.findById(id));
        return "customer/customer-update";
    }

    @PostMapping(value = "/{id}/update")
    public String updateByIdPost(@PathVariable("id") Long id,
                                 @RequestParam("name") String name) {
        Customer customerToUpdate = customerService.findById(id);
        customerToUpdate.setName(name);
        customerService.update(customerToUpdate);
        return "redirect:/customers/{id}";
    }

    @GetMapping(value = "find")
    public String findByName(@RequestParam("name") String name, ModelMap model) {
        model.addAttribute("customers", customerService.findByName(name));
        return "customer/customers";
    }
}