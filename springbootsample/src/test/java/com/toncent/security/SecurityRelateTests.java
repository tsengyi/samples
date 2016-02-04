package com.toncent.security;

import com.toncent.MallApplication;
import com.toncent.security.domain.Resource;
import com.toncent.security.domain.Role;
import com.toncent.security.domain.User;
import com.toncent.security.repository.ResourceRepository;
import com.toncent.security.repository.RoleRepository;
import com.toncent.security.repository.UserRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

/**
 * AUTHOR: 819521
 * DATE  : 2016/2/4
 * TIME  : 11:26
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(MallApplication.class)
public class SecurityRelateTests {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    @Test
    @Transactional
    public void testSaveUser() {
        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        userRepository.save(user);

        User dbUser = userRepository.findByUsername("username");

        Assert.assertEquals("same user", user, dbUser);
    }

    @Test
    @Transactional
    public void testUserRole() {


        Role role = new Role();
        role.setRoleName("role");
        roleRepository.save(role);

        User user = new User();
        user.setUsername("username");
        user.setPassword("password");

        user.addRole(role);
        userRepository.save(user);

        User dbUser = userRepository.findByUsername("username");

        Set<Role> authorities = dbUser.getAuthorities();
        System.out.println("authorities = " + authorities.size());

        Assert.assertEquals("same user", user, dbUser);
        Assert.assertEquals("has 1 role", 1, authorities.size());

    }

    @Test
    @Transactional
    public void testRoleResource() {


        Resource resource = new Resource();
        resource.setUrl("url");
        resourceRepository.save(resource);

        Role role = new Role();
        role.setRoleName("rolename");

        role.addResource(resource);
        roleRepository.save(role);

        Role dbRole = roleRepository.findOne(1L);

        Set<Resource> authorities = dbRole.getResources();

        Assert.assertEquals("same role", role, dbRole);
        Assert.assertEquals("has 1 resource", 1, authorities.size());

    }

}
