package ch05;// == ch05.BytesWritableTest
// == ch05.BytesWritableTest-Capacity
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.apache.hadoop.io.BytesWritable;
import org.apache.hadoop.util.StringUtils;
import org.junit.Test;

public class BytesWritableTest extends WritableTestBase {
  
  @Test
  public void test() throws IOException {
    // vv ch05.BytesWritableTest
    BytesWritable b = new BytesWritable(new byte[] { 3, 5 });
    byte[] bytes = serialize(b);
    assertThat(StringUtils.byteToHexString(bytes), is("000000020305"));
    // ^^ ch05.BytesWritableTest
    
    // vv ch05.BytesWritableTest-Capacity
    b.setCapacity(11);
    assertThat(b.getLength(), is(2));
    assertThat(b.getBytes().length, is(11));
    // ^^ ch05.BytesWritableTest-Capacity
  }
}
