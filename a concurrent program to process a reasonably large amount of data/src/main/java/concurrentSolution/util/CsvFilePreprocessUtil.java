package concurrentSolution.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static concurrentSolution.Regex.*;

/**
 * csv file preprocess util.
 */
public interface CsvFilePreprocessUtil {

    /**
     * preprocess the csv file line to a list of string without the double quotes and the comma
     *
     * @param csvLine the csv line
     * @return the list
     */
    static List<String> preprocess(String csvLine) {
        Pattern pattern = Pattern.compile(QUOTE_REGEX_EXP.getRegex());
        List<String> singleLineList = new ArrayList<>();
        Matcher matcher = pattern.matcher(csvLine);
        while (matcher.find()) {
            singleLineList.add(matcher.group().replaceAll(QUOTE_REGEX.getRegex(), EMPTY_STRING.getRegex()));
        }
        // System.out.println(singleLineList);
        return singleLineList;
    }
}
