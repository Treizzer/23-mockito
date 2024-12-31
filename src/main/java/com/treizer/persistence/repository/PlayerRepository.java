package com.treizer.persistence.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.treizer.persistence.entity.PlayerEntity;

public class PlayerRepository implements ICommonRepository<PlayerEntity> {

    private List<PlayerEntity> playerDB = new ArrayList<>(List.of(
            new PlayerEntity(1L, "Lionel Messi", "Inter Miami", "Delantero"),
            new PlayerEntity(2L, "Cristiano Ronaldo", "Al Nassr", "Delantero"),
            new PlayerEntity(3L, "Neymar Jr.", "Paris Saint-Germain", "Delantero"),
            new PlayerEntity(4L, "Kylian Mbappé", "Paris Saint-Germain", "Delantero"),
            new PlayerEntity(5L, "Kevin De Bruyne", "Manchester City", "Volante"),
            new PlayerEntity(6L, "Virgil van Dijk", "Liverpool", "Defensa")));

    @Override
    public Iterable<PlayerEntity> findAll() {
        System.out.println(" -> Método findAll real!!!");
        return this.playerDB;
    }

    @Override
    public Optional<PlayerEntity> findById(Long id) {
        System.out.println(" -> Método findById real!!!");
        return this.playerDB.stream()
                .filter(player -> player.getId() == id)
                .findFirst();
    }

    @Override
    public PlayerEntity save(PlayerEntity player) {
        System.out.println(" -> Método save real!!!");
        if (this.playerDB.stream().anyMatch(p -> p.getId().equals(player.getId()))) {
            this.playerDB.set(player.getId().intValue(), player);
        } else {
            this.playerDB.add(player);
            player.setId(this.size());
        }

        return player;
    }

    @Override
    public void deleteById(Long id) {
        System.out.println(" -> Método deleteById real!!!");
        // PlayerEntity playerEntity = playerDB.get(id.intValue());

        this.playerDB = this.playerDB.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());

        // return Optional.of(playerEntity);
    }

    @Override
    public Long size() {
        return Long.valueOf(this.playerDB.size());
    }

    @Override
    public void saveVoid(PlayerEntity player) {
        System.out.println(" -> Metodo save Void real!!!");
        this.playerDB.add(player);
    }

    @Override
    public void deleteByIdVoid(Long id) {
        this.playerDB = this.playerDB.stream()
                .filter(p -> p.getId() != id)
                .collect(Collectors.toList());
    }

}
