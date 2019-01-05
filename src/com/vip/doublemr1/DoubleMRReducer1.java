package com.vip.doublemr1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

public class DoubleMRReducer1 extends Reducer<Text, IntWritable, IntWritable, Text>{
	@Override
	protected void reduce(Text name, Iterable<IntWritable> deltas,
			Reducer<Text, IntWritable, IntWritable, Text>.Context context) throws IOException, InterruptedException {
		int total = 0;
		for(IntWritable delta : deltas) {
			total += delta.get();
		}
		context.write(new IntWritable(total), new Text(name));
	}
}
