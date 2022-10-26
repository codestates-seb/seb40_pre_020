package com.SEB_Pre_020.demo.Post.mapper;

import com.SEB_Pre_020.demo.Post.dto.PostDto;
import com.SEB_Pre_020.demo.Post.entity.Post;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-10-26T23:49:28+0900",
    comments = "version: 1.5.3.Final, compiler: javac, environment: Java 13.0.1 (Oracle Corporation)"
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
