package org.example.dto.post;

import lombok.RequiredArgsConstructor;
import org.example.mapper.PostMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class PostRepository {
    private final PostMapper postMapper;

    public List<PostDto> findAll(){
        return postMapper.findAll();
    }
    public List<PostDto> findByCondition(String title, String content){
        return postMapper.findByCondition(title, content);
    }
    public PostDto updatePost(int id, String title, String content){
        return postMapper.updatePost(id, title, content);
    }
    public PostDto findById(int id){
        return postMapper.findById(id);
    }
    public int deleteById(Long id){
        return postMapper.deleteById(id);
    }
    public int save(String title, String content) {
        return postMapper.save(title, content);
}
}
