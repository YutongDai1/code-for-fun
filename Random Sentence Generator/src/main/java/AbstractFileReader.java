/**
 * File class contains file information
 */
public abstract class AbstractFileReader {
    private String fileName;

    /**
     * constructor for File
     * @param fileName name of the file
     */
    public AbstractFileReader(String fileName) {
        this.fileName = fileName;
    }

    /**
     * getter for fileName
     * @return fileName
     */
    public String getFileName() {
        return this.fileName;
    }

    /**
     * setter for fileName
     * @param fileName name of the file
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
