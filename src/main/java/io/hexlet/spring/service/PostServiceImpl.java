package io.hexlet.spring.service;

import io.hexlet.spring.dto.PostCreateDTO;
import io.hexlet.spring.dto.PostDTO;
import io.hexlet.spring.dto.PostUpdateDTO;
import io.hexlet.spring.exception.ResourceNotFoundException;
import io.hexlet.spring.mapper.PostMapper;
import io.hexlet.spring.model.Post;
import io.hexlet.spring.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final PostMapper postMapper;

    @Override
    @Transactional(readOnly = true)
    public List<PostDTO> getAll() {
        return postRepository.findAll().stream()
                .map(postMapper::map)
                .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public PostDTO getById(Long id) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found: " + id));
        return postMapper.map(post);
    }

    @Override
    public PostDTO create(PostCreateDTO dto) {
        Post post = postMapper.map(dto);
        postRepository.save(post);
        return postMapper.map(post);
    }

    @Override
    public PostDTO update(Long id, PostUpdateDTO dto) {
        Post post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post not found: " + id));
        postMapper.update(dto, post);
        // save() здесь уже не обязателен — dirty-checking сработает сам,
        // но оставим для наглядности
        postRepository.save(post);
        return postMapper.map(post);
    }

    @Override
    public void delete(Long id) {
        postRepository.deleteById(id);
    }
}