package org.acme;

import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.inject.Inject;
import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("/hello")
public class GreetingResource {
    @Inject
    EntityManager em;

    public List<Products> list = new ArrayList<>();

    public static String reverseString(String str) {
        return new StringBuilder(str).reverse().toString();
    }

//
//    public LinkedListResource() {
//        objectLinkedList.add(new Products(1, "Sberbank"));
//        objectLinkedList.add(new Products(2, "Tropical fruit"));
//    }


    private String helloString() {
        return "olo";
    }



    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response post(@Valid List<Products> prod) {
        list = prod;
        for (Products pr : list)
            pr.persist();
        return null;
    }

    @GET
    @Path("{id}/getOne")
    @Produces(MediaType.APPLICATION_JSON)
    public Products getOne(@PathParam Long id) {
        return em.find(Products.class, id);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> getAll() {
        Query query = em.createQuery("SELECT c from Products c where c.nameOfProduct LIKE '%'");
        //return helloString();
        List<Products> list = query.getResultList();
        return list;
    }

    @GET
    @Path("/find")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Products> find(@Valid Products prod){
        final Query query = em.createQuery("SELECT c FROM Products c where c.nameOfProduct LIKE :name OR c.product_id = :id " +
                "AND c.product_price = :price OR c.product_owner LIKE :owner"
                )

        .setParameter("name", "%" + prod.getNameOfProduct() + "%")
        .setParameter("id", prod.getProduct_id())
        .setParameter("price", prod.getProduct_price())
        .setParameter("owner", prod.getProduct_owner());

        return (List<Products>) query.getResultList();
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON)
    public void delete() {
    }

}