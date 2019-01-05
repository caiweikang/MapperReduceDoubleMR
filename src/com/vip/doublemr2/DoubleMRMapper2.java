package com.vip.doublemr2;

import java.io.IOException;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

public class DoubleMRMapper2 extends Mapper<IntWritable, Text, IntWritable, Text>{
	@Override
	protected void map(IntWritable key, Text value, Mapper<IntWritable, Text, IntWritable, Text>.Context context)
			throws IOException, InterruptedException {
		String line = value.toString();
		String[] data = line.split("	");
		context.write(new IntWritable(Integer.parseInt(data[0])), new Text(data[1]));
	}
}
