package com.rmg.rantMyGames.service;

import java.util.List;

public interface ICRUDService <DTO, Entity>{
    void create(DTO dto) throws Exception;
    DTO read(Long id) throws Exception;
    void delete(Long id) throws Exception;
    void update(DTO dto) throws Exception;
    List<DTO> getAll();
}
