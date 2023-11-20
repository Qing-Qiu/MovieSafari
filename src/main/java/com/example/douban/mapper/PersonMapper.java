package com.example.douban.mapper;

import com.example.douban.pojo.Person;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Mapper
@Repository
public interface PersonMapper {
    Person findPersonById(@Param("id") String id);

    ArrayList<Person> findPersonByMovie(@Param("id") String id, @Param("limit") String limit, @Param("offset") String offset);

    Integer countPersonByMovie(@Param("id") String id);
}
