package constants;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class FrameTest {

  @Test
  void getId() {
    assertEquals(19,Frame.CONNECT_MESSAGE.getId());
  }

  @Test
  void values() {
    assertEquals(12,Frame.values().length);
  }

  @Test
  void valueOf() {
    assertEquals(Frame.CONNECT_MESSAGE,Frame.valueOf("CONNECT_MESSAGE"));
  }
}