package com.xiaoxuanfeng.plate.common.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import com.xiaoxuanfeng.plate.common.constants.Constant;
import com.xiaoxuanfeng.plate.company.domain.Company;
import com.xiaoxuanfeng.plate.company.service.CompanyService;
import com.xiaoxuanfeng.plate.navigation.service.NavigationService;

/**
 * <h2>文件名称：ApplicationContextCacheUtil.java</h2></br> <h3>文件描述：</h3></br> <h3>详细说明：</h3></br>
 * 
 * @author tangkun
 * @date 2014-10-2 下午3:38:28
 * @version 1.0
 */
@Component
public final class ServletContextCacheUtil {

	private final static Logger log = Logger.getLogger(ServletContextCacheUtil.class);

	public  Object updateCache(ProceedingJoinPoint pjp) throws Throwable {
		Object value=pjp.proceed();
		String location=pjp.getSignature().getDeclaringTypeName();
		if (location.indexOf("navigation") >= 0) {
			NavigationService navigationService = (NavigationService) Constant.context.getBean("navigationServiceImpl");
			try {
				Constant.servletContext.setAttribute("navigations", navigationService.list("from Navigation order by sort"));
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		} else if (location.indexOf("banner") >= 0) {
//			BannerService bannerService = (BannerService) Constant.context.getBean("bannerServiceImpl");
//			try {
//				Constant.servletContext.setAttribute("banners", bannerService.list("from Banner where showHomePage = 1"));
//			} catch (Exception e1) {
//				e1.printStackTrace();
//				log.error(e1.getMessage());
//			}
		} else if (location.indexOf("company") >= 0) {
			CompanyService companyService = (CompanyService) Constant.context.getBean("companyServiceImpl");
			try {
				Map<String, Object> companyinfo = new HashMap<String, Object>();
				List<Company> companys = companyService.list("from Company");
				for (Company company : companys) {
					companyinfo.put(company.getKeyword(), company);
				}
				System.out.println(companyinfo);
				Constant.servletContext.setAttribute("companyInfo", companyinfo);
			} catch (Exception e) {
				e.printStackTrace();
				log.error(e.getMessage());
			}
		}
		return value;
	}

	public final static void initCache() {
		NavigationService navigationService = (NavigationService) Constant.context.getBean("navigationServiceImpl");
		try {
			Constant.servletContext.setAttribute("navigations", navigationService.list("from Navigation order by sort "));
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
//		BannerService bannerService = (BannerService) Constant.context.getBean("bannerServiceImpl");
//		try {
//			Constant.servletContext.setAttribute("banners", bannerService.list("from Banner where  showHomePage = 1"));
//		} catch (Exception e1) {
//			e1.printStackTrace();
//			log.error(e1.getMessage());
//		}
		CompanyService companyService = (CompanyService) Constant.context.getBean("companyServiceImpl");
		try {
			Map<String, Object> companyinfo = new HashMap<String, Object>();
			List<Company> companys = companyService.list("from Company");
			for (Company company : companys) {
				companyinfo.put(company.getKeyword(), company);
			}
			Constant.servletContext.setAttribute("companyInfo", companyinfo);
		} catch (Exception e) {
			e.printStackTrace();
			log.error(e.getMessage());
		}
	}
}
