package com.tianyi.helmet.server.service.support;

import com.tianyi.helmet.server.util.image.ValidateCode;
import com.tianyi.helmet.server.vo.ResponseVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 图片验证码服务
 *
 * Created by liuhanc on 2017/12/8.
 */
@Service
public class VerifyCodeService {
    private Logger logger = LoggerFactory.getLogger(VerifyCodeService.class);

    @Autowired
    private ShortMessageService shortMessageService;
    @Autowired
    private ConfigService configService;

    @Autowired
    private RedisTemplate redisTemplate;
    private final static String VALID_EEY_NAME="VALID_KEY";

    /**
     * 根据操作生成1个验证码输出请求token
     * @param operate
     * @return
     */
    public String genereateToken(String operate){
        String token = UUID.randomUUID().toString();
        String key = VALID_EEY_NAME+":"+operate+":"+token;
        redisTemplate.opsForValue().set(key,operate,1, TimeUnit.MINUTES);
        return token;
    }

    /**
     * 根据验证码输出请求token
     * @param operate
     * @param token
     * @return
     */
    public ValidateCode getValidateCode(String operate,String token){
        String key = VALID_EEY_NAME+":"+operate+":"+token;
        String value = (String)redisTemplate.opsForValue().get(key);
        if(!StringUtils.isEmpty(value)){
            ValidateCode vCode = new ValidateCode(120,40,4,100);
            String code = vCode.getCode();
            redisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);//验证码5分钟有效
            return vCode;
        }else{
            //请求无效
            return null;
        }
    }

    /**
     * 校验验证码是否正确。
     * 如果不正确则自动生成新的token
     *
     * @param operate
     * @param token
     * @param code
     * @return
     */
    public ResponseVo verifyCode(String operate, String token, String code){
        if(StringUtils.isEmpty(code) )
            return ResponseVo.fail("验证码不能为空");

        String key = VALID_EEY_NAME+":"+operate+":"+token;
        String saveCode = (String)redisTemplate.opsForValue().get(key);
        if(StringUtils.isEmpty(saveCode) )
            return ResponseVo.fail("验证码并未生成,无法验证");

        redisTemplate.opsForValue().getOperations().delete(key);

        if(saveCode.equalsIgnoreCase(code)){
            //记录验证码校验通过，防止绕过验证.1分钟内1次有效
            String key2 = VALID_EEY_NAME+":"+operate+":"+token;
            redisTemplate.opsForValue().set(key2,"1",1,TimeUnit.MINUTES);
            return ResponseVo.success();
        }

        //重新生成
        String newToken = genereateToken(operate);
        ResponseVo retVo = ResponseVo.fail("验证码错误");
        retVo.setData(newToken);//token
        return retVo;
    }

    /**
     * 判断某个操作的图形验证码是否校验通过了
     *
     * @param operate
     * @return
     */
    public boolean operateVerified(String operate,String token){
        String key2 = VALID_EEY_NAME+":"+operate+":"+token;
        String val = (String)redisTemplate.opsForValue().get(key2);
        if(StringUtils.isEmpty(val)){
            return false;
        }
        redisTemplate.opsForValue().getOperations().delete(key2);
        return true;
    }

    public ResponseVo<String> sendSmsVerifyCode(String mobile){
        int code = new Double(Math.random()*9000).intValue()+1000;
        String codeStr = String.valueOf(code);
        String key = VALID_EEY_NAME+":SMS:"+mobile;
        redisTemplate.opsForValue().set(key,codeStr,configService.getMsgTimeout(), TimeUnit.MINUTES);//短信有效期
        ResponseVo<String> vo = shortMessageService.sendVerifyCode(codeStr,mobile);
        if(!vo.isSuccess()){
            redisTemplate.opsForValue().getOperations().delete(key);
        }
        return vo;
    }

    public boolean verifySmsCode(String mobile,String code){
        String key = VALID_EEY_NAME+":SMS:"+mobile;
        String oldCode = (String)redisTemplate.opsForValue().get(key);
        redisTemplate.opsForValue().getOperations().delete(key);
        if(code.equalsIgnoreCase(oldCode)){
            return true;
        }else{
            logger.debug("短信码验证不通过。手机："+mobile+",正确："+oldCode+",传入值："+code);
            return false;
        }
    }

}
