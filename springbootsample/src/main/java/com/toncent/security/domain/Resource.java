package com.toncent.security.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 14:31
 */
@Entity
@Table(name = "sec_resource")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String url;

    @ManyToMany(mappedBy = "resources")
    private Set<Role> roles = new HashSet<Role>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
