package com.syc.security.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

/**
 *
 */
@Entity
public class Role implements GrantedAuthority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //得到当前的角色信息--角色名称
    @Override
    public String getAuthority() {

        return this.name;
    }

    @Override
    public String toString() {
        return name;
    }

}
