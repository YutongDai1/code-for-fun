import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;


/**
 * UserInterface Class
 */
public class UserInterface {
    private final List<String> fileList;
    private JsonFileReader jsonFileReader;
    private JsonGrammar jsonGrammar;
    private SentenceGenerator sentenceGenerator;

    /**
     * load the file list
     */
    private final static String LOADING = "Loading grammars ...\n";
    /**
     * available grammars
     */
    private final static String AVAILABLE = "The following grammars are available: \n";
    /**
     * ask user to choose a grammar
     */
    private final static String WHICH = "\nWhich would you like to use? (q to quit)";
    /**
     * quit the program
     */
    private final static String GOODBYE = "Goodbye my friend!";
    /**
     * quit command
     */
    private final static String QUIT = "q";
    /**
     * ask user if you want to get another sentence
     */
    private final static String ANOTHER = "Would you like another? (y/n)";
    /**
     * directory of the grammar files
     */
    private final static String TEMPLATE_DIRECTORY = "grammar/";
    /**
     * wrong input
     */
    private final static String WRONG_INPUT = " is wrong Input! Please try again.";
    /**
     * YES command
     */
    private final static String YES = "Y";
    /**
     * NO command
     */
    private final static String NO = "N";
    /**
     * start label of the grammar
     */
    private final static String START_GRAMMAR_LABEL = "start";


    /**
     * Default constructor for UserInterface class
     */
    public UserInterface() {
        this.fileList = new ArrayList<>();
        this.jsonFileReader = new JsonFileReader();
        this.jsonGrammar = new JsonGrammar();
        this.sentenceGenerator = new SentenceGenerator(jsonGrammar);
    }

    /**
     * setter method for fileDirectory
     * set all JSON files in the grammar directory to fileDirectory
     */
    public void setFileList() {
        File folder = new File(TEMPLATE_DIRECTORY);
        File[] listOfFiles = folder.listFiles();
        for (File file : listOfFiles) {
            fileList.add(file.getName());
        }
    }

    /**
     * getter method for fileDirectory
     *
     * @return fileDirectory
     */
    public List<String> getFileList() {
        return this.fileList;
    }

    /**
     * user interface main method
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        setFileList();
        System.out.println(LOADING);
        String line;
        while (true) {
            System.out.println(AVAILABLE);
            for (int i = 0; i < fileList.size(); i++) {
                System.out.println(i + 1 + ". " + fileList.get(i));
            }
            System.out.println(WHICH);
            line = scanner.nextLine();
            try {
                if (line.equalsIgnoreCase(QUIT)) {
                    System.out.println(GOODBYE);
                    break;
                } else if (Integer.parseInt(line) > 0 && Integer.parseInt(line) <= fileList.size()) {
                    for (int i = 0; i < fileList.size(); i++) {
                        if (line.equals(Integer.toString(i + 1))) {

                            jsonFileReader.setFileName(TEMPLATE_DIRECTORY + fileList.get(i));
                            jsonGrammar = jsonFileReader.readJsonFile();
                            sentenceGenerator.setJsonGrammar(jsonGrammar);
                            System.out.println(sentenceGenerator.generateRandomSentence(START_GRAMMAR_LABEL));

                            do {
                                System.out.println(ANOTHER);
                                line = scanner.nextLine();
                                if (line.equalsIgnoreCase(YES)) {
                                    System.out.println(sentenceGenerator.generateRandomSentence(START_GRAMMAR_LABEL));
                                } else if (line.equalsIgnoreCase(NO)) {
                                    break;
                                } else {
                                    System.err.println("'" + line + "'" + WRONG_INPUT);
                                }
                            } while (true);
                            break;
                        }
                    }
                } else {
                    throw new IllegalArgumentException(InvalidArgsException.INPUT_NOT_DEFINED);
                }
            } catch (IllegalArgumentException e) {
                System.err.println(e.getMessage());
            }
        }
    }

    /**
     * setter method for jsonFileReader
     *
     * @param jsonFileReader jsonFileReader
     */
    public void setJsonFileReader(JsonFileReader jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
    }

    /**
     * setter method for jsonGrammar
     *
     * @param jsonGrammar jsonGrammar
     */
    public void setJsonGrammar(JsonGrammar jsonGrammar) {
        this.jsonGrammar = jsonGrammar;
    }

    /**
     * setter method for sentenceGenerator
     *
     * @param sentenceGenerator sentenceGenerator
     */
    public void setSentenceGenerator(SentenceGenerator sentenceGenerator) {
        this.sentenceGenerator = sentenceGenerator;
    }

    /**
     * getter method for jsonFileReader
     *
     * @return jsonFileReader
     */
    public JsonFileReader getJsonFileReader() {
        return this.jsonFileReader;
    }

    /**
     * getter method for jsonGrammar
     *
     * @return jsonGrammar
     */
    public JsonGrammar getJsonGrammar() {
        return this.jsonGrammar;
    }

    /**
     * getter method for sentenceGenerator
     *
     * @return sentenceGenerator
     */
    public SentenceGenerator getSentenceGenerator() {
        return this.sentenceGenerator;
    }

    /**
     * override toString method
     *
     * @return String
     */
    @Override
    public String toString() {
        return "UserInterface{" +
                "fileList=" + fileList +
                ", jsonFileReader=" + jsonFileReader +
                ", jsonGrammar=" + jsonGrammar +
                ", sentenceGenerator=" + sentenceGenerator +
                '}';
    }

    /**
     * override equals method
     *
     * @param o object
     * @return boolean
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserInterface that = (UserInterface) o;
        return Objects.equals(fileList, that.fileList);
    }

    /**
     * override hashCode method
     *
     * @return int
     */
    @Override
    public int hashCode() {
        return Objects.hash(fileList, jsonFileReader, jsonGrammar, sentenceGenerator);
    }
}
