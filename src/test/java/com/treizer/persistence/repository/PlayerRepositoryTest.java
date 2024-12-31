package com.treizer.persistence.repository;

import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.treizer.DataProvider;
import com.treizer.persistence.entity.PlayerEntity;

public class PlayerRepositoryTest {

    private PlayerRepository playerRepository;

    @BeforeEach
    public void init() {
        this.playerRepository = new PlayerRepository();
    }

    @Test
    public void testFindAll() {
        // Given
        // When
        Iterable<PlayerEntity> results = this.playerRepository.findAll();

        // Then
        Assertions.assertNotNull(results);
        Assertions.assertEquals(1L, results.iterator().next().getId());
        // System.out.println(results.iterator().next().getName());
        Assertions.assertEquals("Lionel Messi", results.iterator().next().getName());
        Assertions.assertEquals("Inter Miami", results.iterator().next().getTeam());
        Assertions.assertEquals("Delantero", results.iterator().next().getPosition());
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        // When
        Optional<PlayerEntity> result = this.playerRepository.findById(id);

        // Then
        Assertions.assertTrue(result.isPresent());
        Assertions.assertEquals("Lionel Messi", result.get().getName());
        Assertions.assertEquals("Inter Miami", result.get().getTeam());
        Assertions.assertEquals("Delantero", result.get().getPosition());
    }

    @Test
    public void testSave() {
        // Given
        PlayerEntity playerEntity = DataProvider.newPlayerEntityMock();

        // When
        PlayerEntity result = this.playerRepository.save(playerEntity);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(7L, result.getId());
        Assertions.assertEquals("Luis Díaz", result.getName());
        Assertions.assertEquals("Liverpool", result.getTeam());
        Assertions.assertEquals("Delantero", result.getPosition());
    }

    @Test
    public void testSaveIf() {
        // Given
        PlayerEntity playerEntity = DataProvider.playerEntityUpdatedMock();

        // When
        PlayerEntity result = this.playerRepository.save(playerEntity);

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertEquals(1L, result.getId());
        Assertions.assertEquals("Hugo Santana", result.getName());
        Assertions.assertEquals("Barcelona", result.getTeam());
        Assertions.assertEquals("Defensa", result.getPosition());
    }

    @Test
    public void testSaveVoid() {
        // Given
        PlayerEntity playerEntity = DataProvider.newPlayerEntityMock();

        // When
        this.playerRepository.saveVoid(playerEntity);

        // Then
        Optional<PlayerEntity> savedPlayer = this.playerRepository.findById(playerEntity.getId());

        Assertions.assertTrue(savedPlayer.isPresent());
        Assertions.assertEquals("Luis Díaz", savedPlayer.get().getName());
        Assertions.assertEquals("Liverpool", savedPlayer.get().getTeam());
        Assertions.assertEquals("Delantero", savedPlayer.get().getPosition());
    }

    @Test
    public void testDeleteById() {
        // Given
        Long id = 1L;

        // When
        this.playerRepository.deleteById(id);

        // Then
        Optional<PlayerEntity> result = this.playerRepository.findById(id);

        Assertions.assertFalse(result.isPresent());
    }

    @Test
    public void testDeleteByIdVoid() {
        // Given
        Long id = 1L;

        // When
        this.playerRepository.deleteByIdVoid(id);

        // Then
        Optional<PlayerEntity> deletedPlayer = this.playerRepository.findById(id);

        Assertions.assertFalse(deletedPlayer.isPresent());
    }

}
