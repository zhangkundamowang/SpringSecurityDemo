package com.syc.security.dao;

import com.syc.security.domain.Blog;
import com.syc.security.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogDao extends JpaRepository<Blog, Long> {

}
