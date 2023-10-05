package Model;

import dto.RoomDTO;
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
public class RoomEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer id;
    Integer HotelId;
    Integer number;
    Integer price;

    public RoomEntity roomDTOtoEntity(RoomDTO roomDTO){
        return RoomEntity.builder()
                .id(roomDTO.getId())
                .HotelId(roomDTO.getHotelId())
                .number(roomDTO.getNumber())
                .price(roomDTO.getPrice())
                .build();
    };

}
