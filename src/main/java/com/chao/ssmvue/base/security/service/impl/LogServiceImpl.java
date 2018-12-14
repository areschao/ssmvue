package com.chao.ssmvue.base.security.service.impl;

import com.chao.ssmvue.base.security.entity.Log;
import com.chao.ssmvue.base.security.mapper.LogMapper;
import com.chao.ssmvue.base.security.service.LogService;
import com.chao.ssmvue.core.service.MyServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统日志 服务实现类
 * </p>
 *
 * @author LuZichao
 * @since 2018-12-12
 */
@Service
public class LogServiceImpl extends MyServiceImpl<LogMapper, Log> implements LogService {

}
