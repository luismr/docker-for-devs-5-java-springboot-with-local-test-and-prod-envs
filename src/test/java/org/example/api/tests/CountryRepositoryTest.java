package org.example.api.tests;

import org.example.api.data.Country;
import org.example.api.repository.CountryCrudRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class CountryRepositoryTest {

    @Autowired
    private CountryCrudRepository repository;

    private Iterable<Country> countries;

    @BeforeEach
    private void setupEach() {
        this.countries = repository.findAll();
    }

    @Test
    public void injectedComponentsAreNotNull() {
        assertThat(repository).isNotNull();
    }

    @Test
    public void checkIfThereIsThreeCountries() {
        assertThat(countries).size().isEqualTo(3);
    }

    @Test
    public void checkIfThereIsBrazilInCountries() {
        List<Country> countryList = new ArrayList<>();
        countries.forEach(countryList::add);
        Optional<Country> found = countryList.stream().filter(c -> "Brazil".equalsIgnoreCase(c.getName())).findFirst();
        assertThat(found.isPresent()).isTrue();
    }

}
