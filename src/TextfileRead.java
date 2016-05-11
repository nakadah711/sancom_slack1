import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

/**
 * テキストファイルの読み込み
 */
public class TextfileRead {
    public static final String getFileContens(String path) {
        String message = "";

        // 読み込むファイルの名前
        String inputFileName = path;
        // ファイルオブジェクトの生成
        File inputFile = new File(inputFileName);

        BufferedReader br = null;
        try {
            // 入力ストリームの生成
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader isr = new InputStreamReader(fis, CommonUtill.UTF8);
            br = new BufferedReader(isr);

            // テキストファイルからの読み込み
            String msg;
            boolean isNeedNewline = false;
            while((msg = br.readLine()) != null) {
                if (isNeedNewline) {
                    message += CommonUtill.getNewlineCode();
                    message += msg;
                } else {
                    isNeedNewline = true;
                    message += msg;
                }
            }
            br.close();
        } catch(Exception e) {
            return null;
        }
        return message;
    }
}
