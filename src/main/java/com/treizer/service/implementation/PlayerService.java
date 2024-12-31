package com.treizer.service.implementation;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import com.treizer.persistence.entity.PlayerEntity;
import com.treizer.persistence.repository.ICommonRepository;
import com.treizer.presentation.dto.PlayerDto;
import com.treizer.presentation.dto.PlayerInsertDto;
import com.treizer.presentation.dto.PlayerUpdateDto;
import com.treizer.service.interfaces.ICommonService;

// It's only test with JUnit
public class PlayerService implements ICommonService<PlayerDto, PlayerInsertDto, PlayerUpdateDto> {

    // Dependency only works with Mockito
    private final ICommonRepository<PlayerEntity> playerRepository;

    public PlayerService(ICommonRepository<PlayerEntity> playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public List<PlayerDto> findAll() {
        var players = this.playerRepository.findAll();

        return StreamSupport
                .stream(players.spliterator(), false)
                .map(p -> new PlayerDto(p.getId(), p.getName(), p.getTeam(), p.getPosition()))
                .collect(Collectors.toList());
    }

    @Override
    public PlayerDto findById(Long id) {
        PlayerEntity player = this.playerRepository.findById(id)
                .orElseThrow(() -> new UnsupportedOperationException("No se encontró al jugador con ID: " + id));

        return new PlayerDto(player.getId(), player.getName(), player.getTeam(), player.getPosition());
    }

    @Override
    public PlayerDto save(PlayerInsertDto insertDto) {
        try {
            Long id = this.playerRepository.size() + 1;
            PlayerEntity playerEntity = new PlayerEntity(id, insertDto.getName(), insertDto.getTeam(),
                    insertDto.getPosition());
            playerEntity = this.playerRepository.save(playerEntity);

            return new PlayerDto(playerEntity.getId(), playerEntity.getName(), playerEntity.getTeam(),
                    playerEntity.getPosition());

        } catch (Exception e) {
            throw new UnsupportedOperationException(
                    "No fue posible guardar el jugador: " + insertDto + " -> e: " + e.toString());
        }
    }

    @Override
    public PlayerDto update(PlayerUpdateDto updateDto, Long id) {
        PlayerEntity playerEntity = id != null
                ? this.playerRepository.findById(id)
                        .orElseThrow(() -> new UnsupportedOperationException(
                                "1. No se encontró al jugador con ID: " + id))
                : this.playerRepository.findById(updateDto.getId())
                        .orElseThrow(() -> new UnsupportedOperationException(
                                "2. No se encontró al jugador con ID: " + updateDto.getId()));

        Optional.ofNullable(updateDto.getName()).ifPresent(playerEntity::setName);
        Optional.ofNullable(updateDto.getTeam()).ifPresent(playerEntity::setTeam);
        Optional.ofNullable(updateDto.getPosition()).ifPresent(playerEntity::setPosition);

        this.playerRepository.save(playerEntity);

        return new PlayerDto(playerEntity.getId(), playerEntity.getName(), playerEntity.getTeam(),
                playerEntity.getPosition());
    }

    @Override
    public PlayerDto deleteById(Long id) {
        PlayerEntity playerEntity = this.playerRepository.findById(id)
                .orElseThrow(() -> new UnsupportedOperationException(
                        "No se encontró al jugador con ID: " + id));

        this.playerRepository.deleteById(id);

        return new PlayerDto(playerEntity.getId(), playerEntity.getName(), playerEntity.getTeam(),
                playerEntity.getPosition());
    }

    @Override
    public void saveVoid(PlayerInsertDto insertDto) {
        // Long id = this.playerRepository.size() + 1;
        Long id = 10L;
        PlayerEntity playerEntity = new PlayerEntity(id, insertDto.getName(), insertDto.getTeam(),
                insertDto.getPosition());
        this.playerRepository.saveVoid(playerEntity);
    }

    @Override
    public void deleteByIdVoid(Long id) {
        this.playerRepository.deleteByIdVoid(id);
    }

}
