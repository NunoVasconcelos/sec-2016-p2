package client;

import file_system.fs_library.FS_Library;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.PublicKey;
import java.util.List;

public class Client
{
	public static void main(String [] args) throws Exception {
        FS_Library lib = new FS_Library();

		// init file system: get publicKey from cc
        lib.fs_init();

		// get publicKeys from all users
		List<PublicKey> publicKeys = lib.fs_list();

		// read local file
		byte[] content = "-1".getBytes();
		Path path = Paths.get(args[0]);
		try {
			content = Files.readAllBytes(path);
		}
		catch(Exception e) {
			e.printStackTrace();
		}

		// write content from local file into block server
		int pos = 1;
		int size = content.length;
		System.out.println("writing content from file: " + path.toString() + ", pos: " + pos + ", size: " + size);
		lib.fs_write(pos, content);

		// read content from stored file on block server
		pos = 1;
		size = 61632048;
		System.out.println("reading content from my file: pos: " + pos + ", size: " + size);
		byte[] bytesReturned = lib.fs_read(publicKeys.get(0), pos, size);
		File output = new File("output.txt");
		try{
			FileOutputStream outputStream = new FileOutputStream(output);
			outputStream.write(bytesReturned);
			outputStream.flush();
			outputStream.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
