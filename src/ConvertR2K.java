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
        String workStr = inputStr;
        //String tmp;
        ConvertStringData csd = new ConvertStringData();

        for (int i = 0; i < workStr.length(); i++ ) {
            csd.setOriginCurrent(workStr.substring(i,i+1));
            csd.setConvertCurrent(csd.getOriginCurrent());

            int charType = ConvertR2KUtill.charType(csd.getConvertCurrent());
            if (charType != ConvertR2KUtill.OTHER) {
                //変換対象の末尾が母音
                String convertChar = r2k(csd.getConvertS(),charType);
                if (convertChar.equals("")) {
                    //末尾が母音なのに変換テーブルにHITしなかった
                    for (int j = 0; j < csd.getConvertS().length(); j++) {
                        //一文字ずつ子音を除去して変換テーブルから変換する
                        //変換できなかった子音は、そのまま表示する
                        convertStr = convertStr + csd.getOriginS().substring(j,1);
                        convertChar = r2k(csd.getConvertS().substring(j+1),charType);
                        if (!convertChar.equals("")) break;
                    }
                }
                convertStr = convertStr + convertChar;
                csd.clearConvertS();
            } else {
                //変換対象の末尾が母音以外
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
     * 1文字分の変換
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
     * 母音を含まない文字の変換
     */
    private int otherConvert(ConvertStringData csd) {
        int ret = CLEAR;
        if (csd.getConvertS().equals("n") && !csd.getConvertCurrent().equals("y")) {
            convertStr = convertStr + "ん";
            if(!csd.getConvertCurrent().equals("n")) {
                ret = SHORTEN;
            }
        } else if (java.lang.Character.isLetter(csd.getConvertCurrent().charAt(0))) {
            if (csd.getConvertS().equals(csd.getConvertCurrent())) {
                convertStr = convertStr + "っ";
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
     * 特殊な変換
     */
    private String specialConvert(String s) {
        //TODO:気になるのがあったら追加したり削除したり
        if (s.equals(",") || s.equals("，")) return "、";
        if (s.equals(".") || s.equals("．")) return "。";
        if (s.equals("-") || s.equals("‐") || s.equals("－")) return "ー";
        return s;
    }

    /**
     * 許容された子音の組み合わせかどうか
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
