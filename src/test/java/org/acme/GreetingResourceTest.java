package org.acme;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

@QuarkusTest
public class GreetingResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
          .when().get("/hello")
          .then()
             .statusCode(200)
             .body(is("Hello RESTEasy"));
    }

    @Test
    @Transactional
    public void testUser() {

        User u = new User();
        u.persist();

        var users = User.findAll();
        assertEquals(users.count(), 1);

    }

    @Test
    @Transactional
    public void testCompany() {
        var companies = Company.findAll();
        assertEquals(companies.count(), 0);

        Company c = new Company();
        c.persist();

        companies = Company.findAll();
        assertEquals(companies.count(), 1);

    }

    @Test
    @Transactional
    public void testManyToMany() {

        User max = new User();
        max.name = "Max";
        max.persist();

        User jim = new User();
        jim.name = "Jim";
        jim.persist();

        Company redhat = new Company();
        redhat.companyName = "redhat";
        redhat.addUser(max);
        redhat.addUser(jim);
        redhat.persist();

        assert(max.companies.contains(redhat));
        assert(jim.companies.contains(redhat));

        //https://stackoverflow.com/questions/36293898/hql-many-to-many-query
        var result = Company.find("from User u join u.companies c where c.companyName = 'redhat'");
        assertEquals(2, result.stream().count());
        assert(result.firstResult() == jim || result.firstResult() == max);


    }

}