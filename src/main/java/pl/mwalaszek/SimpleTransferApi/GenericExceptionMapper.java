package pl.mwalaszek.SimpleTransferApi;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class GenericExceptionMapper implements ExceptionMapper<RuntimeException> {
    @Override
    public Response toResponse(RuntimeException e) {
        return Response.status(400).
                entity(e.getMessage()).
                type("text/plain").
                build();
    }
}
