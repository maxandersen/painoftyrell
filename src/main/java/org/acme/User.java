package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Users")
public class User extends PanacheEntityBase {
    public @Id @GeneratedValue long userId;
    public String name;

    // manytomany from firt google hit of "map manytomany"
    // https://vladmihalcea.com/the-best-way-to-use-the-manytomany-annotation-with-jpa-and-hibernate/
    @ManyToMany(cascade = {
            CascadeType.PERSIST,
            CascadeType.MERGE
    })
    @JoinTable(name = "ComanyUsers",
            joinColumns = @JoinColumn(name = "userid"),
            inverseJoinColumns = @JoinColumn(name = "companyid")
    )
    public List<Company> companies = new ArrayList<>();

    //Getters and setters ommitted for brevity

    public void addCompany(Company company) {
        companies.add(company);
        company.users.add(this);
    }

    public void removeCompany(Company company) {
        companies.remove(company);
        company.users.remove(this);
    }

}
