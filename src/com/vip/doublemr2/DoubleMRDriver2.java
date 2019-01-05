package com.vip.doublemr2;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import com.vip.doublemr1.DoubleMRDriver1;
import com.vip.doublemr1.DoubleMRMapper1;
import com.vip.doublemr1.DoubleMRReducer1;

public class DoubleMRDriver2 {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		
		// 1.设置Job工作的运行类
		job.setJarByClass(DoubleMRDriver1.class);
		// 设置Mapper组件运行的类
		job.setMapperClass(DoubleMRMapper1.class);
		
		// 2.设置Mapper组件输出key的类型
		job.setMapOutputKeyClass(Text.class);
		// 设置Mapper组件输出value类型
		job.setMapOutputValueClass(IntWritable.class);
	
		
		// 3.设置job待处理文件所在的HDFS路径
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.154.129:9000/doublemr/result01"));
		// 设置结果文件路径，也必须是存储HDFS上
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.154.129:9000/doublemr/result"));
		
		// 4.提交任务
		job.waitForCompletion(true);

	}

}
