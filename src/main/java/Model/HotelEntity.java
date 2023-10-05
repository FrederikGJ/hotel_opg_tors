package Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    String name;
    String address;

    public HotelEntity hotelDTOtoEntity(HotelEntity hotelEntity){
        return HotelEntity.builder()
                .id(hotelEntity.getId())
                .name(hotelEntity.getName())
                .address(hotelEntity.getAddress())
                .build();
    };

}
