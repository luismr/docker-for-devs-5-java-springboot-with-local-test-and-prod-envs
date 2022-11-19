package org.example.api.repository;

import org.example.api.data.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface CountryCrudRepository extends CrudRepository<Country, Long> {

    Country findByName(@Param("name") String name);

}
