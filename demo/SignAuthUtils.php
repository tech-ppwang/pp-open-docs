<?php
/**
 * 批批网
 *
 * ================================================
 * 版权所有: 广州市批来批往信息科技有限公司，并保留所有权利。
 * 网站地址: https://www.ppwang.com
 * ================================================
 *
 * 创建者: 研发中心 <tech@ppwang.com>
 * 日期: 2021-06-24
 *
 */


class SignAuthUtils
{
    public static function generateSign($alg, $appId, $appKey, $nonce, $timestamp)
    {
        $digestValue = $nonce . $appId . $appKey . $timestamp;
        $serverSignBytes = hash_hmac($alg, $digestValue, $appKey, true);
        return base64_encode($serverSignBytes);
    }
}

$appId = 'xakeaxkde';
$appKey = 'dia1xMald53AleigmdekAdxmqLekgDxA';
$nonce = 'adek12GdmMdkelAkd95kglRiyOpglDdm';
$timestamp = 1624502662;
$alg = 'md5';

echo SignAuthUtils::generateSign($alg, $appId, $appKey, $nonce, $timestamp);