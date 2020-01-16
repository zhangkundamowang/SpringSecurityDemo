package com.syc.security.domain;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

/**
 * ORM:对象关系映射.
 * O: User类.
 * R: user表.
 * M: User与user表之间得有一个映射关系.
 */
@Entity
@Data
public class User implements UserDetails {

    //这是主键
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column
    private String password;


    //JoinTable:指明两个表中间的关联表.
    //referencedColumnName:对应被关联的那个表的主键名称
    //注意;joinColumns与inverseJoinColumns的区别.
    //cascade:级联操作:级联更新/删除;
    //fetch:对象的加载策略:饥饿模式和延迟加载模式.
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    //JoinTable:设置多表关联时连接的表
    @JoinTable(name = "user_role",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private List<Role> authorities;


    public void setAuthorities(List<Role> authorities) {
        this.authorities = authorities;
    }

    //获取用户的身份,角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    //判断账号是否没有过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    //判断账号是否没有锁定
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    //判断密码是否没有过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    //是否可用
    @Override
    public boolean isEnabled() {
        return true;
    }

}
