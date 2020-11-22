package ch05;// == ch05.TextArrayWritable
import org.apache.hadoop.io.ArrayWritable;
import org.apache.hadoop.io.Text;

// vv ch05.TextArrayWritable
public class TextArrayWritable extends ArrayWritable {
  public TextArrayWritable() {
    super(Text.class);
  }
}
// ^^ ch05.TextArrayWritable
