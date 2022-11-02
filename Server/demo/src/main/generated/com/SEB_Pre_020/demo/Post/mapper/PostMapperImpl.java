package com.SEB_Pre_020.demo.post.mapper;

import com.SEB_Pre_020.demo.post.dto.PostDto;
import com.SEB_Pre_020.demo.post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-11-02T12:28:15+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 11.0.15 (Azul Systems, Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Override
    public List<PostDto.Response> postsToPostResponses(List<Post> posts) {
        if ( posts == null ) {
            return null;
        }

        List<PostDto.Response> list = new ArrayList<PostDto.Response>( posts.size() );
        for ( Post post : posts ) {
            list.add( postToPostResponse( post ) );
        }

        return list;
    }
}
