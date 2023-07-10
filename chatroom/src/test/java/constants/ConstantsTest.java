package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ConstantsTest {

  @Test
  void getIndex() {
    assertEquals(10,Constants.MAX_CLIENT_SIZE.getIndex());
  }

  @Test
  void values() {
    assertEquals(2,Constants.values().length);
  }

  @Test
  void valueOf() {
    assertEquals(Constants.MAX_CLIENT_SIZE,Constants.valueOf("MAX_CLIENT_SIZE"));
  }
}