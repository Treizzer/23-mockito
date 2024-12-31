package com.treizer;

import java.util.List;
import java.util.Optional;

import com.treizer.persistence.entity.PlayerEntity;
import com.treizer.presentation.dto.PlayerDto;
import com.treizer.presentation.dto.PlayerInsertDto;
import com.treizer.presentation.dto.PlayerUpdateDto;

public class DataProvider {

    public static Iterable<PlayerEntity> playerEntityListMock() {
        System.out.println(" -> Obteniendo Listado Player / Mock");

        return List.of(
                new PlayerEntity(1L, "Lionel Messi", "Inter Miami", "Delantero"),
                new PlayerEntity(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
                new PlayerEntity(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
                new PlayerEntity(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
                new PlayerEntity(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
                new PlayerEntity(6L, "Virgil van Dijk", "Liverpool", "Defensa"));
    }

    public static List<PlayerDto> playerDtoListMock() {
        return List.of(
                new PlayerDto(1L, "Lionel Messi", "Inter Miami", "Delantero"),
                new PlayerDto(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
                new PlayerDto(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
                new PlayerDto(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
                new PlayerDto(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
                new PlayerDto(6L, "Virgil van Dijk", "Liverpool", "Defensa"));
    }

    public static Long sizePlayerEntityListMock() {
        return 6L;
    }

    public static Optional<PlayerEntity> optionalPlayerEntityMock() {
        return Optional.of(new PlayerEntity(1L, "Lionel Messi", "Inter Miami", "Delantero"));
    }

    public static PlayerDto playerDtoMock() {
        return new PlayerDto(1L, "Lionel Messi", "Inter Miami", "Delantero");
    }

    public static PlayerInsertDto playerInsertDtoMock() {
        return new PlayerInsertDto("Luis Díaz", "Liverpool", "Delantero");
    }

    public static PlayerEntity newPlayerEntityMock() {
        return new PlayerEntity(7L, "Luis Díaz", "Liverpool", "Delantero");
    }

    public static PlayerDto newPlayerDtoMock() {
        return new PlayerDto(7L, "Luis Díaz", "Liverpool", "Delantero");
    }

    public static PlayerUpdateDto playerUpdateDtoMock() {
        return new PlayerUpdateDto(1L, "Hugo Santana", "Barcelona", "Defensa");
    }

    public static PlayerUpdateDto playerUpdateDtoErrorMock() {
        return new PlayerUpdateDto(10L, "Hugo Santana", "Barcelona", "Defensa");
    }

    public static PlayerEntity playerEntityUpdatedMock() {
        return new PlayerEntity(1L, "Hugo Santana", "Barcelona", "Defensa");
    }

    public static PlayerDto playerDtoUpdatedMock() {
        return new PlayerDto(1L, "Hugo Santana", "Barcelona", "Defensa");
    }

}
