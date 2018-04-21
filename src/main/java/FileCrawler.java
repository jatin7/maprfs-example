import org.apache.hadoop.fs.*;
import org.apache.hadoop.conf.*;
import org.apache.hadoop.io.*;
import org.apache.hadoop.mapred.*;
import org.apache.hadoop.util.*;

import java.io.*;     // for File
import java.net.URI;
import java.util.*;   // for Scanner

public class FileCrawler {
    public static void main(String[] args) throws Exception{
        Scanner console = new Scanner(System.in);
        System.out.print("Directory to crawl? ");
        String directoryName = console.nextLine();

        String uri = "maprfs:///";
        //String dirname = args[ac++];

        Configuration conf = new Configuration();

        FileSystem fs = FileSystem.get(URI.create(uri), conf);


        Path path = new Path( directoryName );

        FileStatus[] fileStatus = fs.listStatus(path);

        for(FileStatus status : fileStatus){
            System.out.println(status.getPath().toString());
        }

        getAllFilePath(path,fs, true);

    }

    /**
     * @param filePath
     * @param fs
     * @return list of absolute file path present in given path
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static List<String> getAllFilePath(Path filePath, FileSystem fs,boolean recursive) throws FileNotFoundException, IOException {
        List<String> fileList = new ArrayList<String>();
        FileStatus[] fileStatus = fs.listStatus(filePath);
        for (FileStatus fileStat : fileStatus) {
            System.out.println("Permission: " + fileStat.toString());
            if (fileStat.isDirectory() ) {
                System.out.println("============================================");
                fileList.addAll(getAllFilePath(fileStat.getPath(), fs, true));
            } else {
                fileList.add(fileStat.getPath().toString());
                System.out.println("File:" + fileStat.getPath().toString());

            }
        }
        return fileList;
    }
}