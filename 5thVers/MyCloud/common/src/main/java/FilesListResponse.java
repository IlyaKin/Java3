import java.util.List;

public class FilesListResponse implements Response {
    private List<String> filesList;

    public List<String> getFilesList() {
        return filesList;
    }

    public FilesListResponse(List<String> filesList){
        this.filesList = filesList;
    }
}
