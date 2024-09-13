/**
 * @Time: 2024/8/29 9:12
 * @Author: guoxun
 * @File: LoginUser
 * @Description:
 */

package com.pipi.xojauthservice.pojo.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Objects;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class LoginUser implements UserDetails {

    /**
     * 用户角色
     */
    private Integer role;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 是否被锁定
     */
    private Boolean lockBit;

    /**
     * 删除标志位
     */
    private Boolean deleteBit;

    /**
     * 角色映射数组
     * roleMapping
     */
    @JsonIgnore
    private String[] roleMapping = {"admin", "manager", "user", "guest"};

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(roleMapping[role]));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !lockBit;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleteBit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LoginUser loginUser = (LoginUser) o;
        return Objects.equals(role, loginUser.role) && Objects.equals(password, loginUser.password)
                && Objects.equals(email, loginUser.email) && Objects.equals(lockBit, loginUser.lockBit)
                && Objects.equals(deleteBit, loginUser.deleteBit);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, password, email, lockBit, deleteBit);
    }
}
