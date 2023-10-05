package dto;


import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class HotelDTO {
    Integer id;
    String name;
    String address;
    Integer rooms; // this is the number of rooms in the hotel
}
