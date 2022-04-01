package ru.job4j.generic;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class RoleStoreTest {

    @Test
    public void whenAddAndFindThenUserRoleDeveloper() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        Role result = role.findById("1");
        assertThat(result.getUserRole(), is("Developer"));
    }

    @Test
    public void whenAddAndFindThenUserIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        Role result = role.findById("10");
        assertNull(result);
    }

    @Test
    public void whenAddDuplicateAndFindUserRoleIsDeveloper() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        role.add(new Role("1", "Engineer"));
        Role result = role.findById("1");
        assertThat(result.getUserRole(), is("Developer"));
    }

    @Test
    public void whenReplaceThenUserRoleIsEngineer() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        role.replace("1", new Role("1", "Engineer"));
        Role result = role.findById("1");
        assertThat(result.getUserRole(), is("Engineer"));
    }

    @Test
    public void whenNoReplaceRoleThenNoChangeUserRole() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        role.replace("10", new Role("10", "Engineer"));
        Role result = role.findById("1");
        assertThat(result.getUserRole(), is("Developer"));
    }

    @Test
    public void whenDeleteUserThenUserIsNull() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        role.delete("1");
        Role result = role.findById("1");
        assertNull(result);
    }

    @Test
    public void whenNoDeleteUserThenUsernameIsPetr() {
        RoleStore role = new RoleStore();
        role.add(new Role("1", "Developer"));
        role.delete("10");
        Role result = role.findById("1");
        assertThat(result.getUserRole(), is("Developer"));
    }
}
