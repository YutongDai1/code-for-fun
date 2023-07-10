package constants;

public enum Regex {
  /**
   * SEND_MESSAGE_REGEX
   */
  SEND_MESSAGE_REGEX("(\\@)(.+)(\s)"),
  SEND_INSULT_REGEX("(\\!)(.+)");
  private final String regex;
  Regex(String regex) {
    this.regex = regex;
  }
  public String getRegex()
  {
    return regex;
  }
}
