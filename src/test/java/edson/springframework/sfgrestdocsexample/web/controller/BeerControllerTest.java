package edson.springframework.sfgrestdocsexample.web.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import edson.springframework.sfgrestdocsexample.domain.Beer;
import edson.springframework.sfgrestdocsexample.repository.BeerRepository;
import edson.springframework.sfgrestdocsexample.web.model.BeerDto;
import edson.springframework.sfgrestdocsexample.web.model.BeerStyleNum;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

// library added to Document parameters inserted in the path for to pass the test
//library that no need parameter in the path for test valid
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

//Adding Documentation RestDoc Mock
@ExtendWith(RestDocumentationExtension.class)
@AutoConfigureRestDocs
// end doc restDoc mock
@WebMvcTest(BeerController.class)
@ComponentScan(basePackages = "edson.springframework.sfgrestdocsexample.web.mapper")
class BeerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @MockBean
    BeerRepository beerRepository;

    @Test
    void getBeerById() throws Exception {
        given(beerRepository.findById(any())).willReturn(Optional.of(Beer.builder().build()));

        mockMvc.perform(get("/api/v1/beer/{beerId}", UUID.randomUUID().toString())
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                //Adding Path Parameter doc
                    .andDo(document("v1/beer", pathParameters(
                        parameterWithName("beerId").description("UUID of desired beer to get.")
                    )));

    }

    @Test
    void saveNewBeer() throws Exception {
        BeerDto beerDto = getValidDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(post("/api/v1/beer/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(beerDtoJson))
                .andExpect(status().isCreated());

    }

    @Test

    void updateBeerById() throws Exception {
        BeerDto beerDto = getValidDto();
        String beerDtoJson = objectMapper.writeValueAsString(beerDto);

        mockMvc.perform(put("/api/v1/beer/" + UUID.randomUUID().toString())
                .contentType(MediaType.APPLICATION_JSON)
                .content(beerDtoJson))
                    .andExpect(status().isNoContent());
    }

    private BeerDto getValidDto(){
        return BeerDto.builder()
                .beerName("Nice Ale")
                .beerStyle(BeerStyleNum.ALE)
                .price(new BigDecimal("15.99"))
                .upc(123123123123L)
                .build();
    }
}