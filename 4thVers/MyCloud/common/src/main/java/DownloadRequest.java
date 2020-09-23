public class DownloadRequest implements Request {
    private String userName;
    private String fileName;


    private String getUserName(){
        return userName;
    }

    private String getFileName(){
        return fileName;
    }

    public DownloadRequest (String userName, String fileName) {
        this.userName = userName;
        this.fileName = fileName;
    }
}
