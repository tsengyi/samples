package com.toncent.security.domain;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 11:15
 */
@Entity
@Table(name = "sec_role")
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column
    private String roleName;

    @ManyToMany(mappedBy = "roles")
    private Set<User> users = new HashSet<User>();


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "sec_role_resource", joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "resource_id"))
    private Set<Resource> resources = new HashSet<Resource>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getAuthority() {
        return "ROLE_" + id;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Resource> getResources() {
        return resources;
    }

    public void setResources(Set<Resource> resources) {
        this.resources = resources;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void addUser(User user) {
        user.getAuthorities().add(this);
        this.users.add(user);
    }

    public void addUsers(Iterable<User> users) {
        for (Iterator<User> iterator = this.users.iterator(); iterator.hasNext(); ) {
            User user = iterator.next();
            this.addUser(user);
        }
    }

    public void addResource(Resource resource) {
        resource.getRoles().add(this);
        this.resources.add(resource);
    }

    public void addResources(Iterable<Resource> resources) {
        for (Iterator<Resource> iterator = this.resources.iterator(); iterator.hasNext(); ) {
            Resource resource = iterator.next();
            this.addResource(resource);
        }
    }
}
