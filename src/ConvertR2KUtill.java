/**
 * Ï·\
 */
public class ConvertR2KUtill {
    //TODO:Pß®Ìe[u
    public static final int CHK_LINE = 0;
    public static final int A_DAN    = 1;
    public static final int I_DAN    = 2;
    public static final int U_DAN    = 3;
    public static final int E_DAN    = 4;
    public static final int O_DAN    = 5;
    public static final int OTHER    = 6;

    private static String[][] R2K_MIX_TABLE = {
        {   "", " ","¢","¤","¦","¨" },
        {  "k", "©","«","­","¯","±" },
        {  "s", "³","µ","·","¹","»" },
        {  "t", "½","¿","Â","Ä","Æ" },
        {  "n", "È","É","Ê","Ë","Ì" },
        {  "h", "Í","Ð","Ó","Ö","Ù" },
        {  "m", "Ü","Ý","Þ","ß","à" },
        {  "y", "â","¢","ä","¢¥","æ" },
        {  "r", "ç","è","é","ê","ë" },
        {  "w", "í","¤¡","¤","¤¥","ð" },

        {  "g", "ª","¬","®","°","²" },
        {  "z", "´","¶","¸","º","¼" },
        {  "j", "¶á","¶","¶ã","¶¥","¶å" },
        {  "d", "¾","À","Ã","Å","Ç" },
        {  "b", "Î","Ñ","Ô","×","Ú" },
        {  "p", "Ï","Ò","Õ","Ø","Û" },

        { "gy", "¬á","¬¡","¬ã","¬¥","¬å" },
        { "zy", "¶á","¶¡","¶ã","¶¥","¶å" },
        { "jy", "¶á","¶¡","¶ã","¶¥","¶å" },
        { "dy", "Àá","À¡","Àã","À¥","Àå" },
        { "by", "Ñá","Ñ¡","Ñã","Ñ¥","Ñå" },
        { "py", "Òá","Ò¡","Òã","Ò¥","Òå" },

        {  "l", "","¡","£","¥","§" },
        {  "v", "","¡","","¥","§" },
        { "sh", "µá","µ","µã","µ¥","µå" },
        { "sy", "µá","µ","µã","µ¥","µå" },
        { "ch", "¿á","¿","¿ã","¿¥","¿å" },
        { "cy", "¿á","¿¡","¿ã","¿¥","¿å" },

        {  "f", "Ó","Ó¡","Ó","Ó¥","Ó§" },
        {  "q", "­","­¡","­","­¥","­§" },
        { "ky", "«á","«¡","«ã","«¥","«å" },
        { "ty", "¿á","¿¡","¿ã","¿¥","¿å" },
        { "ny", "Éá","É¡","Éã","É¥","Éå" },
        { "hy", "Ðá","Ð¡","Ðã","Ð¥","Ðå" },
        { "my", "Ýá","Ý¡","Ýã","Ý¥","Ýå" },
        { "ry", "èá","è¡","èã","è¥","èå" },
        { "ly", "á","¡","ã","¥","å" },
        //{ "lt", "½","¿","Á","Ä","Æ" },
        //ÏXª
        { "lt", "","","Á","","" },

        //ÇÁª
        { "ts", "Â","Â¡","Â","Â¥","Â§" },
        { "wy", "","î","","ï","" },
        { "qy", "­á","­¡","­ã","­¥","­å" },
        { "qw", "­á","­¡","­ã","­¥","­å" },
        { "sw", "·","·¡","·£","·¥","·§" },
        {  "c", "©","µ","­","¹","±" },
        { "th", "Äá","Ä¡","Äã","Ä¥","Äå" },
        { "tw", "Æ","Æ¡","Æ£","Æ¥","Æ§" },
        { "dh", "Åá","Å¡","Åã","Å¥","Åå" },
        { "dw", "Ç","Ç¡","Ç£","Ç¥","Ç§" },
        { "wh", "¤","¤¡","¤","¤¥","¤§" },
        { "vy", "á","¡","ã","¥","å" },
        { "kw", "­","­¡","","­¥","­§" },
        { "gw", "®","®¡","®£","®¥","®§" },
        { "fy", "Óá","Ó¡","Óã","Ó¥","Óå" },
        { "fw", "Ó","Ó¡","Ó£","Ó¥","Ó§" },
        { "lk", "","","","","" },
        { "lw", "ì","","","","" },
        {  "x", "","¡","£","¥","§" },
        { "xk", "","","","","" },
        { "xt", "","","Á","","" },
        { "xts", "","","Á","","" },
        { "xy", "á","¡","ã","¥","å" },
        { "xw", "ì","","","","" },
    };

    private static String[][] R2K_HEBON_TABLE = {
        {   "", " ","¢","¤","¦","¨" },
        {  "k", "©","«","­","¯","±" },
        {  "s", "³","","·","¹","»" },
        {  "t", "½","","","Ä","Æ" },
        {  "n", "È","É","Ê","Ë","Ì" },
        {  "h", "Í","Ð","","Ö","Ù" },
        {  "m", "Ü","Ý","Þ","ß","à" },
        {  "y", "â","","ä","","æ" },
        {  "r", "ç","è","é","ê","ë" },
        {  "w", "í","","","","" },

        {  "g", "ª","¬","®","°","²" },
        {  "z", "´","","¸","º","¼" },
        {  "j", "¶á","¶","¶ã","","¶å" },
        {  "d", "¾","","","Å","Ç" },
        {  "b", "Î","Ñ","Ô","×","Ú" },
        {  "p", "Ï","Ò","Õ","Ø","Û" },
        { "gy", "¬á","","¬ã","","¬å" },
        { "by", "Ñá","","Ñã","","Ñå" },
        { "py", "Òá","","Òã","","Òå" },

        {  "v", "","¡","","¥","§" },
        { "sh", "µá","µ","µã","","µå" },
        { "ch", "¿á","¿","¿ã","","¿å" },

        {  "f", "","","Ó","","" },
        { "ky", "«á","","«ã","","«å" },
        { "ny", "Éá","","Éã","","Éå" },
        { "hy", "Ðá","","Ðã","","Ðå" },
        { "my", "Ýá","","Ýã","","Ýå" },
        { "ry", "èá","","èã","","èå" },
        { "ts", "","","Â","","" },
    };

    /**
     * Ï·e[uÌæ¾
     */
    public static String[][] getConvertTable(int type) {
        //TODO:typeÅw{®ÌÝÌe[uðÔpµ½èAPß®ÌÝÌe[uðÔpµ½è
        String[][] ret = R2K_MIX_TABLE;
        switch(type){
            case ConvertR2K.CONVERT_TYPE_MIX:
                ret = R2K_MIX_TABLE;
                break;
            case ConvertR2K.CONVERT_TYPE_HEBON:
                ret = R2K_HEBON_TABLE;
                break;
            default:
                break;
        }
        return ret;
    }

    /**
     * ¶íÊÌ»è
     */
    public static int charType(String s) {
        int ret = OTHER;
        if(s.equals("a")) {
            ret = A_DAN;
        } else if(s.equals("i")) {
            ret = I_DAN;
        } else if(s.equals("u")) {
            ret = U_DAN;
        } else if(s.equals("e")) {
            ret = E_DAN;
        } else if(s.equals("o")) {
            ret = O_DAN;
        }
        return ret;
    }
}
