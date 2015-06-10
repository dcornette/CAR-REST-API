# HOW TO USE LINK

 - Main Menu
    GET     http://127.0.0.1:8080/rest/api/ftp/
 - Connect with user "test" and pass "test"
    POST    http://127.0.0.1:8080/rest/api/ftp/connect
 - Connect in anonymous
    GET     http://127.0.0.1:8080/rest/api/ftp/connect
 - Disconnect
    GET     http://127.0.0.1:8080/rest/api/ftp/disconnect
 - List files
    GET     http://127.0.0.1:8080/rest/api/ftp/list
 - Upload a file
    PUT     http://127.0.0.1:8080/rest/api/ftp/put
 - Download a file
    GET     http://127.0.0.1:8080/rest/api/ftp/download/pathname/filename
 - Remove a file
    GET     http://127.0.0.1:8080/rest/api/ftp/remove/pathname/filename
 - Remove a file
    DELETE  http://127.0.0.1:8080/rest/api/ftp/remove/pathname/filename
 - Create a directory
    GET     http://127.0.0.1:8080/rest/api/ftp/mkdir/pathname/directory