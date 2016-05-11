import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.FileNotFoundException;

/**
 * �e�L�X�g�t�@�C���̓ǂݍ���
 */
public class TextfileRead {
    public static final String getFileContens(String path) {
        String message = "";

        // �ǂݍ��ރt�@�C���̖��O
        String inputFileName = path;
        // �t�@�C���I�u�W�F�N�g�̐���
        File inputFile = new File(inputFileName);

        BufferedReader br = null;
        try {
            // ���̓X�g���[���̐���
            FileInputStream fis = new FileInputStream(inputFile);
            InputStreamReader isr = new InputStreamReader(fis, CommonUtill.UTF8);
            br = new BufferedReader(isr);

            // �e�L�X�g�t�@�C������̓ǂݍ���
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
