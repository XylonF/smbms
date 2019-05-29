package providerTest;

import cn.smbms.dao.provider.ProviderDao;
import cn.smbms.pojo.Provider;
import cn.smbms.services.provider.ProviderServices;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import java.util.List;

public class test {
    @Test
    public void test1(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProviderDao providerDao = (ProviderDao) context.getBean("providerDao");
        Provider cProvider = new Provider();
        cProvider.setProName("深圳");
        List<Provider> providers = providerDao.queryProvider(cProvider);
        for (Provider p:
             providers) {
            System.out.println(p.getProName());
        }
    }
    @Test
    public void test2(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProviderServices providerServices = (ProviderServices) context.getBean("providerServices");
        Provider provider = new Provider();
        provider.setProName("ttttttt");
        boolean b = providerServices.addProvider(provider);
        System.out.println(b);
    }

    @Test
    public void test3(){
        ApplicationContext context =new ClassPathXmlApplicationContext("ApplicationContext.xml");
        ProviderServices providerServices = (ProviderServices) context.getBean("providerServices");
        List<Provider> providers = providerServices.findProviders(null);
        for (Provider p:
                providers) {
            System.out.println(p.getProName());
        }
    }
}
