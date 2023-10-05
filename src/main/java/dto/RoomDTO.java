package dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class RoomDTO {


    Integer id;
    Integer HotelId;
    Integer number;
    Integer price;


}
