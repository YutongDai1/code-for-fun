package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class RegexTest {

  @Test
  void getRegex() {
    assertEquals("(\\!)(.+)",Regex.SEND_INSULT_REGEX.getRegex());
  }

  @Test
  void values() {
    assertEquals(2,Regex.values().length);
  }

  @Test
  void valueOf() {
    assertEquals(Regex.SEND_INSULT_REGEX,Regex.valueOf("SEND_INSULT_REGEX"));
  }
}