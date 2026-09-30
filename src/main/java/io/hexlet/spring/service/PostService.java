package io.hexlet.spring.service;

import io.hexlet.spring.dto.PostCreateDTO;
import io.hexlet.spring.dto.PostDTO;
import io.hexlet.spring.dto.PostUpdateDTO;

import java.util.List;

public interface PostService {
    List<PostDTO> getAll();
    PostDTO getById(Long id);
    PostDTO create(PostCreateDTO dto);
    PostDTO update(Long id, PostUpdateDTO dto);
    void delete(Long id);
}