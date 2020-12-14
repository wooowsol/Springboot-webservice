package com.sol.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sol.web.domain.posts.Posts;
import com.sol.web.domain.posts.PostsRepository;
import com.sol.web.dto.PostsResponseDto;
import com.sol.web.dto.PostsSaveRequestDto;
import com.sol.web.dto.PostsUpdateRequestDto;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate; // 외부 연동 및 JPA 기능까지 한번에 테스트할 때는 @SpringBootTest와 TestRestTemplate을 사용함.

    @Autowired
    private PostsRepository postsRepository;

    @Autowired
    private WebApplicationContext context;

    @After
    public void tearDown() throws Exception{
        postsRepository.deleteAll();
    }

    @Test
    public void Posts_등록된다() throws Exception{
        // given
        String title = "title";
        String content = "content";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
                .title(title)
                .content(content)
                .author("author")
                .build();

        String url = "http://localhost:"+ port + "/api/v1/posts";   // 호출할 URL

        //when
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto, Long.class);

        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();
        assertThat(all.get(0).getTitle()).isEqualTo(title);
        assertThat(all.get(0).getContent()).isEqualTo(content);
    }


    @Test
    public void Posts_수정된다() {
        //given
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("handsomekey")
                .build());

        Long updateId = savedPost.getId();
        String expectedTitle = "title2";
        String expectedContent = "content2";

        PostsUpdateRequestDto requestDto = PostsUpdateRequestDto.builder()
                .title(expectedTitle)
                .content(expectedContent)
                .build();

        String url = "http://localhost:" + port + "/api/v1/posts/" + updateId;

        HttpEntity<PostsUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = restTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);
        //then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> all = postsRepository.findAll();

        assertThat(all.get(0).getTitle()).isEqualTo(expectedTitle);
        assertThat(all.get(0).getContent()).isEqualTo(expectedContent);
    }


    @Test
    public void Posts_조회된다() {
        Posts savedPost = postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("handsomekey")
                .build());

        Long savedId = savedPost.getId();

        String url = "http://localhost:" + port + "/api/v1/posts/" + savedId;

        ResponseEntity<PostsResponseDto> responseEntity = restTemplate.getForEntity(url, PostsResponseDto.class);

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getTitle()).isEqualTo("title");
        assertThat(responseEntity.getBody().getContent()).isEqualTo("content");
        assertThat(responseEntity.getBody().getAuthor()).isEqualTo("handsomekey");
    }
}

