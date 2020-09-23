public class DownloadRequest implements Request {
    private String userName;
    private String fileName;


    public String getUserName(){
        return userName;
    }

    public String getFileName(){
        return fileName;
    }

    public void setUserName(String userName) {this.userName = userName;}

    public void setFileName(String fileName)  {this.fileName = fileName;}

    public DownloadRequest (String fileName) {
        //this.userName = userName;
        this.fileName = fileName;
    }
}
