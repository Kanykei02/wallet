package com.wallet.service;

    import com.wallet.dao.UserDao;
    import com.wallet.model.User;

    import javax.ws.rs.*;
    import javax.ws.rs.core.MediaType;
    import java.util.List;

@Path("/users")
public class UserService {

    private UserDao userDao = new UserDao();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<User> getAllUsers(){ return userDao.getAllUsers(); }

    @GET
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public User getUserById(@PathParam("userId") int userId){
        return userDao.getUserById(userId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createUser(User user){
        return userDao.createUser(user);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateUser(User user){
        return userDao.updateUser(user);
    }

    @DELETE
    @Path("/{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteUser(@PathParam("userId") int userId){
        return userDao.deleteUserById(userId);
    }
}
