package ch09;// cc ch09.JoinStationMapper Mapper for tagging station records for a reduce-side join
import java.io.IOException;

import ch05.TextPair;
import common.NcdcStationMetadataParser;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapreduce.Mapper;

// vv ch09.JoinStationMapper
public class JoinStationMapper
    extends Mapper<LongWritable, Text, TextPair, Text> {
  private NcdcStationMetadataParser parser = new NcdcStationMetadataParser();

  @Override
  protected void map(LongWritable key, Text value, Context context)
      throws IOException, InterruptedException {
    if (parser.parse(value)) {
      context.write(new TextPair(parser.getStationId(), "0"),
          new Text(parser.getStationName()));
    }
  }
}
// ^^ ch09.JoinStationMapper