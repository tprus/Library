package works.buddy.library.api.config;

import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.Error;
import works.buddy.library.api.errors.Errors;
import works.buddy.library.api.errors.NotFoundException;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {

    @Override
    public Response toResponse(NotFoundException e) {
        return Response.status(Response.Status.NOT_FOUND).header(ApiConstants.CONTENT_TYPE_HEADER, ApiConstants.APPLICATION_JSON).entity(
                new Errors().addError(new Error(e.getMessage()))).build();
    }
}
