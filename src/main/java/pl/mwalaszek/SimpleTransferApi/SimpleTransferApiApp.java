package pl.mwalaszek.SimpleTransferApi;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.servlet.ServletContainer;
import pl.mwalaszek.SimpleTransferApi.config.ServiceContainer;
import pl.mwalaszek.SimpleTransferApi.model.Account;
import pl.mwalaszek.SimpleTransferApi.rest.AccountResource;
import pl.mwalaszek.SimpleTransferApi.rest.BankTransferResource;

import java.math.BigDecimal;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SimpleTransferApiApp extends ResourceConfig {
    private static final Logger LOGGER = Logger.getLogger(SimpleTransferApiApp.class.getName());

    public void run() {
        ResourceConfig config = initializeResourceConfigAndSampleAccounts();
        ServletHolder jerseyServlet = new ServletHolder(new ServletContainer(config));
        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(server, "/");
        context.addServlet(jerseyServlet, "/api/*");
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            LOGGER.log(Level.INFO, e.getMessage());
        } finally {
            server.destroy();
        }
    }

    private ResourceConfig initializeResourceConfigAndSampleAccounts() {
        ServiceContainer serviceContainer = new ServiceContainer();
        ResourceConfig resourceConfig = new ResourceConfig();
        initSampleAccounts(serviceContainer);

        resourceConfig.register(new BankTransferResource(serviceContainer.getTransferService(), serviceContainer.getAccountsService()));
        resourceConfig.register(new AccountResource(serviceContainer.getAccountsService()));
        resourceConfig.register(new GenericExceptionMapper());
        return resourceConfig;
    }

    private void initSampleAccounts(ServiceContainer serviceContainer) {
        Account source = new Account("DE75512108001245126199");
        source.setBalance(new BigDecimal("400"));
        serviceContainer.getAccountsService().save(source);
        serviceContainer.getAccountsService().save(new Account("PL10105000997603123456789123"));
        serviceContainer.getAccountsService().save(new Account("NO8330001234567"));
    }
}