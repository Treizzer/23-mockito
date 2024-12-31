package com.treizer.presentation.controller;

import com.treizer.presentation.dto.PlayerDto;
import com.treizer.presentation.dto.PlayerInsertDto;
import com.treizer.presentation.dto.PlayerUpdateDto;
import com.treizer.service.interfaces.ICommonService;

public class PlayerController {

    private final ICommonService<PlayerDto, PlayerInsertDto, PlayerUpdateDto> playerService;

    public PlayerController(ICommonService<PlayerDto, PlayerInsertDto, PlayerUpdateDto> playService) {
        this.playerService = playService;
    }

    public String findAll() {
        StringBuilder sb = new StringBuilder();
        var players = this.playerService.findAll();
        sb.append("[");
        players.forEach(p -> sb.append(p.toString() + " "));
        sb.append("]");

        return sb.toString();
    }

    public String findById(Long id) {
        return this.playerService.findById(id).toString();
    }

    public String save(PlayerInsertDto insertDto) {
        return this.playerService.save(insertDto).toString();
    }

    public String update(PlayerUpdateDto updateDto, Long id) {
        return this.playerService.update(updateDto, id).toString();
    }

    public String deleteById(Long id) {
        return this.playerService.deleteById(id).toString();
    }

}
