package com.example.services;

import com.example.config.Constants;
import com.example.model.ClientBean;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.*;

@Service
public class FtpService {

	private static ClientBean clientBean = new ClientBean();
	private final FTPClient client = new FTPClient();
	
    private final String host = "127.0.0.1";
    private final int port = 3377;
	
    /**
     * Connect user to the ftp server
     * @param user
     * @param pass
     * @return
     */
	public String connect(String user, String pass) {
        try {      
            client.connect(this.host, this.port);
            if (client.login(user, pass)){
                clientBean.setUsername(user);
                clientBean.setPassword(pass);
                client.disconnect();
                return "<html><h1>You're now login in "+user+"</h1>"+ Constants.COME_BACK +"</html>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<html><h1>Fail to login</h1>"+ Constants.COME_BACK +"</html>";		
	}
	
	/**
	 * Disconnect user to the ftp server
	 * @return
	 */
	public String disconnect() {
        clientBean.setUsername("");
        clientBean.setPassword("");
        return "<html><h4>You're now disconnected</h4>"+ Constants.COME_BACK +"</html>";
	}
	
	/**
	 * List files and directories of the ftp server
	 * @param dir
	 * @return
	 */
	public String list(String dir) {
        try {
            client.connect(this.host, this.port);
            String temp;
            if (client.login(clientBean.getUsername(), clientBean.getPassword())){
            	if(!dir.isEmpty()) {
            		client.cwd(dir);
            		dir += "/";
            	}
                FTPFile[] listeFichiers = client.listFiles();
                temp = "<h3>"+ client.printWorkingDirectory()+"</h3><br/><ul>";
                for (FTPFile file : listeFichiers){
                    if (file.isFile()){
                        temp += "<li><a href=\"http://127.0.0.1:8080/rest/api/ftp/download/"+dir+file.getName()+"\">"+ file.getName() +"</a> -- ";
                        temp += "<a href=\"http://127.0.0.1:8080/rest/api/ftp/remove/"+dir+file.getName()+"\">Remove </a></li>";
                    } else {
                        temp += "<li><a href=\"http://127.0.0.1:8080/rest/api/ftp/list/"+dir+file.getName()+"\">"+ file.getName() +"</a></li>";
                    }
                }
                temp += "</ul>";
                client.disconnect();
                return temp+ Constants.COME_BACK ;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<html><h1>Impossible to list</h1>"+ Constants.COME_BACK +"</html>";
	}
	
	/**
	 * Retrieve files from the ftp server
	 * @param dir
	 * @param file
	 * @return
	 */
	public Response download(String dir, String file) {
        Response.ResponseBuilder rep;
        try {
            client.connect(this.host, this.port);
            if (client.login(clientBean.getUsername(), clientBean.getPassword())){
                client.cwd(dir);
                File liste[] = new File("/"+dir).listFiles();
                for (File fichier : liste){
                    if (fichier.getName().equals(file)){
                        if (fichier.isDirectory()){
                            rep = Response.noContent();
                            return rep.build();
                        }
                    }
                }
                InputStream input = client.retrieveFileStream(file);
                rep = Response.ok(input, MediaType.APPLICATION_OCTET_STREAM);
                client.disconnect();
                return rep.build();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        rep = Response.noContent();
        return rep.build();
	}
	
	/**
	 * Store files to the ftp server
	 * @param dir : the output directory
	 * @param filename : the name of the file
     * @param contents : Content of the file
	 * @return
	 */
    public String putFile(String filename, String contents, String dir) {
        try {
        	client.connect(this.host, this.port);
            if ( client.login(clientBean.getUsername(), clientBean.getPassword()) ) {
            	client.changeWorkingDirectory(dir);
                ByteArrayInputStream fileInput = new ByteArrayInputStream(contents.getBytes());
                if (client.storeFile(filename, fileInput)){
                    return "<html><body><h2>The file was sent</h2></body></html>" + Constants.COME_BACK;
                }
            }
        } catch (IOException e) {
        	e.printStackTrace();
        }
        return "<html><h1>Impossible to send the file</h1></html>";
    }
	
	/**
	 * Make directory to the ftp server
	 * @param dir
	 * @param file
	 * @return
	 */
	public String makeDir(String dir, String file) {
        try {
            client.connect(this.host, this.port);
            if (client.login(clientBean.getUsername(), clientBean.getPassword())){
                client.cwd(dir);
                File liste[] = new File("/"+dir).listFiles();
                for (File fichier : liste){
                    if (fichier.getName().equals(file)){
                        if (fichier.isDirectory()){
                            return "<html><body><h2>The directory already exists</h2></body></html>";
                        }
                    }
                }
                client.makeDirectory(file);
                client.disconnect();
                return "<html><body><h2>Directory created</h2></body></html>";
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "<html><body><h2>Impossible to create the directory</h2></body></html>";
	}
	
	/**
	 * Delete files to the ftp server
	 * @param file
	 * @return
	 */
	public String delete(String file) {
        String temp;
        try {
            client.connect(this.host, this.port);
            if (client.login(clientBean.getUsername(), clientBean.getPassword())){
                client.deleteFile(file);
                return "<html><body><h2>The file has been deleted</h2></body></html>"+ Constants.COME_BACK;
            }
            client.disconnect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        temp = "<html><body><h2>You are not connected</h2></body></html>";
        return temp + Constants.COME_BACK;
	}
}