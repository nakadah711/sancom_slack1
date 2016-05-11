/**
 * ローマ字からひらがなへ変換するサンプル
 */
public class SampleR2K {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.print("引数が不正です");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("読込ファイルパスと出力ファイルパスを指定してください");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("第一引数：読込ファイルパス");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("第二引数：出力ファイルパス");
            return;
        } else {
            String inputStr = TextfileRead.getFileContens(args[0]);
            if(inputStr == null) {
                System.out.print("ファイルを読み込み出来ませんでした");
                System.out.print(CommonUtill.getNewlineCode());
                System.out.print("第一引数、またはファイルを確認してください");
            } else if (inputStr.equals("")) {
                System.out.print("ファイルの中身が空でした");
            } else {
                ConvertR2K crk = new ConvertR2K(inputStr);
                String convertStr = crk.getConvertString(ConvertR2K.CONVERT_TYPE_MIX);
                boolean bRet = TextfileWrite.createText(args[1],convertStr);
                if(bRet) {
                    System.out.print("読込ファイルパス：" + args[0]);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("出力ファイルパス：" + args[1]);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("読込ファイル中身：" + inputStr);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("変換された文字列：" + convertStr);
                    System.out.print(CommonUtill.getNewlineCode());
                } else {
                    System.out.print("ファイル出力に失敗しました");
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("出力ファイルパスを確認してください");
                }
            }
        }
    }
}
