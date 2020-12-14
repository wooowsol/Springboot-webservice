package com.sol.web.service.posts;

import com.sol.web.domain.posts.Posts;
import com.sol.web.domain.posts.PostsRepository;
import com.sol.web.dto.PostsListResponseDto;
import com.sol.web.dto.PostsResponseDto;
import com.sol.web.dto.PostsSaveRequestDto;
import com.sol.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor // final이 선언된 모든 필드를 인자값으로 하는 생성자를 만들어줌
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    @Transactional
    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("헤당 게시글이 없습니다. id="+id));
        return new PostsResponseDto(entity);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc(){ // PostsRepository 결과로 넘어온 List에 저장된 각각의 Posts를 PostsListResponseDto 만들고 다시 리스트에 넣는다.
        return postsRepository.findAllDesc().stream()
                .map(PostsListResponseDto::new)
                .collect(Collectors.toList());
    }

//  findAllDesc() -> List<Posts>로 반환
//  .stream() -> List에 저장된 각각의 원소들에 대하여 stream 진행 -> 전체가 아니라 Posts 하나씩 로직 처리하게 함
//  .map(PostsListResponseDto::new) -> PostsListResponseDto 생성자 호출 ( .map(posts -> new PostsListResponseDto(posts)) 와 같은 의미)
//  stream으로 인해 하나씩 분리된 posts를 인자로 넘겨 PostsListResponseDto 생성자의 매개변수(Posts 변수)로 넣는다.

    @Transactional
    public void delete (Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(()->new
                IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        postsRepository.delete(posts);
    }

}