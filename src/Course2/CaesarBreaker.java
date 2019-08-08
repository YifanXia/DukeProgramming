package Course2;

import edu.duke.FileResource;

public class CaesarBreaker {

    public int[] countLetters(String message) {
        String alphabet = "abcdefghijklmnopqrstuvwxyz";
        int[] counts = new int[alphabet.length()];
        for (int i = 0; i < message.length(); i ++) {
            int index = alphabet.indexOf(Character.toLowerCase(message.charAt(i)));
            if (index != -1) {
                counts[index] ++;
            }
        }
        return counts;
    }

    public int indexOfMax(int[] values) {
        int indMax = 0;
        for (int i = 0; i < values.length; i++) {
            if (values[i] > values[indMax]) {
                indMax = i;
            }
        }
        return indMax;
    }

    public String decrypt(String encrypted) {
        CaesarCipher cc = new CaesarCipher();
        /*int[] letterCounts = countLetters(encrypted);
        int maxIndex = indexOfMax(letterCounts);
        int dKey = maxIndex - 4;
        if (maxIndex < 4) {
            dKey = 26 + dKey;
        }*/
        int dKey = getKey(encrypted);
        return cc.encrypt(encrypted, 26 - dKey);
    }

    public String halfOfString(String message, int start) {
        StringBuilder halfMessage = new StringBuilder("");
        for (int i = start; i < message.length(); i += 2) {
            halfMessage.append(message.charAt(i));
        }
        return halfMessage.toString();
    }

    public int getKey(String message) {
        int[] counts = countLetters(message);
        int maxIndex = indexOfMax(counts);
        int key = maxIndex - 4;
        if (maxIndex < 4) {
            key = 26 + key;
        }
        return key;
    }

    public String decryptTwoKeys(String message) {
        StringBuilder fullDecrypted = new StringBuilder("");
        String evenIndexHalf = halfOfString(message, 0);
        String oddIndexHalf = halfOfString(message, 1);
        System.out.println("First key is: " + getKey(evenIndexHalf));
        System.out.println("Second key is: " + getKey(oddIndexHalf));
        String evenDecrypted = decrypt(evenIndexHalf);
        String oddDecrypted = decrypt(oddIndexHalf);
        int shortHalfLength = Math.min(evenIndexHalf.length(), oddIndexHalf.length());
        for (int i = 0; i < shortHalfLength; i ++) {
            fullDecrypted.append(evenDecrypted.charAt(i));
            fullDecrypted.append(oddDecrypted.charAt(i));
        }
        if (evenIndexHalf.length() > oddIndexHalf.length()) {
            int lastIndex = evenIndexHalf.length() - 1;
            fullDecrypted.append(evenDecrypted.charAt(lastIndex));
        }
        return fullDecrypted.toString();
    }

    public void testDecrypt() {
        CaesarCipher cc = new CaesarCipher();
        String message = "Just a test string with lots of eeeeeeeeeeeeeeeees";
        String encrypted = cc.encrypt(message, 13);
        System.out.println(decrypt(encrypted));

    }

    public void testDecryptTwoKeys() {
        //String encrypted = "Gwpv c vbuq pvokki yfve iqqu qc bgbgbgbgbgbgbgbgbu";
        //String encrypted = "Akag tjw Xibhr awoa aoee xakex znxag xwko";
        String encrypted = "Xifqvximt tsdtlxzrx iijirvtl ek Uybi afvbw yehvv xyi gfqdse iekmfrrpzdrxzse fj xyi jzich sw tsdtlxrxzseec xifqvxic, fjkie xmmie zr xyi trwk, xyek klv nsipu rvfyeh yj zw xyvvi-hzqvrjmfrrp eeh ulijxzsew lfa xymekj zr xymj nsipu iiceki xf vetl sklvv eii melvvvrkpp xifqvximt. Xrov dsmmek e tzees xyvfyxl e hfsi-wvrqv rru gprremek e jcmxlk-gekl xyek rzfmuw gfpcmjmfrj nmkl sklvv ezvgprrvw ej kaf vbrqgpvw. Zx wyslpu klvvvjfvv esk jyitimji xyek tsdtlxzrx gvftvvkmvw esslx xyiji kvsdikvzg xymekj rru klvmi zrkiietxzse rvv tsdqfr-tceti eeh mdtfvkeex. Nlzpv klzw mj jxzpc r mecmu rvxydiex, ni afych pzov ks edieh xyek dsjx sw klv xifqvximt hyvwkmfrj giftci gfrtiir xyidwvpmij nmkl lrzv ks hf nmkl lfa xymekj rvv tservgkiu. Mk zw mdtfvkeex xyek ymxlnepw eii wljwmtmvrkpp jxiezkyx eeh wdsfxy ks wltgsix xyi himmmek sw wejx grvj, flx eesklvv ijwvrkmrp tisgiixp, aymtl av lwlecpp kebi jfv kieexvh, zw xyek ymxlnepw eii gfrkmeyfyj, mehviu tservgkmek E xf S, eeh rfx nlwk rtgvfbzqrxvpp. Xyi gfviijtfrumek wlfwmvpu fj gfqgykekmfrrp kvsdikvp zw swxvr vvjvviiu ks ej tsdtlxrxzseec ksgscsxc. R xsfh tfvkmfr sw fyi vjwsixj dep si gcejwzjziu ks fvpfrx ks xymj jysjzich eeh eii himmie sc egtcmtekmfrj zr e zrvzikc sw fxyii wmvpuw, klv gvvhzgkmfr sw klv jxiytxlvv fj jfpuiu gvfxvmew eeh xyi vvgfrjxiytxzse fj llqrr sikrrj sizrx kaf. Xyi lrpcqrvb fj slv afvb zw jrwk rpxsimkldw xyek zqgpvqvrk deklvqrxzgrp qfhvpj ks swjvv mewzkyxj zrks eeh eewniiw xf jytl ulijxzsew.\n" +
                "\n" +
                "Av rvv vbgpfvzrx zwjyvw wlgy rw lfa xvgyrzulij wsi jsczzrx gvffcidw grr fv umjgfzvvvh, zqgvfzvh, rrrppdvh, rru uidsewkvrxvh xf si gfviitx si ftkmdec. Av vbgitx xf debi pveumek gfrkvzflxzsew me tsdtlxrxzseec xifqvxic, xifqvximt dsuicmek, ueke wkvlgkyiij, lzky-giijfvdeegv tsdtlxzrx, M/S-iwjzgziegp wsi vbkiirrp qvqfvp, kvsxvrtymt zrwsiqrxzse jcjxvqj (KZW), fzscsxmtec tsdtlxzrx, eeh hrxr tsdtiijwzse.\n" +
                "\n" +
                "Sitelwv fj xyi kvsdikvzg rrxlvv fj xyi tycjmtec nsipu zr aymtl av cmmi, xifqvximt gvffcidw eimji me rrp rvve xyek zrkiietxj nmkl xyi tycjmtec nsipu. Kvsdikvzg gfqgykmek jfglwvw se uijmxrzrx, eeeccqmek, rru zqgpvqvrkmek iwjzgziex eckfvzxyqj wsi xifqvximt gvffcidw. Klv xifqvximt tsdtlxzrx xvfyg fj xyi hvtrvkqvrk zw mexvveekmfrrpcc vvrfaeiu wsi zxj tseximsykmfrj ks xyi jlruediexrp tisspvqj zr gfqgykekmfrrp kvsdikvp, euhiijwzrx dejwzzv ueke qrrrkvqvrk zwjyvw me xifqvximt gvffcidw, rru rtgppmek kvsdikvzg xvgyrzulij ks e zrvzikc sw rvvej, megcyumek qfpvglprv fzscsxc, xifqvximt dsuicmek, issskmtw, xifkieglzg mejfvdekmfr wpwkidw, vgfpfkp, eeh tysksemtw.\n" +
                "\n" +
                "Xyi kislt etxzzvpp tscprffvrxvw azxy fxyii xvfygw azxyme klv uigeixdiex eeh azxy klv iijirvtlvvj zr sklvv hvtrvkqvrkw ek Uybi. Klvc gfpcessieki azxy wetycxp zr Qrxyidekmtw, Smfpfkp, Fzstlvqzwkvp, Icitximtec rru Tsdtlxvv Iekzrviimek, rru klv Emtlfprw Wtlfsc fj Iezzvfrdiex. Sipseh Hlov, xyi kislt ecwf tscprffvrxvw azxy iijirvtlvvj rx zrvzslw xft mewkmkykij. Fvgryji sw zxj uigxy rru svveuxy, xyi kvsdikvzg gfqgykmek kislt ek Uybi mj rvxyrfcc xyi xft kvsdikvzg gfqgykmek kislt me klv eekmfr.";
        String decrypted = decryptTwoKeys(encrypted);
        System.out.println(decrypted);
    }


    public static void main(String[] args) {
        CaesarBreaker cb = new CaesarBreaker();
        cb.testDecrypt();
        cb.testDecryptTwoKeys();
    }
}
