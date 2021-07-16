package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;

public class User extends PanacheEntityBase {
    public @Id @GeneratedValue long userId;
    String name;
}
