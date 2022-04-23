package com.softwear.webapp5.data;

import java.util.List;

import com.softwear.webapp5.model.ShopUser;

public class UserPageDTO {

    private List<ShopUser> users;
    private int totalPages;
    
    public UserPageDTO(List<ShopUser> users, int totalPages) {
        this.users = users;
        this.totalPages = totalPages;
    }

    public List<ShopUser> getUsers() {
        return users;
    }

    public void setUsers(List<ShopUser> users) {
        this.users = users;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }
  
}