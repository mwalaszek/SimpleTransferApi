package pl.mwalaszek.SimpleTransferApi.rest;

import pl.mwalaszek.SimpleTransferApi.service.AccountsService;
import pl.mwalaszek.SimpleTransferApi.service.TransferService;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.model.Transfer;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@Path("/transfers")
public class BankTransferResource {
    private TransferService transferService;
    private AccountsService accountsService;

    public BankTransferResource(TransferService transferService, AccountsService accountsService) {
        this.transferService = transferService;
        this.accountsService = accountsService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Account> executeBankTransfer(Transfer transfer) {
        transferService.executeTransfer(transfer);
        return accountsService.findAll();
    }
}
