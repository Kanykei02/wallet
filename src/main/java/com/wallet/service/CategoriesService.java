package com.wallet.service;

import com.wallet.dao.CategoriesDao;
import com.wallet.dao.DbConnection;
import com.wallet.dao.UserDao;
import com.wallet.model.Categories;
import com.wallet.model.Orders;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


@Path("/categories")
public class CategoriesService {
        private CategoriesDao CategoryDao = new CategoriesDao();
        @GET
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        public List<Categories> getAllCategories(){ return CategoryDao.getAllCategories(); }

        @GET
        @Path("/{categoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        public Categories getCategoryById(@PathParam("categoryId") int categoryId){ return CategoriesDao.getCategoriesById(categoryId); }

        @POST
        @Produces(MediaType.APPLICATION_JSON)
        public String createCategory(Categories category){ return CategoriesDao.createCategory(category); }

        @PUT
        @Path("/{categoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        public String updateCategory(Categories category, @PathParam("categoryId") int categoryId){ return CategoryDao.updateCategory(category); }

        @DELETE
        @Path("/{categoryId}")
        @Produces(MediaType.APPLICATION_JSON)
        public String  deleteCategory(@PathParam("categoryId") int categoryId){ return CategoryDao.deleteCatregoryById(categoryId); }
    }

