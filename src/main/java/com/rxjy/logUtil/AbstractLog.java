package com.rxjy.logUtil;

import com.rxjy.logUtil.core.util.StrUtil;
import com.rxjy.logUtil.level.Level;

import java.io.Serializable;

/**
 * 抽象日志类<br></>
 * 实现了一些通用的接口
 * Created by 11019 on 17.9.12.
 */
public abstract class AbstractLog implements Log,Serializable{

    private static final long serialVersionUID = -3211115409504005616L;

    public boolean isEnabled(Level level) {
        switch (level){
            case TRACE:
                return isTraceEnabled();
            case DEBUG:
                return isDebugEnabled();
            case INFO:
                return isInfoEnabled();
            case WARN:
                return isWarnEnabled();
            case ERROR:
                return isErrorEnabled();
            default:
                    throw new Error(StrUtil.format("Can not identity level:{}",level));
        }
    }

    public void warn(Throwable t) {
        this.warn(t,t.getMessage());
    }

    public void info(Throwable t) {
        this.info(t,t.getMessage());
    }

    public void trace(Throwable t) {
        this.trace(t,t.getMessage());
    }

    public void error(Throwable t) {
        this.error(t,t.getMessage());
    }

    public void debug(Throwable t) {
        this.debug(t,t.getMessage());
    }
}
