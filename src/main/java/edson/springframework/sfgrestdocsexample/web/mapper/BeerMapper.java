package edson.springframework.sfgrestdocsexample.web.mapper;

import edson.springframework.sfgrestdocsexample.domain.Beer;
import edson.springframework.sfgrestdocsexample.web.model.BeerDto;
import org.mapstruct.Mapper;

@Mapper(uses = {DateMapper.class})
public interface BeerMapper {
    BeerDto BeerToBeerDto(Beer beer);
    Beer BeerDtoToBeer(BeerDto beerDto);
}
