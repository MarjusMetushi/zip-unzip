import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

public class Zipunzip {
    public void zip(File path) throws IOException {
        File[] file = path.listFiles();
        if (file.length == 0) {
            System.out.println("There is no files in this folder!");
        }
        FileOutputStream output = new FileOutputStream("C:\\Users\\mariu\\Desktop\\coding\\myzip.zip");
        ZipOutputStream zipoutput = new ZipOutputStream(output);

        for (File zipit : file) {
            FileInputStream input = new FileInputStream(zipit);
            ZipEntry zipEntry = new ZipEntry(zipit.getName());
            zipoutput.putNextEntry(zipEntry);
            byte[] bytes = new byte[2048];
            int length;
            while ((length = input.read(bytes))>=0) {
                zipoutput.write(bytes, 0, length);
            }
            input.close();
        }
        zipoutput.close();
    } 
    public void unzip(File zippedFile) throws IOException {
        File xy = new File("extractto");
        byte[] buffer = new byte[2048];
        try (ZipInputStream zipinput = new ZipInputStream(new FileInputStream(zippedFile))) {
            ZipEntry zipEntry = zipinput.getNextEntry();
            while (zipEntry!=null) {
                if (zipEntry.isDirectory()) {
                    continue;
                }else{
                    File newFile = new File("extractto/" + zipEntry.getName());
                    FileOutputStream fileoutput = new FileOutputStream(newFile);
                    int length;
                    while ((length = zipinput.read(buffer))> 0) {
                        fileoutput.write(buffer,0,length);
                    }
                    fileoutput.close();
                }
                zipEntry=zipinput.getNextEntry();
            }
        }
    }
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Welcome to Zip-Unzip program.");
        System.out.println("Choose 1 to zip or choose 2 to unzip");
        System.out.println("Please keep in mind this is program is just a test sample!");
        int choose = scanner.nextInt();
        if (choose == 1) {
            new Zipunzip().zip(new File("filestozip"));
        System.out.println("Zip operation is successful!");
        }
        else if (choose == 2) {
        new Zipunzip().unzip(new File("myzip.zip"));
        System.out.println("Unzip operation is successful!");
        }
        scanner.close();
    }
}