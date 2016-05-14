public class ConvertStringData {
    private String convertS_;           //�ϊ�������ς񂾃f�[�^
    private String originS_;            //�ϊ��O�̕�����ς񂾃f�[�^
    private String convertCurrent_;     //�ϊ��Ώۂ̃J�����g����
    private String originCurrent_;      //�ϊ��O�̃J�����g����

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
     * �A���t�@�x�b�g�̒u��
     */
    private String convertPrepare(String s) {
        return zenkaku2hankaku(s).toLowerCase();
    }
    
    /**
     * �S�p�𔼊p�ɕϊ�
     */
    private String zenkaku2hankaku(String s) {
        StringBuffer sb = new StringBuffer(s);
        for (int i = 0; i < sb.length(); i++) {
            char c = sb.charAt(i);
            if (c >= '��' && c <= '��') {
                sb.setCharAt(i, (char) (c - '��' + 'a'));
            } else if (c >= '�`' && c <= '�y') {
                sb.setCharAt(i, (char) (c - '�`' + 'A'));
            }
        }
        return sb.toString();
    }
}
