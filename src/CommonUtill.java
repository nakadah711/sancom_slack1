/**
 * ‹¤’Ê
 */
public class CommonUtill {
    public  static final String UTF8 = "UTF-8";
    private static final String OS   = System.getProperty("os.name").toLowerCase();
    private static final String CRLF = "\r\n";
    private static final String LF   = "\n";
    private static final String CR   = "\r";

    public static String getNewlineCode() {
        if (OS.startsWith("linux"))        return LF;
        else if (OS.startsWith("mac"))     return CR;
        else if (OS.startsWith("windows")) return CRLF;
        return LF;
    }
}
