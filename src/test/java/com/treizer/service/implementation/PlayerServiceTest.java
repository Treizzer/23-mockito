package com.treizer.service.implementation;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.treizer.DataProvider;
import com.treizer.persistence.entity.PlayerEntity;
import com.treizer.persistence.repository.PlayerRepository;
import com.treizer.presentation.dto.PlayerDto;
import com.treizer.presentation.dto.PlayerInsertDto;
import com.treizer.presentation.dto.PlayerUpdateDto;

/**
 * The error that happend when you execute the test is only
 * a problem with the JVM at version 21
 * Fix: -ea -XX:+EnableDynamicAgentLoading -Xshare:off
 */

@ExtendWith(MockitoExtension.class)
public class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    // @BeforeEach
    // public void init() {
    // MockitoAnnotations.openMocks(this);
    // // PlayerRepository playerRepository = new PlayerRepository();
    // // this.playerRepository = Mockito.mock(PlayerRepository.class); // Simular =
    // // Mock
    // // this.playerService = new PlayerService(this.playerRepository);
    // // this.playerController = new PlayerController(playerService);
    // }

    @Test
    public void testFindAll() {
        // Given

        // When
        when(this.playerRepository.findAll()).thenReturn(DataProvider.playerEntityListMock());
        List<PlayerDto> results = this.playerService.findAll();

        // Then
        Assertions.assertNotNull(results);
        Assertions.assertFalse(results.isEmpty());
        Assertions.assertEquals("Lionel Messi", results.get(0).getName());
        Assertions.assertEquals("Inter Miami", results.get(0).getTeam());
        Assertions.assertEquals("Delantero", results.get(0).getPosition());
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        // When
        // anyLong() -> The statement accept any long number
        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.optionalPlayerEntityMock());
        PlayerDto playerDto = this.playerService.findById(id);

        // Then
        Assertions.assertNotNull(playerDto);
        Assertions.assertEquals("Lionel Messi", playerDto.getName());
        Assertions.assertEquals("Inter Miami", playerDto.getTeam());
        Assertions.assertEquals("Delantero", playerDto.getPosition());
        verify(this.playerRepository).findById(anyLong());
        // You need to use it only in loops
        // verify(this.playerRepository, times(5)).findById(anyLong());
    }

    @Test
    public void testFindByIdError() {
        // Given
        Long id = 10L;

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.playerService.findById(id);
        });
        verify(this.playerRepository).findById(anyLong());
    }

    @Test
    public void testSave() {
        // Given
        PlayerInsertDto insertDto = DataProvider.playerInsertDtoMock();

        // When
        when(this.playerRepository.save(any(PlayerEntity.class))).thenReturn(DataProvider.newPlayerEntityMock());
        PlayerDto playerDto = this.playerService.save(insertDto);

        // Then
        Assertions.assertNotNull(playerDto);
        Assertions.assertEquals("Luis Díaz", playerDto.getName());
        Assertions.assertEquals("Liverpool", playerDto.getTeam());
        Assertions.assertEquals("Delantero", playerDto.getPosition());
        verify(this.playerRepository).save(any(PlayerEntity.class));
    }

    @Test
    public void testSaveError() {
        // Given
        PlayerInsertDto insertDto = DataProvider.playerInsertDtoMock();

        // When
        when(this.playerRepository.size()).thenReturn(DataProvider.sizePlayerEntityListMock());
        when(this.playerRepository.save(any(PlayerEntity.class))).thenThrow(UnsupportedOperationException.class);

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.playerService.save(insertDto);
        });

        // Verify that save was called with any PlayerEntity
        verify(this.playerRepository).save(any(PlayerEntity.class));

        // Optional: Verify no other interactions occurred
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testSaveVoid() {
        // Given
        PlayerInsertDto insertDto = DataProvider.playerInsertDtoMock();

        // When
        /*
         * With a void method you can't evaluate the answer
         * But you can evalute the argument
         */
        this.playerService.saveVoid(insertDto);

        // Then
        ArgumentCaptor<PlayerEntity> playerCaptor = ArgumentCaptor.forClass(PlayerEntity.class);
        verify(this.playerRepository).saveVoid(any(PlayerEntity.class));
        verify(this.playerRepository).saveVoid(playerCaptor.capture());

        Assertions.assertEquals(10L, playerCaptor.getValue().getId());
        Assertions.assertEquals("Luis Díaz", playerCaptor.getValue().getName());
    }

    @Test
    public void testUpdateWithId() {
        // Given
        PlayerUpdateDto updateDto = DataProvider.playerUpdateDtoMock();
        Long id = 1L;

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.optionalPlayerEntityMock());
        when(this.playerRepository.save(any(PlayerEntity.class))).thenReturn(DataProvider.playerEntityUpdatedMock());
        PlayerDto playerDto = this.playerService.update(updateDto, id);

        // Then
        Assertions.assertNotNull(playerDto);
        Assertions.assertEquals("Hugo Santana", playerDto.getName());
        Assertions.assertEquals("Barcelona", playerDto.getTeam());
        Assertions.assertEquals("Defensa", playerDto.getPosition());
        verify(this.playerRepository).findById(anyLong());
        verify(this.playerRepository).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testUpdateWithoutId() {
        // Given
        PlayerUpdateDto updateDto = DataProvider.playerUpdateDtoMock();

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.optionalPlayerEntityMock());
        when(this.playerRepository.save(any(PlayerEntity.class))).thenReturn(DataProvider.playerEntityUpdatedMock());
        PlayerDto playerDto = this.playerService.update(updateDto, null);

        // Then
        Assertions.assertNotNull(playerDto);
        Assertions.assertEquals("Hugo Santana", playerDto.getName());
        Assertions.assertEquals("Barcelona", playerDto.getTeam());
        Assertions.assertEquals("Defensa", playerDto.getPosition());
        verify(this.playerRepository).findById(anyLong());
        verify(this.playerRepository).save(any(PlayerEntity.class));
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testUpdateErrorWithId() {
        // Given
        Long id = 10L;

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.playerService.update(null, id);
        });

        verify(this.playerRepository).findById(anyLong());
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testUpdateErrorWithoutId() {
        // Given
        PlayerUpdateDto updateDto = DataProvider.playerUpdateDtoErrorMock();

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.playerService.update(updateDto, null);
        });

        verify(this.playerRepository).findById(anyLong());
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testDeleteById() {
        // Given
        Long id = 1L;

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(DataProvider.optionalPlayerEntityMock());
        PlayerDto playerDto = this.playerService.deleteById(id);

        // Then
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository).deleteById(anyLong());
        verify(this.playerRepository).deleteById(longCaptor.capture());

        Assertions.assertEquals(1L, longCaptor.getValue());
        Assertions.assertEquals("Lionel Messi", playerDto.getName());
        Assertions.assertEquals("Inter Miami", playerDto.getTeam());
        Assertions.assertEquals("Delantero", playerDto.getPosition());

        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testDeleteByIdError() {
        // Given
        Long id = 10L;

        // When
        when(this.playerRepository.findById(anyLong())).thenReturn(Optional.empty());

        // Then
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            this.playerService.deleteById(id);
        });

        verify(this.playerRepository).findById(anyLong());
        verifyNoMoreInteractions(this.playerRepository);
    }

    @Test
    public void testDeleteByIdVoid() {
        // Given
        Long id = 1L;

        // When
        this.playerService.deleteByIdVoid(id);

        // Then
        ArgumentCaptor<Long> longCaptor = ArgumentCaptor.forClass(Long.class);
        verify(this.playerRepository).deleteByIdVoid(anyLong());
        verify(this.playerRepository).deleteByIdVoid(longCaptor.capture());

        Assertions.assertEquals(1L, longCaptor.getValue());
    }

}
