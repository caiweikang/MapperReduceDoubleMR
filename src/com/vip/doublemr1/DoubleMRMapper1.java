package com.vip.doublemr1;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DoubleMRMapper1 extends Mapper<LongWritable, Text, Text, IntWritable>{
	@Override
	protected void map(LongWritable key, Text value, Mapper<LongWritable, Text, Text, IntWritable>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split(" ");
		int delta = Integer.parseInt(data[2]) - Integer.parseInt(data[3]);
		context.write(new Text(data[1]), new IntWritable(delta));
	}
}
