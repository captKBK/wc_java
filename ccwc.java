import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class ccwc {
    public static void main(String[] args) {
        String option, file;
            if (args[0].charAt(0) == '-') {
                option = args[0];
                if (args.length > 1) {
                    file = args[1];
                } else {
                    file = "";
                }
            } else {
                option = "-clw";
                if (args.length > 0) {
                    file = args[0];
                } else {
                    file = "";
                }
            }

        try {
            ccwc cc = new ccwc();
            /*  
             * you cannot directly access the ccwc class which is the starting point of due
             * to main method since it is not static, so you need to create an instance of it to access its variables and method 
            */
            System.out.println(cc.getWCOutput(option, file) + " " + file);

        } catch (Exception ex){
            System.err.println("Exception: " + ex.getMessage());

        }
    }

    private String getWCOutput (String option, String file) {
        int options = option.length() - 1;
        String output = " ";
        int i = 1;
        while (i <= options) {
            switch (option.charAt(i)){
            case 'c': {
                output += (getNumOfBytes(file) + " ");
                break;
            }
            case 'l': {
                output += (getNumOfLines(file) + " ");
                break;
            }
            case 'w': {
                output += (getNumOfWords(file) + " ");
                break;
            }
            case 'm': {
                output += (getNumOfChars(file) + "");
                break;
            }
            default: {
                output = "Invalid option: " + option.charAt(i) + " in option " + option;
                return output;
            }
        }
        i++;
    }
    return output;
    }

    private String getNumOfBytes(String file) {
        try {
            File f = new File(file);
            FileInputStream fin;
            fin = new FileInputStream(file);
            int bytes = 0;
            while (fin.read() != -1){
                bytes++;
            }
            fin.close();
            return String.valueOf(bytes);
        } catch (Exception ex) {
            ex.getStackTrace();
            return ex.getMessage();
        }
    }

    private String getNumOfLines(String filePath) {
        try {
            Path p = Paths.get(filePath).normalize();
            File f = p.toFile(); // or File f = new File(filePath);            
            FileReader r = new FileReader(f);
            BufferedReader buf = new BufferedReader(r);
            int lines = 0;
            while (buf.readLine() != null) {
                lines++;
            }
            buf.close();
            r.close();
            return String.valueOf(lines);
        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    private String getNumOfWords(String filePath) {
        try {
            Path p = Paths.get(filePath).normalize();
            Scanner sc = new Scanner(p);
            int wordCount = 0;
            while (sc.hasNext() == true) {
                sc.next();
                wordCount++;
            }
            sc.close();
            return String.valueOf(wordCount);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }

    private String getNumOfChars(String filePath) {
        try {
            Path p = Paths.get(filePath).normalize();
            File f = p.toFile();
            FileReader fr = new FileReader(f);
            long numOfChars = 0;
            while (fr.read() != -1) {
                numOfChars++;
            }
            fr.close();
            return String.valueOf(numOfChars);

        } catch (Exception ex) {
            ex.printStackTrace();
            return ex.getMessage();
        }
    }
}