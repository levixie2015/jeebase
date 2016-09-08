package com.doubleview.jeebase.system.utils;

import com.doubleview.jeebase.support.utils.DigestUtils;
import com.doubleview.jeebase.support.utils.EncodeUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * 系统工具类
 */
public class SystemUtils {


    public static final String HASH_ALGORITHM = "SHA-1";
    public static final int HASH_INTERATIONS = 1024;
    public static final int SALT_SIZE = 8;

    /**
     * 生成安全的密码，生成随机的16位salt并经过1024次 sha-1 hash
     * @param plainPassword 明文密码
     */
    public static String entryptPassword(String plainPassword) {
        String plain = EncodeUtils.unescapeHtml(plainPassword);
        byte[] salt = DigestUtils.generateSalt(SALT_SIZE);
        byte[] hashPassword = DigestUtils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return EncodeUtils.encodeHex(salt)+ EncodeUtils.encodeHex(hashPassword);
    }

    /**
     * 验证密码
     * @param plainPassword 明文密码
     * @param password 密文密码
     * @return 验证成功返回true
     */
    public static boolean validatePassword(String plainPassword, String password) {
        String plain = EncodeUtils.unescapeHtml(plainPassword);
        byte[] salt = EncodeUtils.decodeHex(password.substring(0, 16));
        byte[] hashPassword = DigestUtils.sha1(plain.getBytes(), salt, HASH_INTERATIONS);
        return password.equals(EncodeUtils.encodeHex(salt)+ EncodeUtils.encodeHex(hashPassword));
    }


    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, String title){
        saveLog(request, null, null, title);
    }

    /**
     * 保存日志
     */
    public static void saveLog(HttpServletRequest request, Object handler, Exception ex, String title){
/*        User user = UserUtils.getUser();
        if (user != null && user.getId() != null){
            Log log = new Log();
            log.setTitle(title);
            log.setType(ex == null ? Log.TYPE_ACCESS : Log.TYPE_EXCEPTION);
            log.setRemoteAddr(StringUtils.getRemoteAddr(request));
            log.setUserAgent(request.getHeader("user-agent"));
            log.setRequestUri(request.getRequestURI());
            log.setParams(request.getParameterMap());
            log.setMethod(request.getMethod());
            // 异步保存日志
            new SaveLogThread(log, handler, ex).start();
        }*/
    }
}
