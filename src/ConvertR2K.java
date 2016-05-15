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
        String workStr = inputStr;
        //String tmp;
        ConvertStringData csd = new ConvertStringData();

        for (int i = 0; i < workStr.length(); i++ ) {
            csd.setOriginCurrent(workStr.substring(i,i+1));
            csd.setConvertCurrent(csd.getOriginCurrent());

            int charType = ConvertR2KUtill.charType(csd.getConvertCurrent());
            if (charType != ConvertR2KUtill.OTHER) {
                //�ϊ��Ώۂ̖������ꉹ
                String convertChar = r2k(csd.getConvertS(),charType);
                if (convertChar.equals("")) {
                    //�������ꉹ�Ȃ̂ɕϊ��e�[�u����HIT���Ȃ�����
                    for (int j = 0; j < csd.getConvertS().length(); j++) {
                        //�ꕶ�����q�����������ĕϊ��e�[�u������ϊ�����
                        //�ϊ��ł��Ȃ������q���́A���̂܂ܕ\������
                        convertStr = convertStr + csd.getOriginS().substring(j,1);
                        convertChar = r2k(csd.getConvertS().substring(j+1),charType);
                        if (!convertChar.equals("")) break;
                    }
                }
                convertStr = convertStr + convertChar;
                csd.clearConvertS();
            } else {
                //�ϊ��Ώۂ̖������ꉹ�ȊO
                int result = otherConvert(csd);
                switch(result){
                    case CLEAR:
                        csd.clearConvertS();
                        break;
                    case STOCK:
                        csd.setConvertS(csd.getConvertS() + csd.getConvertCurrent());
                        csd.setOriginS(csd.getOriginS() + csd.getOriginCurrent());
                        break;
                    case SHORTEN:
                        csd.setConvertS(csd.getConvertS().substring(1) + csd.getConvertCurrent());
                        csd.setOriginS(csd.getOriginS().substring(1) + csd.getOriginCurrent());
                        break;
                    case REPLACE:
                        csd.setConvertS(csd.getConvertCurrent());
                        csd.setOriginS(csd.getOriginCurrent());
                        break;
                    default:
                        csd.setConvertS(csd.getConvertS() + csd.getConvertCurrent());
                        csd.setOriginS(csd.getOriginS() + csd.getOriginCurrent());
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
            if (s.equals(r2kTable[i][ConvertR2KUtill.CHK_LINE])) {
                return r2kTable[i][value];
            }
        }
        //return s + r2kTable[ConvertR2KUtill.CHK_LINE][value];
        return "";
    }

    /**
     * �ꉹ���܂܂Ȃ������̕ϊ�
     */
    private int otherConvert(ConvertStringData csd) {
        int ret = CLEAR;
        if (csd.getConvertS().equals("n") && !csd.getConvertCurrent().equals("y")) {
            convertStr = convertStr + "��";
            if(!csd.getConvertCurrent().equals("n")) {
                ret = SHORTEN;
            }
        } else if (java.lang.Character.isLetter(csd.getConvertCurrent().charAt(0))) {
            if (csd.getConvertS().equals(csd.getConvertCurrent())) {
                convertStr = convertStr + "��";
                ret = REPLACE;
            } else if (csd.getConvertS().equals("")) {
                ret = REPLACE;
            } else {
                if (isPermissionStr(csd.getConvertS() + csd.getConvertCurrent())) {
                    ret = STOCK;
                } else {
                    convertStr = convertStr + csd.getOriginS();
                    ret = REPLACE;
                }
            }
        } else {
            convertStr = convertStr + csd.getOriginS() + specialConvert(csd.getOriginCurrent());
        }
        return ret;
    }

    /**
     * ����ȕϊ�
     */
    private String specialConvert(String s) {
        //TODO:�C�ɂȂ�̂���������ǉ�������폜������
        if (s.equals(",") || s.equals("�C")) return "�A";
        if (s.equals(".") || s.equals("�D")) return "�B";
        if (s.equals("-") || s.equals("�]") || s.equals("�|")) return "�[";
        return s;
    }

    /**
     * ���e���ꂽ�q���̑g�ݍ��킹���ǂ���
     */
    private boolean isPermissionStr(String s) {
        for(int i = 0; i < r2kTable.length; i++) {
            if (s.equals(r2kTable[i][ConvertR2KUtill.CHK_LINE])) {
                return true;
            }
        }
        return false;
    }
}
