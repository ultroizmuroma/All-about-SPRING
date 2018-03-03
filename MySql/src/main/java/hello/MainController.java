package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

@Controller
@RequestMapping(path="/demo")
public class MainController {
  @Autowired
  private CustomerRepository customerRepository;

  @GetMapping(path = "/add")
  public @ResponseBody String addNewCustomer(@RequestParam String firstName, @RequestParam String lastName) {
    Customer customer = new Customer();
    customer.setFirstName(firstName);
    customer.setLastName(lastName);
    customer.setBirthDate(new Date());
    customerRepository.save(customer);
    return "Saved";
  }

  @GetMapping("/all")
  public @ResponseBody Iterable<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }
}
