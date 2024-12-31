package com.treizer.presentation.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.treizer.DataProvider;
import com.treizer.presentation.dto.PlayerInsertDto;
import com.treizer.presentation.dto.PlayerUpdateDto;
import com.treizer.service.implementation.PlayerService;

@ExtendWith(MockitoExtension.class)
public class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    @Test
    public void testFindAll() {
        // Given

        // When
        when(this.playerService.findAll()).thenReturn(DataProvider.playerDtoListMock());
        String result = this.playerController.findAll();

        // Then
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(this.playerService).findAll();
        verifyNoMoreInteractions(this.playerService);
    }

    @Test
    public void testFindById() {
        // Given
        Long id = 1L;

        // When
        when(this.playerService.findById(anyLong())).thenReturn(DataProvider.playerDtoMock());
        String result = this.playerController.findById(id);

        // Then
        Assertions.assertInstanceOf(String.class, result);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(this.playerService).findById(anyLong());
        verifyNoMoreInteractions(this.playerService);
    }

    @Test
    public void testSave() {
        // Given
        PlayerInsertDto insertDto = DataProvider.playerInsertDtoMock();

        // When
        when(this.playerService.save(any(PlayerInsertDto.class))).thenReturn(DataProvider.newPlayerDtoMock());
        String result = this.playerController.save(insertDto);

        // Then
        Assertions.assertInstanceOf(String.class, result);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(this.playerService).save(any(PlayerInsertDto.class));
        verifyNoMoreInteractions(this.playerService);
    }

    @Test
    public void testUpdate() {
        // Given
        PlayerUpdateDto updateDto = DataProvider.playerUpdateDtoMock();
        Long id = 1L;

        // When
        when(this.playerService.update(any(PlayerUpdateDto.class), anyLong()))
                .thenReturn(DataProvider.playerDtoUpdatedMock());
        String result = this.playerController.update(updateDto, id);

        // Then
        Assertions.assertInstanceOf(String.class, result);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(this.playerService).update(any(PlayerUpdateDto.class), anyLong());
        verifyNoMoreInteractions(this.playerService);
    }

    @Test
    public void testDeleteById() {
        // Given
        Long id = 1L;

        // When
        when(this.playerService.deleteById(anyLong())).thenReturn(DataProvider.playerDtoMock());
        String result = this.playerController.deleteById(id);

        // Then
        Assertions.assertInstanceOf(String.class, result);
        Assertions.assertNotNull(result);
        Assertions.assertFalse(result.isEmpty());
        verify(this.playerService).deleteById(anyLong());
        verifyNoMoreInteractions(this.playerService);
    }

}
