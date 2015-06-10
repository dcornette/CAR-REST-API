package com.example.config;

public class Constants {
    public static final String HEADER =
            "<head>" +
            "<meta charset=\"ISO-8859-1\">" +
            "</head>";

    public static final String MAIN_MENU =
            Constants.HEADER +
            "<h3>Log in with an account</h3>\n" +
            "<form method=\"POST\" action=\"http://127.0.0.1:8080/rest/api/ftp/connect\">\n" +
            "Username: <input type=\"text\" name=\"user\"><br />\n" +
            "Password: <input type=\"password\" name=\"pass\"><br />\n" +
            "<input name=\"submit\" type=\"submit\" value=\"submit\"/><br />\n" +
            "</form>\n" +
            "<h3><a href=\"http://127.0.0.1:8080/rest/api/ftp/connect\">Log in anonymously</a></h3>\n" +
            "<h3><a href=\"http://127.0.0.1:8080/rest/api/ftp/disconnect\">Disconnect</a></h3>\n" +
            "<h3><a href=\"http://127.0.0.1:8080/rest/api/ftp/list\">List files</a></h3>"+
            "<h3>Upload a file</h3>\n" +
            "<form>\n" +
            "Output directory : <input type=\"text\" id=\"dir\" name=\"dir\"" +
            	"placeholder=\"/home/" + System.getProperty("user.name") + "/Bureau\"" + 
            	"value=\"/home/" + System.getProperty("user.name") + "\" /><br />" +
            "File : <input type=\"file\" name=\"file\" id=\"file\"><br />\n" +
            "<input type=\"submit\" id=\"go\" />" +
            Constants.JS_UPLOAD +
            "</form>\n";

    public static final String COME_BACK = "<a href=\"http://127.0.0.1:8080/rest/api/ftp/\">back</a>";

    public static final String JS_UPLOAD =
            "<script>\n" +
                    "var fileInput = document.querySelector('#file');\n" +
                    "var dir = document.querySelector('#dir');\n" +
                    "var button = document.querySelector('#go');\n" +
                    "var reader = new FileReader();\n" +
                    
                    "reader.addEventListener('error', function() { \n" +
                    	"alert(\"error\");\n" +
                    "}, false);\n" +
                    	
					"fileInput.addEventListener('change', function() { \n" +
                    	"reader.readAsText(fileInput.files[0]);\n" +
                    "}, false);\n" +
                    	
                    "button.addEventListener('click', function() { \n" +
	                    "var form = new FormData();\n" +
	                    "var xhr = new XMLHttpRequest();\n" +  
	                    
	                    "form.append('filename', fileInput.files[0].name);\n" +
	                    "form.append('contents', reader.result);\n" +
	                    "form.append('dir', dir.value);\n" +
	                    
	                    "xhr.open(\"PUT\", \"http://127.0.0.1:8080/rest/api/ftp/put\");\n" +
	                    "xhr.send(form);\n" +
	                    "alert(\"File stored \");\n" +
	                "}, false);\n" +
            "</script>";
}