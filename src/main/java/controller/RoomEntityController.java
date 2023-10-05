package controller;

import Model.RoomEntity;
import exception.APIExeption;
import io.javalin.http.Context;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;


import java.util.HashSet;
import java.util.Set;


@NoArgsConstructor(access = AccessLevel.PRIVATE) /// så der ikke oprettes duplicator
public class RoomEntityController  {
    private static controller.RoomEntityController instance;

    public static controller.RoomEntityController getInstance() {
        if (instance == null) {
            instance = new controller.RoomEntityController();
        }
        return instance;
    }
    Set<RoomEntity> rooms = new HashSet<>();


    public void create(Context cxt) {
        RoomEntity room = cxt.bodyAsClass(RoomEntity.class);
        rooms.add(room);
        cxt.status(201); // Created
        cxt.contentType("application/json");
        cxt.json(room);
    }


    public void read(Context cxt) throws APIExeption {
        int roomId = Integer.parseInt(cxt.pathParam("id"));
        RoomEntity room = rooms.stream()
                .filter(h-> h.getId() == roomId)
                .findFirst()
                .orElse(null);
        if(room == null) {
            throw new APIExeption(404,"RoomEntity with id: " + roomId + "does not exist" );
        } else {
            cxt.status(200);
            cxt.contentType("application/json");
            cxt.json(room);
        }
    }


    public void readAll(Context cxt) {
        cxt.status(200);
        cxt.contentType("application/json");
        cxt.json(rooms);
    }


    public void update(Context cxt) {
        int roomId = Integer.parseInt(cxt.pathParam("id"));
        RoomEntity room = rooms.stream()
                .filter(h-> h.getId() == roomId)
                .findFirst()
                .orElse(null);
        rooms.remove(room);
        RoomEntity updatedRoomEntity = cxt.bodyAsClass(RoomEntity.class);
        rooms.add(updatedRoomEntity);
        cxt.status(200);
        cxt.contentType("application/json");
        cxt.json(updatedRoomEntity);
    }


    public void delete(Context cxt) {
        int roomId = Integer.parseInt(cxt.pathParam("id"));
        RoomEntity room = rooms.stream()
                .filter(h-> h.getId() == roomId)
                .findFirst()
                .orElse(null);
        rooms.remove(room);
        cxt.status(204);
    }
}