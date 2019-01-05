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
		
		// 1.����Job������������
		job.setJarByClass(DoubleMRDriver1.class);
		// ����Mapper������е���
		job.setMapperClass(DoubleMRMapper1.class);
		
		// 2.����Mapper������key������
		job.setMapOutputKeyClass(Text.class);
		// ����Mapper������value����
		job.setMapOutputValueClass(IntWritable.class);
	
		
		// 3.����job�������ļ����ڵ�HDFS·��
		FileInputFormat.setInputPaths(job, new Path("hdfs://192.168.154.129:9000/doublemr/result01"));
		// ���ý���ļ�·����Ҳ�����Ǵ洢HDFS��
		FileOutputFormat.setOutputPath(job, new Path("hdfs://192.168.154.129:9000/doublemr/result"));
		
		// 4.�ύ����
		job.waitForCompletion(true);

	}

}
