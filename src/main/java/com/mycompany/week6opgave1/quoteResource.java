/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.week6opgave1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import com.google.gson.JsonObject;
import exception.NoQuotesExist;
import exception.QuoteNotFoundException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * REST Web Service
 *
 * @author Bruger
 */
@Path("quote")
public class quoteResource {

    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of GenericResource
     */
    public quoteResource() {
    }

    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {

//    @GET
//    @Produces(MediaType.TEXT_PLAIN)
//    public String getText() {
        return "Hello";
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getQoute(@PathParam("id") int id) throws QuoteNotFoundException {
        //return "Hello " + name + " From REST";
        String quote;
        
            quote = quotes.get(id);
        

 if (quote==null)   throw new QuoteNotFoundException();

        return quote;
    }

    @GET
    @Path("/random")
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandomQuote() throws NoQuotesExist {
        //return "Hello " + name + " From REST";
        if (quotes.isEmpty()) throw new NoQuotesExist();
        Random r = new Random();
        int random = r.nextInt(quotes.size()+1);
        String quote = quotes.get(random);

        return quote;
    }

    /**
     * PUT method for updating or creating an instance of GenericResource
     *
     * @param jsonQuote
     * @param content representation for the resource
     * @return 
     */

    @POST
    @Consumes({MediaType.APPLICATION_JSON})
    @Produces({MediaType.APPLICATION_JSON})
    public Response addQuote(String jsonQuote) {
        System.out.println("jsonQuote"+jsonQuote);
        JsonParser parser = new JsonParser();
        com.google.gson.JsonObject job = parser.parse(jsonQuote).getAsJsonObject();
        String quoteValue = job.get("quote").getAsString();
        System.out.println("quote: "+quoteValue);
        JsonObject result = new JsonObject();
        result.addProperty("id", quotes.size()+1);
        result.addProperty("quote", quoteValue);
        

        quotes.put(quotes.size()+1, quoteValue);
        return Response
                .status(200)
                .entity(gson.toJson(result))
                .build();
    }
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public void putQuote(String quote) {
    }

    @DELETE
    @Path("{id}")
    public void deleteQuote(@PathParam("id") String quote) {
    }
    
    
    private static Map<Integer, String> quotes = new HashMap() {
        {
            put(1, "Friends are kisses blown to us by angels");
            put(2, "Do not take life too seriously. You will never get out of it alive");
            put(3, "Behind every great man, is a woman rolling her eyes");
        }
    };
}
