package adventofcode;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class FileReader {
	Path path;
	List<String> content;
	final static Charset ENCODING = StandardCharsets.UTF_8;

	FileReader (String filename) {
	    path = Paths.get(filename);
	}
	List<String> readLines() {
		if (content != null) {
			return content;
		}
		try {
			content = Files.readAllLines(path, ENCODING);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
		return content;
	}
}
