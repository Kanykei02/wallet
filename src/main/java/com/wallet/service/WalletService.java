package com.wallet.service;

import com.wallet.dao.WalletDao;
import com.wallet.model.Wallet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/wallets")
public class WalletService {
    private WalletDao WalletDao = new WalletDao();
    @GET
    @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
    public List<Wallet> getAllUsers(){ return WalletDao.getAllWallets(); }

    @GET
    @Path("/{walletId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Wallet getWalletById(@PathParam("walletId") int walletId){
        return WalletDao.getWalletById(walletId);
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public String createWallet (Wallet wallet){ return WalletDao.createWallet(wallet); }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public String updateWallet(Wallet wallet){
        return WalletDao.updateWallet(wallet);
    }

    @DELETE
    @Path("/{walletId}")
    @Produces(MediaType.APPLICATION_JSON)
    public String deleteWallet(@PathParam("walletId") int walletId){
        return WalletDao.deleteWalletById(walletId);
    }
}