import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

/**
 * テキストファイルへの書き込み
 */
public class TextfileWrite {
    public static final boolean createText(String path, String messages) {
        boolean bRet = true;
        String outputFilePath = path;
        File outputFile = new File(outputFilePath);

        try {
            FileOutputStream fos = new FileOutputStream(outputFile);
            OutputStreamWriter osw = new OutputStreamWriter(fos, CommonUtill.UTF8);
            PrintWriter pw = new PrintWriter(osw);

            pw.println(messages);
            pw.close();
        } catch(Exception e) {
            bRet = false;
        }
        return bRet;
    }
}
