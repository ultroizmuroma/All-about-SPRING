package ru.barinov.server.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.barinov.server.model.Customer;
import ru.barinov.server.model.DefaultResponse;
import ru.barinov.server.repository.CustomerRepository;

@Controller
public class CustomerController {
  private CustomerRepository repository;

  @Autowired
  public CustomerController(CustomerRepository repository) {
    this.repository = repository;
  }
  @GetMapping(path = "/customerlist")
  public @ResponseBody Iterable<Customer> getCustomerList() {
    return repository.findAll();
  }

  @GetMapping(path = "/customer")
  public @ResponseBody Customer getCustomer(@RequestParam Long id) {
    return repository.findOne(id);
  }

  @PostMapping(path = "/customer")
  public @ResponseBody DefaultResponse addCustomer(@RequestBody Customer requestBody) {
    Customer customer = new Customer();
    customer.setCustomerName(requestBody.getCustomerName());
    repository.save(customer);
    return new DefaultResponse("SUCCESS");
  }

  @PutMapping(path = "/customer")
  public @ResponseBody DefaultResponse updateCustomer(@RequestBody Customer requestBody) {
    Customer customer = repository.findOne(requestBody.getId());
    customer.setCustomerName(requestBody.getCustomerName());
    repository.save(customer);
    return new DefaultResponse("SUCCESS");
  }
}
