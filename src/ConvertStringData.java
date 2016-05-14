public class ConvertStringData {
    private String convertS_;           //変換文字を積んだデータ
    private String originS_;            //変換前の文字を積んだデータ
    private String convertCurrent_;     //変換対象のカレント文字
    private String originCurrent_;      //変換前のカレント文字

    public void setConvertS(String originS) {
        convertS_ = convertPrepare(originS);
    }

    public String getConvertS() {
        if (convertS_ == null) convertS_ = "";
        return convertS_;
    }

    public void setOriginS(String originS) {
        originS_  = originS;
    }

    public String getOriginS() {
        if (originS_ == null) originS_ = "";
        return originS_;
    }

    public void setConvertCurrent(String originCurrent) {
        convertCurrent_ = convertPrepare(originCurrent);
    }

    public String getConvertCurrent() {
        return convertCurrent_;
    }

    public void setOriginCurrent(String originCurrent) {
        originCurrent_  = originCurrent;
    }

    public String getOriginCurrent() {
        return originCurrent_;
    }

    public void clearConvertS() {
        originS_  = "";
        convertS_ = "";
    }

    /**
     * アルファベットの置換
     */
    private String convertPrepare(String s) {
        return zenkaku2hankaku(s).toLowerCase();
    }
    
    /**
     * 全角を半角に変換
     */
    private String zenkaku2hankaku(String s) {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= 'ａ' && c <= 'ｚ') {
                sb.setCharAt(i, (char) (c - 'ａ' + 'a'));
            } else if (c >= 'Ａ' && c <= 'Ｚ') {
                sb.setCharAt(i, (char) (c - 'Ａ' + 'A'));
            }
        }
        return sb.toString();
    }
}
