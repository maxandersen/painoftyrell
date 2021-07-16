package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company extends PanacheEntityBase {
    public @Id @GeneratedValue long companyId;
    @Column(unique=true)
    public String companyName;

    @ManyToMany(mappedBy = "companies")
    public List<User> users = new ArrayList<>();

    public void addUser(User user) {
        users.add(user);
        user.companies.add(this);
    }

    public void removeUser(User user) {
        users.remove(user);
        user.companies.remove(this);
    }
}
