package works.buddy.library.api.config;

import works.buddy.library.api.ApiConstants;
import works.buddy.library.api.errors.BadRequestException;
import works.buddy.library.api.errors.Error;
import works.buddy.library.api.errors.Errors;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

public class BadRequestExceptionMapper implements ExceptionMapper<BadRequestException> {

    @Override
    public Response toResponse(BadRequestException e) {
        return Response.status(Response.Status.BAD_REQUEST).header(ApiConstants.CONTENT_TYPE_HEADER, ApiConstants.APPLICATION_JSON).entity(
                new Errors().addError(new Error(e.getMessage()))).build();
    }
}
