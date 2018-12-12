package com.ravindra.practice.restapi.data;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "users", collectionResourceRel = "users")
public interface UserRestRepository extends PagingAndSortingRepository<User,Long> {
    List<String> findByRole(@Param("role") String role);
    List<String> findByNameContaining(@Param("name") String name);
}
