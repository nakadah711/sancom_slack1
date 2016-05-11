/**
 * ローマ字からカナへの変換
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
     * 変換後の文字列を返却
     */
    public String getConvertString(int type) {
        r2kTable = ConvertR2KUtill.getConvertTable(type);
        convert();
        return convertStr;
    }

    /**
     * 変換
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
                //変換対象の末尾が母音
                String convertChar = r2k(buf,charType);
                if(convertChar.equals("")) {
                    //末尾が母音なのに変換テーブルにHITしなかった
                    String workBuf = buf;
                    while(workBuf.length() > 0 && convertChar.equals("")) {
                        //一文字ずつ子音を除去して変換テーブルから変換する
                        //変換できなかった子音は、そのまま表示する
                        convertStr = convertStr + workBuf.substring(0,1);
                        workBuf = workBuf.substring(1);
                        convertChar = r2k(workBuf,charType);
                    }
                }
                convertStr = convertStr + convertChar;
                buf = "";
            } else {
                //変換対象の末尾が母音以外
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
     * 1文字分の変換
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
     * 母音を含まない文字の変換
     */
    private int otherConvert(String buf, String tmp) {
        int ret = CLEAR;
        if(buf.equals("n") && !tmp.equals("y")) {
            convertStr = convertStr + "ん";
            if(!tmp.equals("n")) {
                ret = SHORTEN;
            }
        } else if(java.lang.Character.isLetter(tmp.charAt(0))) {
            if(buf.equals(tmp)) {
                convertStr = convertStr + "っ";
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
     * 特殊な変換
     */
    private String specialConvert(String s) {
        //TODO:気になるのがあったら追加したり削除したり
        if(s.equals(",") || s.equals("，")) return "、";
        if(s.equals(".") || s.equals("．")) return "。";
        if(s.equals("-") || s.equals("‐") || s.equals("－")) return "ー";
        return s;
    }

    /**
     * 許容された子音の組み合わせかどうか
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
