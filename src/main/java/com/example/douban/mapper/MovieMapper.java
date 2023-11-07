package com.example.douban.mapper;

import com.example.douban.pojo.Movie;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface MovieMapper {
    Movie findMovieById(@Param("id") String id);

    ArrayList<Movie> findMovieByTag(String tag);

    ArrayList<Movie> findMovieByKeywords(@Param("keyword") String keyword);
}
