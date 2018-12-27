package pl.mwalaszek.SimpleTransferApi.rest;

import pl.mwalaszek.SimpleTransferApi.model.Transfer;
import pl.mwalaszek.SimpleTransferApi.service.AccountsService;
import pl.mwalaszek.SimpleTransferApi.service.TransferService;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
    public Transfer executeBankTransfer(Transfer transfer) {
        transferService.executeTransfer(transfer);
        return transfer;
    }
}
