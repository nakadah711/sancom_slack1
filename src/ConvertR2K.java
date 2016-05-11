/**
 * ���[�}������J�i�ւ̕ϊ�
 */
public class ConvertR2K implements ConvertInterface {
    private static final int CLEAR   = 0;
    private static final int STOCK   = 1;
    private static final int SHORTEN = 2;
    private static final int REPLACE = 3;
    private String           inputStr;
    private String           convertStr;
    private String[][]       r2kTable;

    ConvertR2K() {
        inputStr = "";
    }

    ConvertR2K(String inStr) {
        inputStr = inStr;
    }

    /**
     * �ϊ���̕������ԋp
     */
    public String getConvertString(int type) {
        r2kTable = ConvertR2KUtill.getConvertTable(type);
        convert();
        return convertStr;
    }

    /**
     * �ϊ�
     */
    private void convert() {
        if (inputStr == null || inputStr.equals("")) return;

        convertStr = "";
        String buf = "";
        //String workStr = inputStr.toLowerCase();
        String workStr = ConvertR2KUtill.convertPrepare(inputStr);
        String tmp;

        for (int i = 0; i < workStr.length(); i++ ) {
            tmp = workStr.substring(i,i+1);

            int charType = ConvertR2KUtill.charType(tmp);
            if(charType != ConvertR2KUtill.OTHER) {
                //�ϊ��Ώۂ̖������ꉹ
                String convertChar = r2k(buf,charType);
                if(convertChar.equals("")) {
                    //�������ꉹ�Ȃ̂ɕϊ��e�[�u����HIT���Ȃ�����
                    String workBuf = buf;
                    while(workBuf.length() > 0 && convertChar.equals("")) {
                        //�ꕶ�����q�����������ĕϊ��e�[�u������ϊ�����
                        //�ϊ��ł��Ȃ������q���́A���̂܂ܕ\������
                        convertStr = convertStr + workBuf.substring(0,1);
                        workBuf = workBuf.substring(1);
                        convertChar = r2k(workBuf,charType);
                    }
                }
                convertStr = convertStr + convertChar;
                buf = "";
            } else {
                //�ϊ��Ώۂ̖������ꉹ�ȊO
                int result = otherConvert(buf,tmp);
                switch(result){
                    case CLEAR:
                        buf = "";
                        break;
                    case STOCK:
                        buf = buf + tmp;
                        break;
                    case SHORTEN:
                        buf = buf.substring(1) + tmp;
                        break;
                    case REPLACE:
                        buf = tmp;
                        break;
                    default:
                        buf = buf + tmp;
                        break;
                }
            }
        }
    }

    /**
     * 1�������̕ϊ�
     */
    private String r2k(String s, int value) {
        for(int i = 0; i < r2kTable.length; i++) {
            if(s.equals(r2kTable[i][ConvertR2KUtill.CHK_LINE])) {
                return r2kTable[i][value];
            }
        }
        return s + r2kTable[ConvertR2KUtill.CHK_LINE][value];
    }

    /**
     * �ꉹ���܂܂Ȃ������̕ϊ�
     */
    private int otherConvert(String buf, String tmp) {
        int ret = CLEAR;
        if(buf.equals("n") && !tmp.equals("y")) {
            convertStr = convertStr + "��";
            if(!tmp.equals("n")) {
                ret = SHORTEN;
            }
        } else if(java.lang.Character.isLetter(tmp.charAt(0))) {
            if(buf.equals(tmp)) {
                convertStr = convertStr + "��";
                ret = REPLACE;
            } else if(buf.equals("")) {
                ret = REPLACE;
            } else {
                if(isPermissionStr(buf + tmp)) {
                    ret = STOCK;
                } else {
                    convertStr = convertStr + buf;
                    ret = REPLACE;
                }
            }
        } else {
            convertStr = convertStr + buf + specialConvert(tmp);
        }
        return ret;
    }

    /**
     * ����ȕϊ�
     */
    private String specialConvert(String s) {
        //TODO:�C�ɂȂ�̂���������ǉ�������폜������
        if(s.equals(",") || s.equals("�C")) return "�A";
        if(s.equals(".") || s.equals("�D")) return "�B";
        if(s.equals("-") || s.equals("�]") || s.equals("�|")) return "�[";
        return s;
    }

    /**
     * ���e���ꂽ�q���̑g�ݍ��킹���ǂ���
     */
    private boolean isPermissionStr(String s) {
        for(int i = 0; i < r2kTable.length; i++) {
            if(s.equals(r2kTable[i][ConvertR2KUtill.CHK_LINE])) {
                return true;
            }
        }
        return false;
    }
}
