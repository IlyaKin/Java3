import java.util.List;

public class FilesListResponse implements Response {

    private List<String> filesList;

    private List<String> getFilesList() {
        return filesList;
    }

    public FilesListResponse(List<String> filesList){
        this.filesList = filesList;
    }
}
