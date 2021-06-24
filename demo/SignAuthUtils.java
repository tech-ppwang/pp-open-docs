package com.ppwang.open.utils;
import com.tencent.tsf.gateway.core.constant.AlgType;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.HmacAlgorithms;
import org.apache.commons.codec.digest.HmacUtils;
import java.util.Arrays;
import java.util.List;
/**
 * 批批网
 * ```
 * ================================================
 * 版权所有: 广州市批来批往信息科技有限公司，并保留所有权利。
 * 网站地址: https://www.ppwang.com
 * ================================================
 * 创建者: 研发中心 <tech@ppwang.com>
 * ```
 * ------------------------------------------------
 * Class SignAuthUtils
 *
 * @author lizhuohuan <birdylee_cn@163.com>
 * @description 描述内容
 * @date 2020-12-29
 * ------------------------------------------------
 */
@Slf4j
public class SignAuthUtils {
    public final static List<String> algSupportList = Arrays.asList("md5");
    /**
     *
     * @param alg
     * @return
     */
    public static boolean isAlgSupported(String alg) {
        return algSupportList.contains(alg);
    }
    /**
     * 生成签名
     *
     * @param alg
     * @param appId
     * @param appKey
     * @param nonce
     * @param timestamp
     * @return
     * @throws Exception
     */
    public static String generateSign(String alg, String appId, String appKey, String nonce, String timestamp) throws Exception {
        String digestValue = nonce + appId + appKey + timestamp;
        byte[] serverSignBytes;
        if (alg.equals("md5")) {
            serverSignBytes = new HmacUtils(HmacAlgorithms.HMAC_MD5, appKey).hmac(digestValue);
        } else {
            throw new Exception("alg illegal");
        }
        String signValue = Base64.encodeBase64String(serverSignBytes);
        if (log.isDebugEnabled()) {
            log.debug("签名明文：{}，签名密文：{}", digestValue, signValue);
        }
        return signValue;
    }
    /**
     * 验证签名
     *
     * @param alg
     * @param appId
     * @param appKey
     * @param nonce
     * @param timestamp
     * @param sign
     * @return
     * @throws Exception
     */
    public static boolean isSignOk(String alg, String appId, String appKey, String nonce, String timestamp, String sign) throws Exception {
        String signGenerated = generateSign(alg, appId, appKey, nonce, timestamp);
        return signGenerated.equals(sign);
    }
    /**
     * 调试用
     *
     * @param args
     * @throws Exception
     */
    public static void main(String[] args) throws Exception {
        String appId = "xakeaxkde";
        String appKey = "dia1xMald53AleigmdekAdxmqLekgDxA";
        String nonce = "adek12GdmMdkelAkd95kglRiyOpglDdm";
        String alg = "md5";
        String timestamp = "1624502662";
        System.out.println(generateSign(alg, appId, appKey, nonce, timestamp));
    }
}