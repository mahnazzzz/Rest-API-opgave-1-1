package httpErrors;

import exception.QuoteNotFoundException;
import javax.ws.rs.NotFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.servlet.ServletContext;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class QuoteNotFoundExceptionMapper implements ExceptionMapper<QuoteNotFoundException> {

  private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

  @Context
  ServletContext context;

  @Override
  public Response toResponse(QuoteNotFoundException ex) {
    JsonObject error = new JsonObject();
    JsonObject errorDetail = new JsonObject();
    int statusCode = 404;
    errorDetail.addProperty("code", statusCode);
    errorDetail.addProperty("message", "The requested quote was not found on our server");
    error.add("error", errorDetail);
    return Response.status(statusCode).entity(gson.toJson(error)).type(MediaType.APPLICATION_JSON).build();
  }
}
