package edson.springframework.sfgrestdocsexample.web.controller;

import edson.springframework.sfgrestdocsexample.repository.BeerRepository;
import edson.springframework.sfgrestdocsexample.web.mapper.BeerMapper;
import edson.springframework.sfgrestdocsexample.web.model.BeerDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequiredArgsConstructor
@RequestMapping("/api/v1/beer")
@RestController
public class BeerController {

    private final BeerMapper beerMapper;
    private final BeerRepository beerRepository;

    @GetMapping("/{beerId}")
    public ResponseEntity<?> getBeerById(@PathVariable("beerId") UUID beerId){
        return new ResponseEntity<>(beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get()), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> saveNewBeer(@RequestBody @Validated BeerDto beerDto){
        beerRepository.save(beerMapper.BeerDtoToBeer(beerDto));
        return new ResponseEntity<>("Created", HttpStatus.CREATED);
    }

    @PutMapping("/{beerId}")
    public ResponseEntity<?> updateBeerById(@PathVariable("beerId") UUID beerId, @RequestBody @Validated BeerDto beerDto){
        //Beer oldBeer = beerRepository.findById(beerId).get();

        beerRepository.findById(beerId).ifPresent(beer -> {
            beer.setBeerName(beerDto.getBeerName());
            beer.setBeerStyle(beerDto.getBeerStyle().name());
            beer.setPrice(beerDto.getPrice());
            beer.setUpc(beerDto.getUpc());
            beerRepository.save(beer);
        });


        /*BeerDto oldBeer = beerMapper.BeerToBeerDto(beerRepository.findById(beerId).get());
        oldBeer.setBeerName(beerDto.getBeerName());
        oldBeer.setBeerStyle(beerDto.getBeerStyle());
        oldBeer.setPrice(beerDto.getPrice());
        oldBeer.setUpc(beerDto.getUpc());

        beerRepository.save(beerMapper.BeerDtoToBeer(oldBeer));*/
        return new ResponseEntity<>("Updated", HttpStatus.NO_CONTENT);
    }
}
