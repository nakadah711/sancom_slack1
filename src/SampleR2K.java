/**
 * ���[�}������Ђ炪�Ȃ֕ϊ�����T���v��
 */
public class SampleR2K {
    public static void main(String[] args) {
        if(args.length != 2) {
            System.out.print("�������s���ł�");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("�Ǎ��t�@�C���p�X�Əo�̓t�@�C���p�X���w�肵�Ă�������");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("�������F�Ǎ��t�@�C���p�X");
            System.out.print(CommonUtill.getNewlineCode());
            System.out.print("�������F�o�̓t�@�C���p�X");
            return;
        } else {
            String inputStr = TextfileRead.getFileContens(args[0]);
            if(inputStr == null) {
                System.out.print("�t�@�C����ǂݍ��ݏo���܂���ł���");
                System.out.print(CommonUtill.getNewlineCode());
                System.out.print("�������A�܂��̓t�@�C�����m�F���Ă�������");
            } else if (inputStr.equals("")) {
                System.out.print("�t�@�C���̒��g����ł���");
            } else {
                ConvertR2K crk = new ConvertR2K(inputStr);
                String convertStr = crk.getConvertString(ConvertR2K.CONVERT_TYPE_MIX);
                boolean bRet = TextfileWrite.createText(args[1],convertStr);
                if(bRet) {
                    System.out.print("�Ǎ��t�@�C���p�X�F" + args[0]);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("�o�̓t�@�C���p�X�F" + args[1]);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("�Ǎ��t�@�C�����g�F" + inputStr);
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("�ϊ����ꂽ������F" + convertStr);
                    System.out.print(CommonUtill.getNewlineCode());
                } else {
                    System.out.print("�t�@�C���o�͂Ɏ��s���܂���");
                    System.out.print(CommonUtill.getNewlineCode());
                    System.out.print("�o�̓t�@�C���p�X���m�F���Ă�������");
                }
            }
        }
    }
}
