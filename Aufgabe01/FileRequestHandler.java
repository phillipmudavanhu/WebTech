import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.io.File;
import java.time.LocalDateTime;

/**
 * Request handler for HTTP/1.1 GET requests.
 */
public class FileRequestHandler {

    private final Path documentRoot;
    private static final String NEW_LINE = System.lineSeparator();

    public FileRequestHandler(Path documentRoot) {
        this.documentRoot = documentRoot;
    }

    /**
     * Called to handle an HTTP/1.1 GET request: first, the status code of the
     * request is determined and a corresponding response header is sent.
     * If the status code is <200>, the requested document root path is sent
     * back to the client. In case the path points to a file, the file is sent,
     * and in case the path points to a directory, a listing of the contained
     * files is sent.
     *
     * @param request Client request
     * @param response Server response
     */
    public void handle(String request, OutputStream response)
    throws IOException {
        response.write("Method handle not implemented.".getBytes());
        response.write(NEW_LINE.getBytes());

        /*
         * (a) Determine status code of the request and write proper status
         * line to the response output stream.
         *
         * Only continue if the request can be processed (status code 200).
         * In case the path points to a file (b) or a directory (c) write the
         * appropriate header fields and …
         *
         * (b) … the content of the file …
         * (c) … a listing of the directory contents …
         *
         * … to the response output stream.
         */
         Path path = Paths.get(request.split(" ")[1]);

         if (!Files.exists(path)) {
             //404
             response.write("HTTP/1.1 404 Not Found".getBytes());
         } 
         else if (false){
             //400
         }
         else if (!request.startsWith("GET ")){
             //501 - function other than GET
             response.write("HTTP/1.1 501 Not Implemented".getBytes());
         }
         else if (!request.endsWith("1.1")){
             //505 - HTTP version other than 1.1
             response.write("HTTP/1.1 505 HTTP Version Not Supported".getBytes());
         }
         else {
             //200 - accept
             response.write("HTTP/1.1 200 OK".getBytes());
             

             if (Files.isDirectory(path)) {
                 
                 //list directory contents
                 //response.write(folder.listFiles().getBytes());

                 /*
                 for (final File fileEntry : folder.listFiles()) {
                    if (fileEntry.isDirectory()) {
                        listFilesForFolder(fileEntry);
                    } else {
                    System.out.println(fileEntry.getName());
                    }
                }
                */
                 //response.write(Files.(path).getBytes());
                
             }
             else {
                //private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");

                //what a DATE header should look like: Date: Tue, 15 Nov 1994 08:12:31 GMT

                /*LocalDateTime now = LocalDateTime.now();
                System.out.println(dtf.format(now));
                */
                 response.write(Files.readAllBytes(path));
                
             }
        }
    }
}
