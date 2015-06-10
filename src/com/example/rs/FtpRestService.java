package com.example.rs;

import com.example.config.Constants;
import com.example.services.FtpService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import org.apache.cxf.jaxrs.ext.multipart.Multipart;


@Path( "/ftp" )
public class FtpRestService {

	@Inject private FtpService ftpservice;

    @GET
    @Produces("text/html")
    public String displayForm() {
        return Constants.MAIN_MENU;
    }

    @POST
    @Path("/connect")
    @Produces("text/html")
    public String connectTheUser(@FormParam("user")final String user, @FormParam("pass")final String pass) {
    	return this.ftpservice.connect(user, pass);
    }

    @GET
    @Path("/connect")
    @Produces("text/html")
    public String connectAnonymous() {
    	return this.ftpservice.connect("anonymous", "");
    }

    @GET
    @Path("/disconnect")
    @Produces("text/html")
    public String disconnect() {
    	return this.ftpservice.disconnect();
    }

    @GET
    @Path("/list")
    @Produces("text/html")
    public String getListFile() {
    	return this.ftpservice.list("");
    }

    @GET
    @Path("/list/{var: .*}")
    @Produces("text/html")
    public String getListFile(@PathParam("var") String dir) {
        return this.ftpservice.list(dir);
    }

    @GET
    @Path("/download/{var: .*}/{file}")
    @Produces("application/octet-stream")
    public Response getFile(@PathParam("var") String dir, @PathParam("file") String file) {
    	return this.ftpservice.download(dir, file);
    }

    @PUT
    @Path("/put")
    @Produces("text/html")
    public String putFile(@Multipart("filename")String filename,@Multipart("contents") String contents,@Multipart("dir") String dir) {
    	return this.ftpservice.putFile(filename, contents, dir);
    }
    
    @GET
    @Path("/mkdir/{var: .*}/{file}")
    @Produces("text/html")
    public String createDir(@PathParam("var") String dir, @PathParam("file") String file) {
    	return this.ftpservice.makeDir(dir, file);
    }

    @DELETE
    @Path("/remove/{var: .*}")
    @Produces("text/html")
    public String removeFile(@PathParam("var") String file) {
    	return this.ftpservice.delete(file);
    }

    @GET
    @Path("/remove/{var: .*}")
    @Produces("text/html")
    public String removeFileGetVersion(@PathParam("var") String file) {
    	return this.ftpservice.delete(file);
    }
}