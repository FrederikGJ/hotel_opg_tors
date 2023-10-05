package controller;

import Model.HotelEntity;
import exception.APIExeption;
import io.javalin.http.Context;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PRIVATE) /// så der ikke oprettes duplicator
public class HotelEntityController {
    private static HotelEntityController instance;

    public static HotelEntityController getInstance() {
        if (instance == null) {
            instance = new HotelEntityController();
        }
        return instance;
    }

    Set<HotelEntity> hotels = new HashSet<>();


    public void create(Context cxt) {
        HotelEntity hotel = cxt.bodyAsClass(HotelEntity.class);
        hotels.add(hotel);
        cxt.status(201); // Created
        cxt.contentType("application/json");
        cxt.json(hotel);
    }


    public void read(Context cxt) throws APIExeption {
        int hotelId = Integer.parseInt(cxt.pathParam("id"));
        HotelEntity hotel = hotels.stream()
                .filter(h -> h.getId() == hotelId)
                .findFirst()
                .orElse(null);
        if (hotel == null) {
            throw new APIExeption(404, "HotelEntity with id: " + hotelId + "does not exist");
        } else {
            cxt.status(200);
            cxt.contentType("application/json");
            cxt.json(hotel);
        }
    }


    public void readAll(Context cxt) {
        cxt.status(200);
        cxt.contentType("application/json");
        cxt.json(hotels);
    }


    public void update(Context cxt) {
        int hotelId = Integer.parseInt(cxt.pathParam("id"));
        HotelEntity hotel = hotels.stream()
                .filter(h -> h.getId() == hotelId)
                .findFirst()
                .orElse(null);
        hotels.remove(hotel);
        HotelEntity updatedHotelEntity = cxt.bodyAsClass(HotelEntity.class);
        hotels.add(updatedHotelEntity);
        cxt.status(200);
        cxt.contentType("application/json");
        cxt.json(updatedHotelEntity);

    }


    public void delete(Context cxt) {
        int hotelId = Integer.parseInt(cxt.pathParam("id"));
        HotelEntity hotel = hotels.stream()
                .filter(h -> h.getId() == hotelId)
                .findFirst()
                .orElse(null);
        hotels.remove(hotel);
        cxt.status(204);
    }

}

