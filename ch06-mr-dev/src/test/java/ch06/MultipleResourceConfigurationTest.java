package ch06;// == ch06.MultipleResourceConfigurationTest
// == ch06.MultipleResourceConfigurationTest-Override
// == ch06.MultipleResourceConfigurationTest-Final
// == ch06.MultipleResourceConfigurationTest-Expansion
// == ch06.MultipleResourceConfigurationTest-SystemExpansion
// == ch06.MultipleResourceConfigurationTest-NoSystemByDefault
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import org.apache.hadoop.conf.Configuration;
import org.junit.Test;

public class MultipleResourceConfigurationTest {
  
  @Test
  public void get() throws IOException {
    // Single test as an expedient for inclusion in the book
    
    // vv ch06.MultipleResourceConfigurationTest
    Configuration conf = new Configuration();
    conf.addResource("configuration-1.xml");
    conf.addResource("configuration-2.xml");
    // ^^ ch06.MultipleResourceConfigurationTest
    
    assertThat(conf.get("color"), is("yellow"));

    // override
    // vv ch06.MultipleResourceConfigurationTest-Override
    assertThat(conf.getInt("size", 0), is(12));
    // ^^ ch06.MultipleResourceConfigurationTest-Override

    // final properties cannot be overridden
    // vv ch06.MultipleResourceConfigurationTest-Final
    assertThat(conf.get("weight"), is("heavy"));
    // ^^ ch06.MultipleResourceConfigurationTest-Final

    // variable expansion
    // vv ch06.MultipleResourceConfigurationTest-Expansion
    assertThat(conf.get("size-weight"), is("12,heavy"));
    // ^^ ch06.MultipleResourceConfigurationTest-Expansion

    // variable expansion with system properties
    // vv ch06.MultipleResourceConfigurationTest-SystemExpansion
    System.setProperty("size", "14");
    assertThat(conf.get("size-weight"), is("14,heavy"));
    // ^^ ch06.MultipleResourceConfigurationTest-SystemExpansion

    // system properties are not picked up
    // vv ch06.MultipleResourceConfigurationTest-NoSystemByDefault
    System.setProperty("length", "2");
    assertThat(conf.get("length"), is((String) null));
    // ^^ ch06.MultipleResourceConfigurationTest-NoSystemByDefault

  }

}
