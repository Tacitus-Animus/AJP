package fp.executeAroundMethod;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestEAM {

	public static void main(String[] args) throws IOException {

		FileWriterEAM.use("eam.txt", writerEAM -> writerEAM.writeStuff("Hi"));
		
		System.out.println(Files.readAllLines(Paths.get("eam.txt")));
		
	}

}
