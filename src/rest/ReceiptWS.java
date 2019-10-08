package rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import dom.Receipt;
import dom.ReceiptReader;

@ApplicationPath("/")
@Produces(MediaType.APPLICATION_JSON) 
@Consumes(MediaType.APPLICATION_JSON)
public class ReceiptWS extends Application {
	
	@GET  
    public Response getReceipt(@QueryParam("rawReceipt") String rawReceipt) {
		Receipt receipt = ReceiptReader.read(rawReceipt);
		if(receipt != null) {
			return Response.ok(receipt.buildReceipt()).build();
		}
        return Response.status(Status.BAD_REQUEST).build();   
	}
}
