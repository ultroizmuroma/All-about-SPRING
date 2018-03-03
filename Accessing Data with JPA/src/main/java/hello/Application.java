package hello;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {
  private static final Logger log = LoggerFactory.getLogger(Application.class);


  public static void main(String[] args) {
    SpringApplication.run(Application.class);
  }

//  @Bean
//  public CommandLineRunner demo(CustomerRepository repository) {
//    return (args) -> {
//      repository.save(new Customer("Алексей", "Баринов"));
//      repository.save(new Customer("Евгения", "Беззубова"));
//      log.info("findAll");
//      for (Customer customer : find(repository)) {
//        log.info("----------------------");
//        log.info(customer.toString());
//        log.info("----------------------");
//      }
//    };
//  }

//  @Bean
//  @ConfigurationProperties("app.datasource")
//  public DataSource dataSource() {
//    return DataSourceBuilder.create().build();
//  }
//
//  private List<Customer> find(CustomerRepository repository) {
//    return repository.findByLastName("Баринов");
//  }
}
