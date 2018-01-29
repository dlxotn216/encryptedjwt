package io.crscube.coop.cubetoken.cooperation.base;

import org.springframework.stereotype.Component;

/**
 * @author Lee Tae Su 
 * @project CubeToken
 * @version 1.0
 * @since 2018-01-26
 * 
 * HTTP 요청 Thread의 Context를 wrapping 하는 클래스
 * ThreadLocal의 캡슐화
 */
@Component
public class RequestThreadContext {
	
	/**
	 * ThreadLocalInfo를 담는 local context
	 * 
	 * @see ThreadLocalInfo
	 */
	private static ThreadLocal<ThreadLocalInfo> local;
	
	static{
		local = new ThreadLocal<>();
	}
	
	public static ThreadLocalInfo getLocal(){
		ThreadLocalInfo info = local.get();
		if(info == null){
			info = new ThreadLocalInfo();
			setLocal(info);
		}
		return info;
	}
	
	private static void setLocal(ThreadLocalInfo info){
		local.set(info);
	}
	
	public static void remoteLocal(){
		local.remove();
	}
}
