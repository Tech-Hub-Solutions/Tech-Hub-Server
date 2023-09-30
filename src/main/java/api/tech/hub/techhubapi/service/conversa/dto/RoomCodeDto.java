package api.tech.hub.techhubapi.service.conversa.dto;

import lombok.AllArgsConstructor;

public record RoomCodeDto(
        String roomCode
) {
    public RoomCodeDto(String roomCode) {
        this.roomCode = roomCode;
    }
}
