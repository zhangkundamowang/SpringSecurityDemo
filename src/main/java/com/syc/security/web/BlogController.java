package com.syc.security.web;

import com.syc.security.domain.Blog;
import com.syc.security.service.BlogServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.util.List;


/*@PreAuthorize("hasAuthority('ROLE_ADMIN')")*/
@RestController
@RequestMapping("/blogs")
public class BlogController {

    @Autowired
    private BlogServiceImpl blogService;

   /* @PreAuthorize("hasAuthority('ROLE_ADMIN')")*/
    @GetMapping
    public ModelAndView showList(Model model) {
        List<Blog> blogs = blogService.findAll();
        model.addAttribute("blogs", blogs);
        // /templates/blogs/list.html
        return new ModelAndView("/blogs/list", "model", model);
    }

    //SpringSecurity相对于Shiro较优秀在于对安全控制粒度很细,可以细到具体的某个方法上!
    //此注解不用加前缀@PreAuthorize("hasAnyRole('USER','ADMIN')")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @GetMapping("/del/{id}")
    public ModelAndView deleteById(@PathVariable("id") Long id, Model model) {
        blogService.deleteById(id);
        model.addAttribute("blogs", blogService.findAll());
        return new ModelAndView("/blogs/list", "model", model);
    }

}
