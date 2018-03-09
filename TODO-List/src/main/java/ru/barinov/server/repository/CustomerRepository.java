package ru.barinov.server.repository;

import org.springframework.data.repository.CrudRepository;
import ru.barinov.server.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {}
