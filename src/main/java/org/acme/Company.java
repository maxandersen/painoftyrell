package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
public class Company extends PanacheEntityBase {
    public @Id @GeneratedValue long companyId;
    @Column(unique=true)
    String companyName;
}
