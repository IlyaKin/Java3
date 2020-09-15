public class UploadRequest implements Request {

    private String userName;
    private String fileName;
    private long fileSize;


    private String getUserName(){
        return userName;
    }

    private String getFileName(){
        return fileName;
    }

    private long getFileSize(){
        return fileSize;
    }

    public UploadRequest(String userName, String fileName, long fileSize){
        this.userName = userName;
        this.fileName = fileName;
        this.fileSize = fileSize;
    }
}