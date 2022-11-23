package org.example.api.tests;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.api.CrudApplication;
import org.example.api.data.Country;
import org.example.api.repository.CountryCrudRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.hasToString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.MOCK,
        classes = CrudApplication.class
)
@AutoConfigureMockMvc
public class CountryRestRepositoryTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private CountryCrudRepository repository;

    @Test
    public void givenListOfCountriesThenReturnsJsonArray() throws Exception {
        mvc.perform(get("/countries").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.countries", hasSize(3)));
    }

    @Test
    public void givenCountryById() throws Exception {
        mvc.perform(get("/countries/{id}", 2).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", hasToString("Argentina")));
    }

    @Test
    public void createParaguay() throws Exception {
        mvc.perform(put("/countries/{id}", 4)
                        .content(
                                asJsonString(
                                        new Country(4L, "Paraguay")
                                )
                        )
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful());

        mvc.perform(get("/countries/{id}", 4).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", hasToString("Paraguay")));
    }

    private String asJsonString(Country country) {
        try {
            return new ObjectMapper().writeValueAsString(country);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
