package ch05;// == ch05.IntWritableTest
// == ch05.IntWritableTest-ValueConstructor
// == ch05.IntWritableTest-SerializedLength
// == ch05.IntWritableTest-SerializedBytes
// == ch05.IntWritableTest-Deserialization
// == ch05.IntWritableTest-Comparator
// == ch05.IntWritableTest-ObjectComparison
// == ch05.IntWritableTest-BytesComparison
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.apache.hadoop.io.*;
import org.apache.hadoop.util.StringUtils;
import org.junit.Test;

public class IntWritableTest extends WritableTestBase {
  
  @Test
  public void walkthroughWithNoArgsConstructor() throws IOException {
    // vv ch05.IntWritableTest
    IntWritable writable = new IntWritable();
    writable.set(163);
    // ^^ ch05.IntWritableTest
    checkWalkthrough(writable);
  }

  @Test
  public void walkthroughWithValueConstructor() throws IOException {
    // vv ch05.IntWritableTest-ValueConstructor
    IntWritable writable = new IntWritable(163);
    // ^^ ch05.IntWritableTest-ValueConstructor
    checkWalkthrough(writable);
  }

  private void checkWalkthrough(IntWritable writable) throws IOException {
    // vv ch05.IntWritableTest-SerializedLength
    byte[] bytes = serialize(writable);
    assertThat(bytes.length, is(4));
    // ^^ ch05.IntWritableTest-SerializedLength
    
    // vv ch05.IntWritableTest-SerializedBytes
    assertThat(StringUtils.byteToHexString(bytes), is("000000a3"));
    // ^^ ch05.IntWritableTest-SerializedBytes
    
    // vv ch05.IntWritableTest-Deserialization
    IntWritable newWritable = new IntWritable();
    deserialize(newWritable, bytes);
    assertThat(newWritable.get(), is(163));
    // ^^ ch05.IntWritableTest-Deserialization
  }
  
  @Test
  @SuppressWarnings("unchecked")
  public void comparator() throws IOException {
    // vv ch05.IntWritableTest-Comparator
    RawComparator<IntWritable> comparator =
        WritableComparator.get(IntWritable.class);
    // ^^ ch05.IntWritableTest-Comparator
    
    // vv ch05.IntWritableTest-ObjectComparison
    IntWritable w1 = new IntWritable(163);
    IntWritable w2 = new IntWritable(67);
    assertThat(comparator.compare(w1, w2), greaterThan(0));
    // ^^ ch05.IntWritableTest-ObjectComparison
    
    // vv ch05.IntWritableTest-BytesComparison
    byte[] b1 = serialize(w1);
    byte[] b2 = serialize(w2);
    assertThat(comparator.compare(b1, 0, b1.length, b2, 0, b2.length),
        greaterThan(0));
    // ^^ ch05.IntWritableTest-BytesComparison
  }
  
  @Test
  public void test() throws IOException {
    IntWritable src = new IntWritable(163);
    IntWritable dest = new IntWritable();
    assertThat(writeTo(src, dest), is("000000a3"));
    assertThat(dest.get(), is(src.get()));
  }

}
