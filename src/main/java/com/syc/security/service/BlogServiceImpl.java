package com.syc.security.service;

import com.syc.security.dao.BlogDao;
import com.syc.security.domain.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BlogServiceImpl  {

    @Autowired
    private BlogDao blogDao;

    public List<Blog> findAll() {
        return blogDao.findAll();
    }

    public void deleteById(Long id) {
        blogDao.deleteById(id);
    }

}
