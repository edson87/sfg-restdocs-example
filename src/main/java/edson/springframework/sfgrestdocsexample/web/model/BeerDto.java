package edson.springframework.sfgrestdocsexample.web.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDto {
    @Null
    private UUID id;
    @Null
    private Long version;
    @Null
    private OffsetDateTime createdDate;
    @Null
    private OffsetDateTime lastUpdatedDate;
    @NotBlank
    private String beerName;
    @NotBlank
    private String beerStyle;
    @Positive
    @NotNull
    private Long upc;
    @Positive
    @NotNull
    private BigDecimal price;
    @Positive
    @NotNull
    private  Integer minOnHand;
    @Null
    private Integer quantityToBrew;
}
