package edson.springframework.sfgrestdocsexample.bootstrap;

import edson.springframework.sfgrestdocsexample.domain.Beer;
import edson.springframework.sfgrestdocsexample.repository.BeerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class BeerLoader implements CommandLineRunner {

    private final BeerRepository beerRepository;

    public BeerLoader(BeerRepository beerRepository) {
        this.beerRepository = beerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loaderBeerObject();
    }

    private void loaderBeerObject() {
        if (beerRepository.count() == 0){
            beerRepository.save(Beer.builder()
                    .beerName("Mango Bobs")
                    .beerStyle("IPA")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(123456789L)
                    .price(new BigDecimal("12.3"))
                    .build());

            beerRepository.save(Beer.builder()
                    .beerName("Galaxy cat")
                    .beerStyle("PALE_ALE")
                    .quantityToBrew(200)
                    .minOnHand(12)
                    .upc(12345678910L)
                    .price(new BigDecimal("11.95"))
                    .build());

            System.out.println("Amount of beers: " + beerRepository.count());
        }
    }
}
