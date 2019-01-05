package com.vip.doublemr1;

import java.io.IOException;

import org.apache.curator.framework.recipes.locks.InterProcessReadWriteLock;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

public class DoubleMRDriver1 {

	public static void main(String[] args) throws Exception {
		Configuration configuration = new Configuration();
		Job job = Job.getInstance(configuration);
		
		// 1.����Job������������
		job.setJarByClass(DoubleMRDriver1.class);
		// ����Mapper������е���
		job.setMapperClass(DoubleMRMapper1.class);
		// ����Reducer���������
		job.setReducerClass(DoubleMRReducer1.class);
		
		// 2.����Mapper������key������
		job.setMapOutputKeyClass(Text.class);
		// ����Mapper������value����
		job.setMapOutputValueClass(IntWritable.class);
		// ����Reducer������key������
		job.setOutputKeyClass(IntWritable.class);
		// ����Reducer������value����
		job.setOutputValueClass(Text.class);
		
		// ����Reducer�����Լ��Զ����������
//		job.setNumReduceTasks(3);
//		job.setPartitionerClass(FlowPartition.class);
		
		// 3.����job�������ļ����ڵ�HDFS·��
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.154.129:9000/doublemr/doubleMR.txt"));
		// ���ý���ļ�·����Ҳ�����Ǵ洢HDFS��
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.154.129:9000/doublemr/result01"));
		
		// 4.�ύ����
		job.waitForCompletion(true);
	}

}
