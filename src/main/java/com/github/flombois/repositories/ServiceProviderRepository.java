package com.github.flombois.repositories;

import com.github.flombois.entities.ServiceProvider;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface ServiceProviderRepository extends CrudRepository<ServiceProvider, UUID> {

}
