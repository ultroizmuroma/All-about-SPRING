package ru.barinov.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.barinov.server.model.Customer;
import ru.barinov.server.repository.CustomerRepository;

@Controller
public class CustomerController {
  private CustomerRepository repository;

  @Autowired
  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }
  @GetMapping(path = "/customerlist")
  public @ResponseBody
  Iterable<Customer> getCustomerList() {
    return repository.findAll();
  }

  @GetMapping(path = "/customer")
  public @ResponseBody
  Customer getCustomer(@RequestParam Long id) {
    return repository.findOne(id);
  }

  @PostMapping(path = "/customer")
  public @ResponseBody String addCustomer(@RequestParam String name) {
    Customer customer = new Customer();
    customer.setCustomerName(name);
    repository.save(customer);
    return "SUCCESS";
  }

  @PutMapping(path = "/customer")
  public @ResponseBody String updateCustomer(@RequestParam Long id,
                                             @RequestParam String name) {
    Customer customer = repository.findOne(id);
    customer.setCustomerName(name);
    repository.save(customer);
    return "SUCCESS";
  }
}
