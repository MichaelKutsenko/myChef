package com.myChef.JPA;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mocart on 07-Mar-17.
 */
@Entity
@Table(name = "Ugroups", schema = "my_chef_db")
public class Ugroup  implements Serializable {
    private long groupId;
    private String groupName;
    private String description;
    private List<User> users  = new ArrayList<>();

    @Id
    @Column(name = "group_id")
    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    @Basic
    @Column(name = "group_name")
    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    @Basic
    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToMany(mappedBy = "ugroups", cascade = CascadeType.DETACH)
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ugroup ugroups = (Ugroup) o;

        if (groupId != ugroups.groupId) return false;
        if (groupName != null ? !groupName.equals(ugroups.groupName) : ugroups.groupName != null) return false;
        if (description != null ? !description.equals(ugroups.description) : ugroups.description != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (groupId ^ (groupId >>> 32));
        result = 31 * result + (groupName != null ? groupName.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        return result;
    }

    public void addUser(User user){
        if (!users.contains(user)){
            users.add(user);
        }
    }
}
