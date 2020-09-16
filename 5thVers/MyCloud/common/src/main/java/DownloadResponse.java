public class DownloadResponse implements Response {

    private String fileName;
    private long fileSize;

    private String getFileName(){
        return fileName;
    }
    private long getFileSize(){
        return fileSize;
    }
}
