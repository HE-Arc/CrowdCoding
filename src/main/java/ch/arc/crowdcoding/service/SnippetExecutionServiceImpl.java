package ch.arc.crowdcoding.service;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import ch.arc.crowdcoding.model.CodeSnippet;

@Service("snippetExecutor")
public class SnippetExecutionServiceImpl implements SnippetExecutionService {
	@Override
	public String runSnippet(CodeSnippet snippet) {
		
		String codeContent = snippet.getContent();
		String filename = "code.py";
		String output = "";
		
		File inputFile = new File(filename);
		
		
		BufferedWriter out;
		try {
			out = new BufferedWriter(new FileWriter(inputFile));
			out.write(codeContent);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		CodeProcessTask process = new CodeProcessTask();
		process.filename = filename;
		process.command = "python";
		
		Future<String> future = executor.submit(process);
		
		try {
			output = future.get();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		executor.shutdown();
		
		inputFile.delete();
		
		
		return output;
	}
	

	
	private class CodeProcessTask implements Callable<String>
	{	
		private static final int CODE_PROCESS_TIMEOUT = 10;
		
		public String command = "";
		public String filename = "";
		
		@Override
		public String call() throws Exception {
			String output = "";
			
			ProcessBuilder pb = new ProcessBuilder(command , filename);
			Process process;
			try {
				process = pb.start();
				process.waitFor(CODE_PROCESS_TIMEOUT, TimeUnit.SECONDS);
				output = outputBuilder(process);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return output;
		}
		
	}
	
	private static String outputBuilder(Process endedProcess)
	{
		JSONObject executionOutput = new JSONObject();
			
		BufferedReader in = new BufferedReader(new InputStreamReader(endedProcess.getInputStream()));
		String progOutput = in.lines().collect(Collectors.joining("\n"));
		
		executionOutput.put("output", progOutput);
		executionOutput.put("status", endedProcess.exitValue());
		
		return executionOutput.toString();
	}
}