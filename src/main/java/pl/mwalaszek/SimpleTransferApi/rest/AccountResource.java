package pl.mwalaszek.SimpleTransferApi.rest;

import pl.mwalaszek.SimpleTransferApi.service.AccountsService;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/accounts")
public class AccountResource {
    private AccountsService accountsService;

    public AccountResource(AccountsService accountsService) {
        this.accountsService = accountsService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Account> getAllAccounts(){
        return accountsService.findAll();
    }

    @GET
    @Path("/history/{iban}")
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Transfer> accountHistory(@PathParam("iban") String iban){
        return accountsService.getHistory(iban);
    }
}
