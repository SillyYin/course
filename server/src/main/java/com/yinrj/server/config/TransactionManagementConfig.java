package com.yinrj.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 对多个表在一个函数里进行操作的时候需要添加一个事务处理
 * @author Yin
 * @date 2021/1/10
 */
@EnableTransactionManagement
@Configuration
public class TransactionManagementConfig {
}
