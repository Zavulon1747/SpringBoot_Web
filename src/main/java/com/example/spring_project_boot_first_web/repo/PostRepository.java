package com.example.spring_project_boot_first_web.repo;


import com.example.spring_project_boot_first_web.models.Post;
import org.springframework.data.repository.CrudRepository;

public interface PostRepository extends CrudRepository<Post, Long> {

}
